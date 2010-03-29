package br.com.wobr.unittest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Test;

import com.webobjects.eoaccess.EOModel;
import com.webobjects.eoaccess.EOModelGroup;

import er.extensions.foundation.ERXProperties;

/**
 * TODO: Initialize editing context once
 * <p>
 * TODO: Clear editing context changes
 * <p>
 * TODO: Dispose editing context after test execution
 * 
 * @author <a href="mailto:hprange@gmail.com">Henrique Prange</a>
 */
public class TestTemporaryEditingContextProvider
{
	private static final String TEST_MODEL_NAME = "Test";

	@Test
	public void exceptionIfCannotFindModel() throws Exception
	{
		try
		{
			new TemporaryEditingContextProvider( "UnknownModel" );
		}
		catch( IllegalArgumentException exception )
		{
			assertThat( exception.getMessage(), is( "Cannot load model named 'UnknownModel'" ) );
		}

	}

	@Test
	public void loadOneModel() throws Exception
	{
		new TemporaryEditingContextProvider( TEST_MODEL_NAME );

		EOModel result = EOModelGroup.defaultGroup().modelNamed( TEST_MODEL_NAME );

		assertThat( result, notNullValue() );
	}

	@After
	public void tearDown()
	{
		EOModelGroup modelGroup = EOModelGroup.defaultGroup();

		EOModel model = modelGroup.modelNamed( TEST_MODEL_NAME );

		if( model != null )
		{
			modelGroup.removeModel( model );
		}
	}

	@Test
	public void useMemoryAdaptorForAllModels() throws Exception
	{
		new TemporaryEditingContextProvider( TEST_MODEL_NAME );

		String result = EOModelGroup.defaultGroup().modelNamed( TEST_MODEL_NAME ).adaptorName();

		assertThat( result, is( "Memory" ) );
	}

	@Test
	public void useMemoryPrototypesForAllModels() throws Exception
	{
		new TemporaryEditingContextProvider();

		String result = ERXProperties.stringForKey( "dbEOPrototypesEntityGLOBAL" );

		assertThat( result, is( "EOMemoryPrototypes" ) );
	}
}
