package br.com.wobr.unittest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.internal.matchers.TypeSafeMatcher;

import com.webobjects.eocontrol.EOEnterpriseObject;

public class CanBeSavedMatcher<T extends EOEnterpriseObject> extends TypeSafeMatcher<T>
{
	private Exception exception;

	private final String message;

	public CanBeSavedMatcher()
	{
		this.message = null;
	}

	public CanBeSavedMatcher( final String message )
	{
		this.message = message;
	}

	public void describeTo( final Description description )
	{
		if( exception == null && message == null )
		{
			description.appendText( "\nvalid for save enterprise object " );
			description.appendText( "\n     but got: a valid one\n" );

			return;
		}

		if( exception != null && message == null )
		{
			description.appendText( "no validate for save exception" );
			description.appendText( "\n     but got: " );
			description.appendValue( exception.getMessage() );
			description.appendText( "\n" );

			return;
		}

		if( exception == null && message != null )
		{
			description.appendText( "expecting exception other than \"" );
			description.appendText( message );
			description.appendText( "\"\n     but got: no validation exception" );
			description.appendText( "\n" );

			return;
		}

		description.appendText( "expecting exception other than \"" );
		description.appendText( message );
		description.appendText( "\"\n     but got: \"" );
		description.appendText( exception.getMessage() );
		description.appendText( "\"\n" );
	}

	@Override
	public boolean matchesSafely( final T eo )
	{
		try
		{
			eo.validateForSave();
		}
		catch( Exception e )
		{
			exception = e;

			if( message != null )
			{
				Matcher<String> matcher = is( not( message ) );

				return matcher.matches( exception.getMessage() );
			}

			return false;
		}

		return true;
	}
}
