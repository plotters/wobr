package br.com.wobr.unittest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;
import org.junit.internal.matchers.TypeSafeMatcher;

import com.webobjects.eocontrol.EOEnterpriseObject;

public class CanBeSavedMatcher<T extends EOEnterpriseObject> extends TypeSafeMatcher<T>
{
	public static Matcher<EOEnterpriseObject> canBeSaved()
	{
		return new CanBeSavedMatcher<EOEnterpriseObject>();
	}

	public static Matcher<EOEnterpriseObject> cannotBeSaved()
	{
		return not(canBeSaved());
	}

	public static Matcher<EOEnterpriseObject> cannotBeSavedBecauseOf(String message)
	{
		return not(new CanBeSavedMatcher<EOEnterpriseObject>(message));
	}

	public static <T extends EOEnterpriseObject> void verify(T actual, Matcher<T> matcher)
	{
		if(!matcher.matches(actual))
		{
			Description description = new StringDescription();

			description.appendText("\nExpected: ");
			description.appendDescriptionOf(matcher);

			throw new AssertionError(description.toString());
		}
	}

	private Exception exception;

	private final String message;

	public CanBeSavedMatcher()
	{
		this.message = null;
	}

	public CanBeSavedMatcher(String message)
	{
		this.message = message;
	}

	public void describeTo(Description description)
	{
		if(exception == null && message == null)
		{
			description.appendText("\nvalid for save enterprise object ");
			description.appendText("\n     but got: a valid one\n");

			return;
		}

		if(exception != null && message == null)
		{
			description.appendText("no validate for save exception");
			description.appendText("\n     but got: ");
			description.appendValue(exception.getMessage());
			description.appendText("\n");
		}

		if(exception == null && message != null)
		{
			description.appendText(message);
			description.appendText("\n     but got: no validation exception");
			description.appendText("\n");
		}

		description.appendText("expecting exception other than \"");
		description.appendText(message);
		description.appendText("\"\n     but got: \"");
		description.appendText(exception.getMessage());
		description.appendText("\"\n");
	}

	@Override
	public boolean matchesSafely(T eo)
	{
		try
		{
			eo.validateForSave();
		}
		catch(Exception e)
		{
			exception = e;

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
