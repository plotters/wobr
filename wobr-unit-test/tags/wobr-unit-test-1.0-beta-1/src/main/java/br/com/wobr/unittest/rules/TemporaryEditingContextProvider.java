package br.com.wobr.unittest.rules;

import java.net.URL;

import org.junit.rules.ExternalResource;

import com.webobjects.eoaccess.EOAdaptorContext;
import com.webobjects.eoaccess.EODatabaseContext;
import com.webobjects.eoaccess.EOModel;
import com.webobjects.eoaccess.EOModelGroup;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSBundle;

import er.extensions.eof.ERXEC;
import er.extensions.foundation.ERXProperties;
import er.memoryadaptor.ERMemoryAdaptorContext;

/**
 * The TemporaryEditingContextProvider Rule provides an editing context using
 * the memory adaptor that is guaranteed to be reset when the test method
 * finishes (whether it passes or fails):
 * 
 * <pre>
 * public class TestMyModel
 * {
 * 	&#064;Rule
 * 	public TemporaryEditingContextProvider provider = new TemporaryEditingContextProvider();
 * 
 * 	&#064;Test
 * 	public void testingMyModelLogic() throws Exception
 * 	{
 * 		EOEditingContext editingContext = provider.editingContext();
 * 		MyEntity object = (MyEntity) EOUtilities.createAndInsertInstance(editingContext, &quot;MyEntity&quot;);
 * 		// ...
 * 	}
 * }
 * </pre>
 * 
 * @author <a href="mailto:hprange@gmail.com">Henrique Prange</a>
 */
public class TemporaryEditingContextProvider extends ExternalResource
{
	private EOEditingContext editingContext;

	private boolean finished = false;

	private boolean initialized = false;

	/**
	 * Creates a TemporaryEditingContextProvider rule.
	 * 
	 * @param modelNames
	 *            the name of all models required by unit tests.
	 */
	public TemporaryEditingContextProvider(final String... modelNames)
	{
		fixJavaMemoryDictionary();

		// Use Memory prototypes for tests. We don't want to set this
		// information in the EOModel dictionary
		ERXProperties.setStringForKey("EOMemoryPrototypes", "dbEOPrototypesEntityGLOBAL");

		for(String modelName : modelNames)
		{
			loadModel(modelName);
		}

		NSArray<EOModel> models = EOModelGroup.defaultGroup().models();

		for(EOModel model : models)
		{
			if(!"Memory".equals(model.adaptorName()))
			{
				// Use Memory adaptor for tests. We don't want to set this
				// information in the EOModel dictionary
				model.setAdaptorName("Memory");
			}
		}
	}

	/**
	 * Reset all changes made into the provided editing context.
	 * 
	 * @see org.junit.rules.ExternalResource#after()
	 */
	@Override
	protected void after()
	{
		ERMemoryAdaptorContext adaptorContext = currentAdaptorContext();

		if(adaptorContext != null)
		{
			adaptorContext.resetAllEntities();
		}

		editingContext.revert();
		editingContext.unlock();
		editingContext.dispose();
		editingContext = null;

		finished = true;

		super.after();
	}

	/**
	 * Initialize the editing context provided for unit testing.
	 * 
	 * @see org.junit.rules.ExternalResource#before()
	 */
	@Override
	protected void before() throws Throwable
	{
		super.before();

		initializeOnce();
	}

	EOEditingContext createEditingContext()
	{
		return ERXEC.newEditingContext();
	}

	ERMemoryAdaptorContext currentAdaptorContext()
	{
		NSArray<String> modelNames = EOModelGroup.defaultGroup().modelNames();

		if(modelNames.isEmpty())
		{
			return null;
		}

		EODatabaseContext databaseContext = EOUtilities.databaseContextForModelNamed(editingContext, modelNames.objectAtIndex(0));

		EOAdaptorContext adaptorContext = databaseContext.adaptorContext();

		if(!(adaptorContext instanceof ERMemoryAdaptorContext))
		{
			throw new IllegalStateException(String.format("Expected %s, but got %s. Please, use the %s constructor to load all the required models for testing.", ERMemoryAdaptorContext.class.getName(), adaptorContext.getClass().getName(), this.getClass().getSimpleName()));
		}

		return (ERMemoryAdaptorContext) adaptorContext;
	}

	/**
	 * Get the editing context to be used for unit testing.
	 * 
	 * @return an <code>EOEditingContext</code> that save changes in memory.
	 */
	public EOEditingContext editingContext()
	{
		if(finished)
		{
			throw new IllegalStateException(String.format("You cannot obtain an editing context instance after the %s disposal", this.getClass().getSimpleName()));
		}

		if(editingContext == null)
		{
			initializeOnce();
		}

		return editingContext;
	}

	/**
	 * Maven build produces an incomplete Info.plist dictionary for
	 * JavaMemoryAdaptor framework. Fixing it here temporarily.
	 */
	private void fixJavaMemoryDictionary()
	{
		NSBundle bundle = NSBundle.bundleForName("JavaMemoryAdaptor");

		bundle._infoDictionary().takeValueForKey("er.memoryadaptor.ERMemoryAdaptor", "EOAdaptorClassName");
	}

	private void initializeOnce()
	{
		if(initialized)
		{
			return;
		}

		initialized = true;

		editingContext = createEditingContext();

		editingContext.lock();
	}

	/**
	 * Load the model with the specified name into the default model group.
	 * 
	 * @param modelName
	 *            name of the model to be loaded
	 * @throws IllegalArgumentException
	 *             if no model can be found with the specified name
	 * @see EOModelGroup#defaultGroup();
	 */
	protected void loadModel(final String modelName)
	{
		EOModelGroup modelGroup = EOModelGroup.defaultGroup();

		EOModel model = modelGroup.modelNamed(modelName);

		if(model != null)
		{
			return;
		}

		URL url = getClass().getResource("/" + modelName + ".eomodeld");

		if(url == null)
		{
			throw new IllegalArgumentException(String.format("Cannot load model named '%s'", modelName));
		}

		modelGroup.addModelWithPathURL(url);
	}
}
