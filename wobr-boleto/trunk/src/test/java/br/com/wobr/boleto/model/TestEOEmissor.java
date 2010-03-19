package br.com.wobr.boleto.model;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Rule;
import org.junit.Test;

import br.com.wobr.unittest.TemporaryEditingContextProvider;

/**
 * @author <a href="mailto:hprange@gmail.com">Henrique Prange</a>
 */
public class TestEOEmissor
{
	@Rule
	public final TemporaryEditingContextProvider editingContextProvider = new TemporaryEditingContextProvider( "Boleto" );

	@Test
	public void converteEmissorParaEmissorStella() throws Exception
	{
		EOEmissor emissor = EOEmissor.createEOEmissor( editingContextProvider.editingContext() );

		emissor.setAgencia( 1234 );
		emissor.setCarteira( 175 );
		emissor.setCedente( "Teste" );
		emissor.setCodigoFornecidoPelaAgencia( 10 );
		emissor.setCodigoOperacao( 50 );
		emissor.setContaCorrente( 45678L );
		emissor.setDigitoVerificadorAgencia( "6" );
		emissor.setDigitoVerificadorContaCorrente( "7" );
		emissor.setDigitoVerificadorNossoNumero( "8" );
		emissor.setNossoNumero( 22222L );
		emissor.setNumeroConvenio( 33333L );

		br.com.caelum.stella.boleto.Emissor result = emissor.toStellaEmissor();

		assertThat( result.getAgencia(), is( 1234 ) );
		assertThat( result.getCarteira(), is( 175 ) );
		assertThat( result.getCedente(), is( "Teste" ) );
		assertThat( result.getCodFornecidoPelaAgencia(), is( 10 ) );
		assertThat( result.getCodOperacao(), is( 50 ) );
		assertThat( result.getContaCorrente(), is( 45678L ) );
		assertThat( result.getDvAgencia(), is( '6' ) );
		assertThat( result.getDvContaCorrente(), is( '7' ) );
		assertThat( result.getDvNossoNumero(), is( '8' ) );
		assertThat( result.getNossoNumero(), is( 22222L ) );
		assertThat( result.getNumConvenio(), is( 33333L ) );
	}

	@Test
	public void converteEmissorVazioParaEmissorStella() throws Exception
	{
		EOEmissor emissor = EOEmissor.createEOEmissor( editingContextProvider.editingContext() );

		br.com.caelum.stella.boleto.Emissor result = emissor.toStellaEmissor();

		assertThat( result, notNullValue() );
	}
}
