package br.com.wobr.unittest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.mockito.Mockito;

import com.webobjects.eoaccess.EOModel;
import com.webobjects.eoaccess.EOModelGroup;

import er.extensions.foundation.ERXProperties;

/**
 * TODO: change loaded model to use the memory adaptor
 * <p>
 * TODO: Exception if model does not exist or cannot be find
 * 
 * @author <a href="mailto:hprange@gmail.com">Henrique Prange</a>
 */
public class TestTemporaryEditingContextProvider
{
	private static final String TEST_MODEL_NAME = "Test";

	@Test
	public void doNotLoadTheSameModelTwice() throws Exception
	{
		new TemporaryEditingContextProvider( TEST_MODEL_NAME );

		TemporaryEditingContextProvider provider = Mockito.spy( new TemporaryEditingContextProvider( TEST_MODEL_NAME ) );

		Mockito.verify( provider, Mockito.times( 0 ) ).loadModel( TEST_MODEL_NAME );
	}

	@Test
	public void loadOneModel() throws Exception
	{
		new TemporaryEditingContextProvider( TEST_MODEL_NAME );

		EOModel result = EOModelGroup.defaultGroup().modelNamed( TEST_MODEL_NAME );

		assertThat( result, notNullValue() );
	}

	@Test
	public void useMemoryPrototypesForAllModules() throws Exception
	{
		new TemporaryEditingContextProvider();

		String result = ERXProperties.stringForKey( "dbEOPrototypesEntityGLOBAL" );

		assertThat( result, is( "EOMemoryPrototypes" ) );
	}
}
