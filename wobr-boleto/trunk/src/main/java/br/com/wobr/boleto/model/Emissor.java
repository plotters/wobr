package br.com.wobr.boleto.model;

import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.EOEditingContext;

/**
 * @author <a href="mailto:hprange@gmail.com">Henrique Prange</a>
 */
public class Emissor extends _Emissor
{

	public static Emissor createEmissor( final EOEditingContext editingContext )
	{
		return (Emissor) EOUtilities.createAndInsertInstance( editingContext, ENTITY_NAME );
	}

	public br.com.caelum.stella.boleto.Emissor toStellaEmissor()
	{
		br.com.caelum.stella.boleto.Emissor emissor = br.com.caelum.stella.boleto.Emissor.newEmissor();

		if( agencia() != null )
		{
			emissor.withAgencia( agencia() );
		}

		if( carteira() != null )
		{
			emissor.withCarteira( carteira() );
		}

		emissor.withCedente( cedente() );

		if( codigoFornecidoPelaAgencia() != null )
		{
			emissor.withCodFornecidoPelaAgencia( codigoFornecidoPelaAgencia() );
		}

		if( codigoOperacao() != null )
		{
			emissor.withCodOperacao( codigoOperacao() );
		}

		if( contaCorrente() != null )
		{
			emissor.withContaCorrente( contaCorrente() );
		}

		if( digitoVerificadorAgencia() != null )
		{
			emissor.withDvAgencia( digitoVerificadorAgencia().charAt( 0 ) );
		}

		if( digitoVerificadorContaCorrente() != null )
		{
			emissor.withDvContaCorrente( digitoVerificadorContaCorrente().charAt( 0 ) );
		}

		if( digitoVerificadorNossoNumero() != null )
		{
			emissor.withDvNossoNumero( digitoVerificadorNossoNumero().charAt( 0 ) );
		}

		if( nossoNumero() != null )
		{
			emissor.withNossoNumero( nossoNumero() );
		}

		if( numeroConvenio() != null )
		{
			emissor.withNumConvenio( numeroConvenio() );
		}

		return emissor;
	}
}
