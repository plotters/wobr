package br.com.wobr.unittest;

import static br.com.wobr.unittest.EOAssert.confirm;
import static br.com.wobr.unittest.EOAssert.hasBeenDeleted;
import static br.com.wobr.unittest.EOAssert.hasBeenSaved;
import static br.com.wobr.unittest.EOAssert.hasNotBeenDeleted;
import static br.com.wobr.unittest.EOAssert.hasNotBeenSaved;

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
	public final TemporaryEditingContextProvider provider = new TemporaryEditingContextProvider("Test");

	@Rule
	public final ExpectedException thrown = ExpectedException.none();

	@Test
	public void hasBeenDeletedFailure() throws Exception
	{
		thrown.expect(AssertionError.class);
		thrown.expectMessage("\nExpected: deleted object\n     but got: an active object");

		confirm(foo, hasBeenDeleted());
	}

	@Test
	public void hasBeenDeletedSuccessAfterSaveChanges() throws Exception
	{
		editingContext.deleteObject(foo);
		editingContext.saveChanges();

		confirm(foo, hasBeenDeleted());
	}

	@Test
	public void hasBeenDeletedSucessBeforeSaveChanges() throws Exception
	{
		editingContext.deleteObject(foo);

		confirm(foo, hasBeenDeleted());
	}

	@Test
	public void hasBeenSavedFailure() throws Exception
	{
		thrown.expect(AssertionError.class);
		thrown.expectMessage("\nExpected: saved object\n     but got: an object with unsaved changes");

		confirm(foo, hasBeenSaved());
	}

	@Test
	public void hasBeenSavedSuccess() throws Exception
	{
		editingContext.saveChanges();

		confirm(foo, hasBeenSaved());
	}

	@Test
	public void hasNotBeenDeletedFailure() throws Exception
	{
		editingContext.deleteObject(foo);

		thrown.expect(AssertionError.class);
		thrown.expectMessage("\nExpected: not deleted object\n     but got: a deleted object");

		confirm(foo, hasNotBeenDeleted());
	}

	@Test
	public void hasNotBeenDeletedSuccess() throws Exception
	{
		confirm(foo, hasNotBeenDeleted());
	}

	@Test
	public void hasNotBeenSavedFailure() throws Exception
	{
		editingContext.saveChanges();

		thrown.expect(AssertionError.class);
		thrown.expectMessage("\nExpected: not saved object\n     but got: an object with saved changes");

		confirm(foo, hasNotBeenSaved());
	}

	@Test
	public void hasNotBeenSavedSuccess() throws Exception
	{
		confirm(foo, hasNotBeenSaved());
	}

	@Test
	public void hasUnsavedChangesAfterSaveChanges() throws Exception
	{
		editingContext.saveChanges();

		foo.setBar("test");

		confirm(foo, hasNotBeenSaved());
	}

	@Before
	public void setup()
	{
		editingContext = provider.editingContext();

		foo = FooEntity.createFooEntity(editingContext);
	}
}
