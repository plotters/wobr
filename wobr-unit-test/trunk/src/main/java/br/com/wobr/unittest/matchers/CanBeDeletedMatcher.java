package br.com.wobr.unittest.matchers;

import org.hamcrest.Description;

import com.webobjects.eocontrol.EOEnterpriseObject;

/**
 * @author <a href="mailto:hprange@gmail.com">Henrique Prange</a>
 * @param <T>
 */
public class CanBeDeletedMatcher<T extends EOEnterpriseObject> extends AbstractEnhancedTypeSafeMatcher<T>
{
	public CanBeDeletedMatcher()
	{
		super();
	}

	public CanBeDeletedMatcher(final String message)
	{
		super(message);
	}

	public void describeTo(final Description description)
	{
		if(message != null)
		{
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

			return;
		}

		description.appendText("valid for delete enterprise object");
		description.appendText("\n     but got: ");

		if(exception == null)
		{
			description.appendText("a valid one");

			return;
		}

		description.appendText(exception.getClass().getName());
		description.appendText(": ");
		description.appendValue(exception.getMessage());
	}

	@Override
	protected void matchesWithPossibleException(final T eo) throws Exception
	{
		eo.validateForDelete();
	}
}
