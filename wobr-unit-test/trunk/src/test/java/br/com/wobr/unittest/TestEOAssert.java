package br.com.wobr.unittest;

import static br.com.wobr.unittest.EOAssert.confirm;
import static br.com.wobr.unittest.EOAssert.hasBeenDeleted;
import static br.com.wobr.unittest.EOAssert.hasNotBeenDeleted;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import br.com.wobr.unittest.model.FooEntity;
import br.com.wobr.unittest.rules.TemporaryEditingContextProvider;

import com.webobjects.eocontrol.EOEditingContext;

/**
 * @author <a href="mailto:hprange@gmail.com">Henrique Prange</a>
 */
public class TestEOAssert
{
	@Rule
	public final TemporaryEditingContextProvider provider = new TemporaryEditingContextProvider( "Test" );

	private EOEditingContext editingContext;

	private FooEntity foo;

	@Test
	public void hasBeenDeletedFailure() throws Exception
	{
		try
		{
			confirm( foo, hasBeenDeleted() );
		}
		catch( AssertionError exception )
		{
			assertThat( exception.getMessage(), is( "\nExpected: deleted object\n     but got: an active object" ) );
		}
	}

	@Test
	public void hasBeenDeletedSuccessAfterSaveChanges() throws Exception
	{
		editingContext.deleteObject( foo );
		editingContext.saveChanges();

		confirm( foo, hasBeenDeleted() );
	}

	@Test
	public void hasBeenDeletedSucessBeforeSaveChanges() throws Exception
	{
		editingContext.deleteObject( foo );

		confirm( foo, hasBeenDeleted() );
	}

	@Test
	public void hasNotBeenDeletedFailure() throws Exception
	{
		editingContext.deleteObject( foo );

		try
		{
			confirm( foo, hasNotBeenDeleted() );
		}
		catch( AssertionError exception )
		{
			assertThat( exception.getMessage(), is( "\nExpected: not deleted object\n     but got: an deleted object" ) );
		}
	}

	@Test
	public void hasNotBeenDeletedSuccess() throws Exception
	{
		confirm( foo, hasNotBeenDeleted() );
	}

	@Before
	public void setup()
	{
		editingContext = provider.editingContext();

		foo = FooEntity.createFooEntity( editingContext );
	}
}
