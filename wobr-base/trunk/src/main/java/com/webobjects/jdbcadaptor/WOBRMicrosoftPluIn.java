package com.webobjects.jdbcadaptor;

import com.webobjects.eoaccess.EOAdaptor;
import com.webobjects.eoaccess.EOAttribute;
import com.webobjects.eoaccess.EOSQLExpression;
import com.webobjects.eoaccess.synchronization.EOSchemaGenerationOptions;
import com.webobjects.eoaccess.synchronization.EOSchemaSynchronizationFactory;
import com.webobjects.foundation.NSArray;

public class WOBRMicrosoftPluIn extends MicrosoftPlugIn
{
	public static class DOitMicrosoftSynchronizationFactory extends MicrosoftSynchronizationFactory
	{
		public DOitMicrosoftSynchronizationFactory( final EOAdaptor adaptor )
		{
			super( adaptor );
		}

		@Override
		public NSArray<EOSQLExpression> statementsToInsertColumnForAttribute( final EOAttribute attribute, final EOSchemaGenerationOptions options )
		{
			String columnCreationClause = _columnCreationClauseForAttribute( attribute );

			return new NSArray( _expressionForString( "alter table " + formatTableName( attribute.entity().externalName() ) + " add " + columnCreationClause + "" ) );
		}
	}

	public WOBRMicrosoftPluIn( final JDBCAdaptor adaptor )
	{
		super( adaptor );
	}

	@Override
	public EOSchemaSynchronizationFactory createSchemaSynchronizationFactory()
	{
		return new DOitMicrosoftSynchronizationFactory( adaptor() );
	}
}
