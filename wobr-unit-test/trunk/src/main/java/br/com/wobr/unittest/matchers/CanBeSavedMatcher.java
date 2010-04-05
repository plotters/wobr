package br.com.wobr.unittest.matchers;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.internal.matchers.TypeSafeMatcher;

import com.webobjects.eocontrol.EOEnterpriseObject;

/**
 * @author <a href="mailto:hprange@gmail.com">Henrique Prange</a>
 * 
 * @param <T>
 *            a kind of <code>EOEnterpriseObject</code>
 */
public class CanBeSavedMatcher<T extends EOEnterpriseObject> extends TypeSafeMatcher<T>
{
	private Exception exception;

	private final String message;

	public CanBeSavedMatcher()
	{
		this.message = null;
	}

	public CanBeSavedMatcher(final String message)
	{
		this.message = message;
	}

	public void describeTo(final Description description)
	{
		if(message == null)
		{
			description.appendText("valid for save enterprise object");
			description.appendText("\n     but got: ");

			if(exception == null)
			{
				description.appendText("a valid one\n");

				return;
			}

			description.appendText(exception.getClass().getName());
			description.appendText(": ");
			description.appendValue(exception.getMessage());

			return;
		}

		description.appendText("expecting exception other than \"");
		description.appendText(message);
		description.appendText("\"\n     but got: ");

		if(exception == null)
		{
			description.appendText("no validation exception");

			return;
		}

		description.appendText(exception.getClass().getName());
		description.appendText(": \"");
		description.appendText(exception.getMessage());
		description.appendText("\"");
	}

	@Override
	public boolean matchesSafely(final T eo)
	{
		try
		{
			eo.validateForSave();
		}
		catch(Exception exception)
		{
			this.exception = exception;

			if(message != null)
			{
				Matcher<String> matcher = is(not(message));

				return matcher.matches(exception.getMessage());
			}

			return false;
		}

		return true;
	}
}
