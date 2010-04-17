package br.com.wobr.unittest.rules;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.net.URL;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.wobr.unittest.model.FooEntity;
import br.com.wobr.unittest.rules.TemporaryEditingContextProvider;

import com.webobjects.eoaccess.EOModel;
import com.webobjects.eoaccess.EOModelGroup;
import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.foundation.NSArray;

import er.extensions.foundation.ERXProperties;
import er.memoryadaptor.ERMemoryAdaptorContext;

/**
 * @author <a href="mailto:hprange@gmail.com">Henrique Prange</a>
 */
@RunWith(MockitoJUnitRunner.class)
public class TestTemporaryEditingContextProvider
{
	private static final String TEST_MODEL_NAME = "Test";

	@Mock
	private EOEditingContext mockEditingContext;

	@Test(expected = IllegalStateException.class)
	public void cannotCreateEditingContextAfterTestExecution() throws Throwable
	{
		TemporaryEditingContextProvider provider = new TemporaryEditingContextProvider(TEST_MODEL_NAME);

		provider.before();
		provider.after();

		provider.editingContext();
	}

	@Test
	public void changeAdaptorForModelsNotLoadedByTemporaryEditingContextProvider() throws Exception
	{
		URL url = getClass().getResource("/" + TEST_MODEL_NAME + ".eomodeld");

		EOModel model = EOModelGroup.defaultGroup().addModelWithPathURL(url);

		model.setAdaptorName("JDBC");

		new TemporaryEditingContextProvider();

		assertThat(model.adaptorName(), is("Memory"));
	}

	@Test
	public void changeAdaptorIfModelAlreadyLoadedWithDifferentAdaptor() throws Exception
	{
		URL url = getClass().getResource("/" + TEST_MODEL_NAME + ".eomodeld");

		EOModel model = EOModelGroup.defaultGroup().addModelWithPathURL(url);

		model.setAdaptorName("JDBC");

		new TemporaryEditingContextProvider(TEST_MODEL_NAME);

		assertThat(model.adaptorName(), is("Memory"));
	}

	@Test
	public void clearEditingContextChangesAfterTestExecution() throws Exception
	{
		TemporaryEditingContextProvider provider = new TemporaryEditingContextProvider(TEST_MODEL_NAME);

		EOEditingContext editingContext = provider.editingContext();

		FooEntity foo = FooEntity.createFooEntity(editingContext);

		foo.setBar("test");

		editingContext.saveChanges();

		provider.after();

		provider = new TemporaryEditingContextProvider(TEST_MODEL_NAME);

		NSArray<FooEntity> result = FooEntity.fetchAllFooEntities(provider.editingContext());

		assertThat(result.isEmpty(), is(true));
	}

	@Test
	public void disposeEditingContextAfterTestExecution() throws Throwable
	{
		TemporaryEditingContextProvider provider = Mockito.spy(new TemporaryEditingContextProvider(TEST_MODEL_NAME));

		Mockito.doReturn(mockEditingContext).when(provider).createEditingContext();
		Mockito.doReturn(Mockito.mock(ERMemoryAdaptorContext.class)).when(provider).currentAdaptorContext();

		provider.before();

		Mockito.verify(mockEditingContext, Mockito.times(0)).dispose();

		provider.after();

		Mockito.verify(mockEditingContext, Mockito.times(1)).dispose();
	}

	@Test
	public void doNotClearTheDatabaseContextIfNoModelsLoaded() throws Throwable
	{
		TemporaryEditingContextProvider provider = new TemporaryEditingContextProvider();

		provider.before();

		EOModelGroup modelGroup = EOModelGroup.defaultGroup();

		NSArray<EOModel> models = modelGroup.models();

		for(EOModel model : models)
		{
			modelGroup.removeModel(model);
		}

		provider.after();
	}

	@Test(expected = IllegalStateException.class)
	public void exceptionIfAdaptorContextIsNotMemoryAdaptor() throws Throwable
	{
		TemporaryEditingContextProvider provider = new TemporaryEditingContextProvider(TEST_MODEL_NAME);

		NSArray<EOModel> models = EOModelGroup.defaultGroup().models();

		for(EOModel model : models)
		{
			model.setAdaptorName("JDBC");
		}

		provider.before();
		provider.after();

	}

	@Test
	public void exceptionIfCannotFindModel() throws Exception
	{
		try
		{
			new TemporaryEditingContextProvider("UnknownModel");
		}
		catch(IllegalArgumentException exception)
		{
			assertThat(exception.getMessage(), is("Cannot load model named 'UnknownModel'"));
		}
	}

	@Test
	public void initializeEditingContextOnce() throws Exception
	{
		TemporaryEditingContextProvider provider = Mockito.spy(new TemporaryEditingContextProvider(TEST_MODEL_NAME));

		Mockito.doReturn(mockEditingContext).when(provider).createEditingContext();

		provider.editingContext();
		provider.editingContext();

		Mockito.verify(provider, Mockito.times(1)).createEditingContext();
		Mockito.verify(mockEditingContext, Mockito.times(1)).lock();
	}

	@Test
	public void loadOneModel() throws Exception
	{
		new TemporaryEditingContextProvider(TEST_MODEL_NAME);

		EOModel result = EOModelGroup.defaultGroup().modelNamed(TEST_MODEL_NAME);

		assertThat(result, notNullValue());
	}

	@After
	public void tearDown()
	{
		EOModelGroup modelGroup = EOModelGroup.defaultGroup();

		EOModel model = modelGroup.modelNamed(TEST_MODEL_NAME);

		if(model != null)
		{
			modelGroup.removeModel(model);
		}
	}

	@Test
	public void useMemoryAdaptorForAllModels() throws Exception
	{
		new TemporaryEditingContextProvider(TEST_MODEL_NAME);

		String result = EOModelGroup.defaultGroup().modelNamed(TEST_MODEL_NAME).adaptorName();

		assertThat(result, is("Memory"));
	}

	@Test
	public void useMemoryPrototypesForAllModels() throws Exception
	{
		new TemporaryEditingContextProvider();

		String result = ERXProperties.stringForKey("dbEOPrototypesEntityGLOBAL");

		assertThat(result, is("EOMemoryPrototypes"));
	}
}
