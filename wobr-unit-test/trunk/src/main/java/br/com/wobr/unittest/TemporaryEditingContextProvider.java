package br.com.wobr.unittest;

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

public class TemporaryEditingContextProvider extends ExternalResource
{
	private EOEditingContext editingContext;

	private boolean initialized = false;

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

	@Override
	protected void after()
	{
		EODatabaseContext databaseContext = EOUtilities.databaseContextForModelNamed(editingContext, EOModelGroup.defaultGroup().modelNames().objectAtIndex(0));
		EOAdaptorContext adaptorContext = databaseContext.adaptorContext();

		((ERMemoryAdaptorContext) adaptorContext).resetAllEntities();

		editingContext.revert();
		editingContext.unlock();
		editingContext.dispose();
		editingContext = null;

		super.after();
	}

	@Override
	protected void before() throws Throwable
	{
		super.before();

		initializeOnce();
	}

	public EOEditingContext editingContext()
	{
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

		editingContext = ERXEC.newEditingContext();

		editingContext.lock();
	}

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
