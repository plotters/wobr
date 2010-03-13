package br.com.wobr.unittest;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.rules.ExternalResource;

import com.webobjects.eoaccess.EOAdaptorContext;
import com.webobjects.eoaccess.EODatabaseContext;
import com.webobjects.eoaccess.EOModel;
import com.webobjects.eoaccess.EOModelGroup;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.eocontrol.EOEnterpriseObject;
import com.webobjects.eocontrol.EOGlobalID;
import com.webobjects.foundation.NSBundle;

import er.extensions.eof.ERXEC;
import er.memoryadaptor.ERMemoryAdaptorContext;

public class TemporaryEditingContextProvider extends ExternalResource
{
	private EOEditingContext editingContext;

	private boolean initialized = false;

	public TemporaryEditingContextProvider(String... modelNames)
	{
		fixJavaMemoryDictionary();

		// Use Memory prototypes for tests. We don't want to set this
		// information in the EOModel dictionary
		System.setProperty("dbEOPrototypesEntityGLOBAL", "EOMemoryPrototypes");

		for(String modelName : modelNames)
		{
			if(EOModelGroup.defaultGroup().modelNamed(modelName) == null)
			{
				URL url;

				try
				{
					url = new File("src/main/resources/" + modelName + ".eomodeld").toURI().toURL();
				}
				catch(MalformedURLException exception)
				{
					throw new RuntimeException(exception);
				}

				EOModel model = EOModelGroup.defaultGroup().addModelWithPathURL(url);

				// Use Memory adaptor for tests. We don't want to set this
				// information in the EOModel dictionary
				model.setAdaptorName("Memory");
			}
		}
	}

	@Override
	protected void after()
	{
		EODatabaseContext databaseContext = EOUtilities.databaseContextForModelNamed(editingContext, "Boleto");
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

	public boolean eoHasBeenSaved(EOEnterpriseObject anEnterpriseObject)
	{
		EOGlobalID globalId = editingContext().globalIDForObject(anEnterpriseObject);
		return !(globalId == null || globalId.isTemporary());
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

		System.out.println("inicializou editingContext");

		initialized = true;

		editingContext = ERXEC.newEditingContext();

		editingContext.lock();
	}

	public void saveChanges(boolean assumeSuccess)
	{
		Exception exception = null;
		try
		{
			editingContext().saveChanges();
		}
		catch(Exception e)
		{
			exception = e;
			if(assumeSuccess)
			{
				e.printStackTrace();
				editingContext().revert();
			}
		}
		if(assumeSuccess)
		{
			assertNull(exception);
		}
		else
		{
			assertNotNull(exception);
		}
	}
}
