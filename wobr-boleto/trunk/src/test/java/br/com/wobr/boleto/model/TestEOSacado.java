package br.com.wobr.boleto.model;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Rule;
import org.junit.Test;

import br.com.wobr.unittest.TemporaryEditingContextProvider;

public class TestEOSacado
{
	@Rule
	public final TemporaryEditingContextProvider editingContextProvider = new TemporaryEditingContextProvider( "Boleto" );

	@Test
	public void converteSacadoParaSacadoStella() throws Exception
	{
		EOSacado sacado = EOSacado.createEOSacado( editingContextProvider.editingContext() );

		sacado.setBairro( "Vila Perdida" );
		sacado.setCep( "12345678" );
		sacado.setCidade( "Itaquaquecetuba" );
		sacado.setCpf( "12345678909" );
		sacado.setEndereco( "Rua sem sa\u00edda" );
		sacado.setNome( "Seu Madruga" );
		sacado.setUf( "SP" );

		br.com.caelum.stella.boleto.Sacado result = sacado.toStellaSacado();

		assertThat( result.getBairro(), is( "Vila Perdida" ) );
		assertThat( result.getCep(), is( "12345678" ) );
		assertThat( result.getCidade(), is( "Itaquaquecetuba" ) );
		assertThat( result.getCpf(), is( "12345678909" ) );
		assertThat( result.getEndereco(), is( "Rua sem sa\u00edda" ) );
		assertThat( result.getNome(), is( "Seu Madruga" ) );
		assertThat( result.getUf(), is( "SP" ) );
	}

	@Test
	public void converteSacadoVazioParaSacadoStella() throws Exception
	{
		EOSacado sacado = EOSacado.createEOSacado( editingContextProvider.editingContext() );

		br.com.caelum.stella.boleto.Sacado result = sacado.toStellaSacado();

		assertThat( result, notNullValue() );
	}
}