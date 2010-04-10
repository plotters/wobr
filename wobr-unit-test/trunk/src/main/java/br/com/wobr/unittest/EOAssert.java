package br.com.wobr.unittest;

import static org.hamcrest.CoreMatchers.not;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;

import br.com.wobr.unittest.matchers.CanBeDeletedMatcher;
import br.com.wobr.unittest.matchers.CanBeSavedMatcher;
import br.com.wobr.unittest.matchers.HasBeenDeletedMatcher;
import br.com.wobr.unittest.matchers.HasBeenSavedMatcher;
import br.com.wobr.unittest.matchers.SaveChangesMatcher;

import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.eocontrol.EOEnterpriseObject;

/**
 * This class is the entry point for writing assertions to WebObjects related
 * unit tests.
 * <p>
 * Before start using this framework, remember to use the import static feature
 * in order to write more readable code:
 * 
 * <pre>
 * import static br.com.wobr.unittest.EOAssert.*;
 * </pre>
 * 
 * <h4>Confirm that an enterprise object can/cannot be saved</h4>
 * 
 * <pre>
 * // Checks whether the eo can be saved
 * confirm(eo, canBeSaved());
 * </pre>
 * 
 * <pre>
 * // Checks whether the eo cannot be saved
 * confirm(eo, cannotBeSaved());
 * </pre>
 * 
 * <pre>
 * // Much safer check whether eo cannot be saved
 * confirm(eo, cannotBeSavedBecause(&quot;The foo property cannot be null&quot;));
 * </pre>
 * 
 * </pre>
 * 
 * @author <a href="mailto:hprange@gmail.com">Henrique Prange</a>
 * @since 1.0
 */
public class EOAssert
{
	public static Matcher<EOEnterpriseObject> canBeDeleted()
	{
		return new CanBeDeletedMatcher<EOEnterpriseObject>();
	}

	public static Matcher<EOEnterpriseObject> canBeSaved()
	{
		return new CanBeSavedMatcher<EOEnterpriseObject>();
	}

	public static Matcher<EOEnterpriseObject> cannotBeDeleted()
	{
		return not(canBeDeleted());
	}

	public static Matcher<EOEnterpriseObject> cannotBeDeletedBecause(final String message)
	{
		return not(new CanBeDeletedMatcher<EOEnterpriseObject>(message));
	}

	public static Matcher<EOEnterpriseObject> cannotBeSaved()
	{
		return not(canBeSaved());
	}

	public static Matcher<EOEnterpriseObject> cannotBeSavedBecause(final String message)
	{
		return not(new CanBeSavedMatcher<EOEnterpriseObject>(message));
	}

	public static <T extends EOEnterpriseObject> void confirm(final T actual, final Matcher<T> matcher)
	{
		confirmImplementation(actual, matcher);
	}

	public static <T extends EOEditingContext> void confirm(final T actual, final Matcher<T> matcher)
	{
		confirmImplementation(actual, matcher);
	}

	private static <T> void confirmImplementation(final T actual, final Matcher<T> matcher)
	{
		if(matcher.matches(actual))
		{
			return;
		}

		Description description = new StringDescription();

		description.appendText("\nExpected: ");
		description.appendDescriptionOf(matcher);

		throw new AssertionError(description.toString());
	}

	public static Matcher<EOEditingContext> doNotSaveChanges()
	{
		return not(saveChanges());
	}

	public static Matcher<EOEditingContext> doNotSaveChangesBecause(final String message)
	{
		return not(new SaveChangesMatcher<EOEditingContext>(message));
	}

	public static Matcher<EOEnterpriseObject> hasBeenDeleted()
	{
		return new HasBeenDeletedMatcher<EOEnterpriseObject>();
	}

	public static Matcher<EOEnterpriseObject> hasBeenSaved()
	{
		return new HasBeenSavedMatcher<EOEnterpriseObject>();
	}

	public static Matcher<EOEnterpriseObject> hasNotBeenDeleted()
	{
		return not(hasBeenDeleted());
	}

	public static Matcher<EOEnterpriseObject> hasNotBeenSaved()
	{
		return not(hasBeenSaved());
	}

	public static Matcher<EOEditingContext> saveChanges()
	{
		return new SaveChangesMatcher<EOEditingContext>();
	}

	/**
	 * <code>EOAssert</code> is not intended to be instantiated.
	 */
	private EOAssert()
	{
	}
}
