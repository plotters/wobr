package br.com.wobr.unittest.matchers;

import org.hamcrest.Description;

import com.webobjects.eocontrol.EOEnterpriseObject;

/**
 * @author <a href="mailto:hprange@gmail.com">Henrique Prange</a>
 * @param <T> a kind of <code>EOEnterpriseObject</code>
 */
public class CanBeSavedMatcher<T extends EOEnterpriseObject> extends AbstractEnhancedTypeSafeMatcher<T>
{
	public CanBeSavedMatcher()
	{
		super();
	}

	public CanBeSavedMatcher( final String message )
	{
		super( message );
	}

	public void describeTo( final Description description )
	{
		if( message == null )
		{
			description.appendText( "valid for save enterprise object" );
			description.appendText( "\n     but got: " );

			if( exception == null )
			{
				description.appendText( "a valid one" );

				return;
			}

			description.appendText( exception.getClass().getName() );
			description.appendText( ": " );
			description.appendValue( exception.getMessage() );

			return;
		}

		description.appendText( "expecting exception other than \"" );
		description.appendText( message );
		description.appendText( "\"\n     but got: " );

		if( exception == null )
		{
			description.appendText( "no validation exception" );

			return;
		}

		description.appendText( exception.getClass().getName() );
		description.appendText( ": \"" );
		description.appendText( exception.getMessage() );
		description.appendText( "\"" );
	}

	@Override
	protected void matchesWithPossibleException( final T eo )
	{
		eo.validateForSave();
	}
}
