package br.com.wobr.unittest;

import org.hamcrest.Description;
import org.junit.internal.matchers.TypeSafeMatcher;

import com.webobjects.eocontrol.EOEnterpriseObject;

/**
 * @author <a href="mailto:hprange@gmail.com">Henrique Prange</a>
 * @param <T>
 */
public class CanBeDeletedMatcher<T extends EOEnterpriseObject> extends TypeSafeMatcher<T>
{
	public void describeTo( final Description description )
	{

	}

	@Override
	public boolean matchesSafely( final T eo )
	{
		return false;
	}
}
