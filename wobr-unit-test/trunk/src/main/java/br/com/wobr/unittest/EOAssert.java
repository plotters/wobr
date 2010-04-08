package br.com.wobr.unittest;

import static org.hamcrest.CoreMatchers.not;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;

import br.com.wobr.unittest.matchers.CanBeSavedMatcher;
import br.com.wobr.unittest.matchers.HasBeenDeletedMatcher;
import br.com.wobr.unittest.matchers.SaveChangesMatcher;

import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.eocontrol.EOEnterpriseObject;

/**
 * @author <a href="mailto:hprange@gmail.com">Henrique Prange</a>
 */
public class EOAssert
{
	public static Matcher<EOEnterpriseObject> canBeSaved()
	{
		return new CanBeSavedMatcher<EOEnterpriseObject>();
	}

	public static Matcher<EOEnterpriseObject> cannotBeSaved()
	{
		return not( canBeSaved() );
	}

	public static Matcher<EOEnterpriseObject> cannotBeSavedBecauseOf( final String message )
	{
		return not( new CanBeSavedMatcher<EOEnterpriseObject>( message ) );
	}

	public static <T extends EOEnterpriseObject> void confirm( final T actual, final Matcher<T> matcher )
	{
		if( matcher.matches( actual ) )
		{
			return;
		}

		Description description = new StringDescription();

		description.appendText( "\nExpected: " );
		description.appendDescriptionOf( matcher );

		throw new AssertionError( description.toString() );
	}

	public static Matcher<EOEditingContext> doNotSaveChanges()
	{
		return not( saveChanges() );
	}

	public static Matcher<EOEditingContext> doNotSaveChangesBecauseOf( final String message )
	{
		return not( new SaveChangesMatcher<EOEditingContext>( message ) );
	}

	public static Matcher<EOEnterpriseObject> hasBeenDeleted()
	{
		return new HasBeenDeletedMatcher<EOEnterpriseObject>();
	}

	public static Matcher<EOEnterpriseObject> hasNotBeenDeleted()
	{
		return not( hasBeenDeleted() );
	}

	public static Matcher<EOEditingContext> saveChanges()
	{
		return new SaveChangesMatcher<EOEditingContext>();
	}
}
