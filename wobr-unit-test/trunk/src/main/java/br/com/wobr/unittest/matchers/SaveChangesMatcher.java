package br.com.wobr.unittest.matchers;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.internal.matchers.TypeSafeMatcher;

import com.webobjects.eocontrol.EOEditingContext;

/**
 * @author <a href="mailto:hprange@gmail.com">Henrique Prange</a>
 * @param <T> a kind of <code>EOEditingContext</code>.
 */
public class SaveChangesMatcher<T extends EOEditingContext> extends TypeSafeMatcher<T>
{
	private Exception exception;

	private final String message;

	public SaveChangesMatcher()
	{
		this.message = null;
	}

	public SaveChangesMatcher( final String message )
	{
		this.message = message;
	}

	public void describeTo( final Description description )
	{
		if( message != null )
		{
			description.appendText( "expecting exception other than \"" );
			description.appendText( message );
			description.appendText( "\" while saving the editing context\n     but got: " );

			if( exception == null )
			{
				description.appendText( "no exception and the editing context was successfully saved" );

				return;
			}

			description.appendText( exception.getClass().getName() );
			description.appendText( ": \"" );
			description.appendText( exception.getMessage() );
			description.appendText( "\"" );

			return;
		}

		if( exception != null )
		{
			description.appendText( "successfully saved editing context\n     but got: " );
			description.appendText( exception.getClass().getName() );
			description.appendText( ": \"" );
			description.appendText( exception.getMessage() );
			description.appendText( "\"" );

			return;
		}

		description.appendText( "successfully saved editing context\n     but got: a successfully saved editing context\n" );
	}

	@Override
	public boolean matchesSafely( final T editingContext )
	{
		try
		{
			editingContext.saveChanges();
		}
		catch( Exception exception )
		{
			this.exception = exception;

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
