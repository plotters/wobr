package br.com.wobr.unittest;

import org.hamcrest.Description;
import org.junit.internal.matchers.TypeSafeMatcher;

import com.webobjects.eocontrol.EOEditingContext;

/**
 * @author <a href="mailto:hprange@gmail.com">Henrique Prange</a>
 * @param <T>
 */
public class SaveChangesMatcher<T extends EOEditingContext> extends TypeSafeMatcher<T>
{
	private Exception exception;

	public void describeTo( final Description description )
	{
		description.appendText( exception.getMessage() );
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

			return false;
		}

		return true;
	}
}
