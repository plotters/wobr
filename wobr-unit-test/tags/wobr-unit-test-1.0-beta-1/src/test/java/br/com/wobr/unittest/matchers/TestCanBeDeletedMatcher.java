package br.com.wobr.unittest.matchers;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.hamcrest.StringDescription;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.webobjects.eocontrol.EOEnterpriseObject;
import com.webobjects.foundation.NSValidation;

/**
 * @author <a href="mailto:hprange@gmail.com">Henrique Prange</a>
 */
@RunWith(MockitoJUnitRunner.class)
public class TestCanBeDeletedMatcher
{
	@Mock
	private EOEnterpriseObject mockObject;

	private CanBeDeletedMatcher<EOEnterpriseObject> matcher;

	private StringDescription mockDescription;

	@Test
	public void descriptionForCanBeDeletedFailure() throws Exception
	{
		Mockito.doThrow(new NSValidation.ValidationException("error")).when(mockObject).validateForDelete();

		matcher.matchesSafely(mockObject);
		matcher.describeTo(mockDescription);

		assertThat(mockDescription.toString(), is("valid for delete enterprise object\n     but got: com.webobjects.foundation.NSValidation$ValidationException: \"error\""));
	}

	@Test
	public void descriptionForCanBeDeletedSuccess() throws Exception
	{
		matcher.describeTo(mockDescription);

		assertThat(mockDescription.toString(), is("valid for delete enterprise object\n     but got: a valid one"));
	}

	@Test
	public void descriptionForCanBeDeletedWithMessageFailure() throws Exception
	{
		matcher = new CanBeDeletedMatcher<EOEnterpriseObject>("An expected error");

		Mockito.doThrow(new NSValidation.ValidationException("An unexpectedError")).when(mockObject).validateForDelete();

		matcher.matchesSafely(mockObject);
		matcher.describeTo(mockDescription);

		assertThat(mockDescription.toString(), is("expecting exception other than \"An expected error\"\n     but got: com.webobjects.foundation.NSValidation$ValidationException: \"An unexpectedError\""));
	}

	@Test
	public void descriptionForCanBeDeletedWithMessageSuccess() throws Exception
	{
		matcher = new CanBeDeletedMatcher<EOEnterpriseObject>("An expected error");

		matcher.describeTo(mockDescription);

		assertThat(mockDescription.toString(), is("expecting exception other than \"An expected error\"\n     but got: no validation exception"));
	}

	@Test
	public void matchesCanBeDeleted() throws Exception
	{
		Mockito.doNothing().when(mockObject).validateForDelete();

		boolean result = matcher.matchesSafely(mockObject);

		assertThat(result, is(true));
	}

	@Test
	public void matchesCannotBeDeleted() throws Exception
	{
		Mockito.doThrow(new NSValidation.ValidationException("error")).when(mockObject).validateForDelete();

		boolean result = matcher.matchesSafely(mockObject);

		assertThat(result, is(false));
	}

	@Test
	public void matchesCannotBeDeletedWithNoMatchingMessage() throws Exception
	{
		matcher = new CanBeDeletedMatcher<EOEnterpriseObject>("another error");

		Mockito.doThrow(new NSValidation.ValidationException("error")).when(mockObject).validateForDelete();

		boolean result = matcher.matchesSafely(mockObject);

		assertThat(result, is(true));
	}

	@Test
	public void matchesCannotBeSavedWithMatchingMessage() throws Exception
	{
		matcher = new CanBeDeletedMatcher<EOEnterpriseObject>("error");

		Mockito.doThrow(new NSValidation.ValidationException("error")).when(mockObject).validateForDelete();

		boolean result = matcher.matchesSafely(mockObject);

		assertThat(result, is(false));
	}

	@Before
	public void setup()
	{
		matcher = new CanBeDeletedMatcher<EOEnterpriseObject>();

		mockDescription = new StringDescription();
	}
}
