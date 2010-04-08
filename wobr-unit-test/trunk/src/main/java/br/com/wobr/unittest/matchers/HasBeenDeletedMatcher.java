package br.com.wobr.unittest.matchers;

import org.hamcrest.Description;
import org.junit.internal.matchers.TypeSafeMatcher;

import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.eocontrol.EOEnterpriseObject;

/**
 * The HasBeenDeletedMatcher expect the common use of enterprise object that are
 * added to the editing context right after their creation.
 * 
 * @author <a href="mailto:hprange@gmail.com">Henrique Prange</a>
 * @param <T>
 */
public class HasBeenDeletedMatcher<T extends EOEnterpriseObject> extends TypeSafeMatcher<T>
{
	private String status;

	public void describeTo( final Description description )
	{
		description.appendText( String.format( "deleted object\n     but got: an %s object", status ) );
	}

	private boolean isDeletedEO( final T eo )
	{
		EOEditingContext editingContext = eo.editingContext();

		if( editingContext == null )
		{
			return true;
		}

		return editingContext.deletedObjects().contains( eo );
	}

	@Override
	public boolean matchesSafely( final T eo )
	{
		boolean isDeleted = isDeletedEO( eo );

		status = isDeleted ? "deleted" : "active";

		return isDeleted;
	}
}
