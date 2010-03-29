package br.com.wobr.unittest;

import org.hamcrest.Description;
import org.junit.internal.matchers.TypeSafeMatcher;

import com.webobjects.eocontrol.EOEnterpriseObject;

public class HasBeenDeletedMatcher<T extends EOEnterpriseObject> extends TypeSafeMatcher<T>
{
	public void describeTo( final Description arg0 )
	{

	}

	@Override
	public boolean matchesSafely( final T item )
	{
		return false;
	}
}
