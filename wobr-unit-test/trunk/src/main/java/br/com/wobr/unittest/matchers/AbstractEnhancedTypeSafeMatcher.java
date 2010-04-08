package br.com.wobr.unittest.matchers;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

import org.hamcrest.Matcher;
import org.junit.internal.matchers.TypeSafeMatcher;

/**
 * @author <a href="mailto:hprange@gmail.com">Henrique Prange</a>
 * @param <T>
 */
public abstract class AbstractEnhancedTypeSafeMatcher<T> extends TypeSafeMatcher<T>
{
	protected Exception exception;

	protected final String message;

	public AbstractEnhancedTypeSafeMatcher()
	{
		this.message = null;
	}

	public AbstractEnhancedTypeSafeMatcher( final String message )
	{
		this.message = message;
	}

	@Override
	public final boolean matchesSafely( final T item )
	{
		try
		{
			matchesWithPossibleException( item );
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

	protected abstract void matchesWithPossibleException( T item ) throws Exception;
}
