package br.com.wobr.unittest;

import org.hamcrest.Description;
import org.junit.internal.matchers.TypeSafeMatcher;

import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.eocontrol.EOEnterpriseObject;
import com.webobjects.eocontrol.EOGlobalID;

/**
 * @author <a href="mailto:hprange@gmail.com">Henrique Prange</a>
 * @param <T>
 */
public class HasBeenSavedMatcher<T extends EOEnterpriseObject> extends TypeSafeMatcher<T>
{
	public void describeTo( final Description description )
	{

	}

	@Override
	public boolean matchesSafely( final T enterpriseObject )
	{
		EOEditingContext editingContext = enterpriseObject.editingContext();

		EOGlobalID globalId = editingContext.globalIDForObject( enterpriseObject );

		return !( globalId == null || globalId.isTemporary() );
	}
}
