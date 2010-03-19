package br.com.wobr.boleto.model;

import com.webobjects.eocontrol.EOEditingContext;

public class EODescricao extends _EODescricao
{
	@Override
	public void awakeFromInsertion( final EOEditingContext ec )
	{
		super.awakeFromInsertion( ec );

		if( tipo() == null )
		{
			setTipo( "1" );
		}
	}
}
