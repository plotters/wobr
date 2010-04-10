package br.com.wobr.unittest;

import static br.com.wobr.unittest.EOAssert.canBeDeleted;
import static br.com.wobr.unittest.EOAssert.canBeSaved;
import static br.com.wobr.unittest.EOAssert.cannotBeDeleted;
import static br.com.wobr.unittest.EOAssert.cannotBeDeletedBecause;
import static br.com.wobr.unittest.EOAssert.cannotBeSaved;
import static br.com.wobr.unittest.EOAssert.confirm;
import static br.com.wobr.unittest.EOAssert.hasBeenDeleted;
import static br.com.wobr.unittest.EOAssert.hasBeenSaved;
import static br.com.wobr.unittest.EOAssert.hasNotBeenDeleted;
import static br.com.wobr.unittest.EOAssert.hasNotBeenSaved;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import br.com.wobr.unittest.model.FooEntity;
import br.com.wobr.unittest.rules.TemporaryEditingContextProvider;

import com.webobjects.eocontrol.EOEditingContext;

/**
 * @author <a href="mailto:hprange@gmail.com">Henrique Prange</a>
 */
public class TestEOAssert
{
	private EOEditingContext editingContext;

	private FooEntity foo;

	@Rule
	public final TemporaryEditingContextProvider provider = new TemporaryEditingContextProvider( "Test" );

	@Rule
	public final ExpectedException thrown = ExpectedException.none();

	@Test
	public void canBeDeletedFailure() throws Exception
	{
		foo.setCanBeDeleted( false );

		thrown.expect( AssertionError.class );
		thrown.expectMessage( is( "\nExpected: valid for delete enterprise object\n     but got: com.webobjects.foundation.NSValidation$ValidationException: \"This foo object can't be deleted\"" ) );

		confirm( foo, canBeDeleted() );
	}

	@Test
	public void canBeDeletedSuccess() throws Exception
	{
		foo.setCanBeDeleted( true );

		confirm( foo, canBeDeleted() );
	}

	@Test
	public void canBeSavedFailure() throws Exception
	{
		foo.setCanBeSaved( false );

		thrown.expect( AssertionError.class );
		thrown.expectMessage( is( "\nExpected: valid for save enterprise object\n     but got: com.webobjects.foundation.NSValidation$ValidationException: \"This foo object can't be saved\"" ) );

		confirm( foo, canBeSaved() );
	}

	@Test
	public void canBeSavedSuccess() throws Exception
	{
		foo.setCanBeSaved( true );

		confirm( foo, canBeSaved() );
	}

	@Test
	public void cannotBeDeletedFailure() throws Exception
	{
		foo.setCanBeDeleted( true );

		thrown.expect( AssertionError.class );
		thrown.expectMessage( is( "\nExpected: not valid for delete enterprise object\n     but got: a valid one" ) );

		confirm( foo, cannotBeDeleted() );
	}

	@Test
	public void cannotBeDeletedSuccess() throws Exception
	{
		foo.setCanBeDeleted( false );

		confirm( foo, cannotBeDeleted() );
	}

	@Test
	public void cannotBeDeletedWithMessageFailure() throws Exception
	{
		foo.setCanBeDeleted( true );

		thrown.expect( AssertionError.class );
		thrown.expectMessage( is( "\nExpected: not expecting exception other than \"This foo object can't be deleted\"\n     but got: no validation exception" ) );

		confirm( foo, cannotBeDeletedBecause( "This foo object can't be deleted" ) );
	}

	@Test
	public void cannotBeDeletedWithMessageSuccess() throws Exception
	{
		foo.setCanBeDeleted( false );

		confirm( foo, cannotBeDeletedBecause( "This foo object can't be deleted" ) );
	}

	@Test
	public void cannotBeDeletedWithWrongCauseFailure() throws Exception
	{
		foo.setCanBeDeleted( false );

		thrown.expect( AssertionError.class );
		thrown.expectMessage( is( "\nExpected: not expecting exception other than \"The wrong exception\"\n     but got: com.webobjects.foundation.NSValidation$ValidationException: \"This foo object can't be deleted\"" ) );

		confirm( foo, cannotBeDeletedBecause( "The wrong exception" ) );
	}

	@Test
	public void cannotBeSavedFailure() throws Exception
	{
		foo.setCanBeSaved( true );

		thrown.expect( AssertionError.class );
		thrown.expectMessage( is( "\nExpected: not valid for save enterprise object\n     but got: a valid one" ) );

		confirm( foo, cannotBeSaved() );
	}

	@Test
	public void cannotBeSavedSuccess() throws Exception
	{
		foo.setCanBeSaved( false );

		confirm( foo, cannotBeSaved() );
	}

	@Test
	public void cannotBeSavedWithMessageSuccess() throws Exception
	{
		foo.setCanBeSaved( false );

		confirm( foo, cannotBeDeletedBecause( "This foo object can't be saved" ) );
	}

	@Test
	public void hasBeenDeletedFailure() throws Exception
	{
		thrown.expect( AssertionError.class );
		thrown.expectMessage( is( "\nExpected: deleted object\n     but got: an active object" ) );

		confirm( foo, hasBeenDeleted() );
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
	public void hasBeenSavedFailure() throws Exception
	{
		thrown.expect( AssertionError.class );
		thrown.expectMessage( is( "\nExpected: saved object\n     but got: an object with unsaved changes" ) );

		confirm( foo, hasBeenSaved() );
	}

	@Test
	public void hasBeenSavedSuccess() throws Exception
	{
		editingContext.saveChanges();

		confirm( foo, hasBeenSaved() );
	}

	@Test
	public void hasNotBeenDeletedFailure() throws Exception
	{
		editingContext.deleteObject( foo );

		thrown.expect( AssertionError.class );
		thrown.expectMessage( is( "\nExpected: not deleted object\n     but got: a deleted object" ) );

		confirm( foo, hasNotBeenDeleted() );
	}

	@Test
	public void hasNotBeenDeletedSuccess() throws Exception
	{
		confirm( foo, hasNotBeenDeleted() );
	}

	@Test
	public void hasNotBeenSavedFailure() throws Exception
	{
		editingContext.saveChanges();

		thrown.expect( AssertionError.class );
		thrown.expectMessage( is( "\nExpected: not saved object\n     but got: an object with saved changes" ) );

		confirm( foo, hasNotBeenSaved() );
	}

	@Test
	public void hasNotBeenSavedSuccess() throws Exception
	{
		confirm( foo, hasNotBeenSaved() );
	}

	@Test
	public void hasUnsavedChangesAfterSaveChanges() throws Exception
	{
		editingContext.saveChanges();

		foo.setBar( "test" );

		confirm( foo, hasNotBeenSaved() );
	}

	@Before
	public void setup()
	{
		editingContext = provider.editingContext();

		foo = FooEntity.createFooEntity( editingContext );
	}
}
