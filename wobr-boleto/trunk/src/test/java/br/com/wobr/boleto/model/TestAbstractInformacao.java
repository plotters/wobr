package br.com.wobr.boleto.model;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Rule;
import org.junit.Test;

import br.com.wobr.unittest.rules.TemporaryEnterpriseObjectProvider;

public class TestAbstractInformacao
{
	@Rule
	public final TemporaryEnterpriseObjectProvider provider = new TemporaryEnterpriseObjectProvider( "Boleto" );

	@Test
	public void inicializacaoDescricao() throws Exception
	{
		EODescricao descricao = provider.createInstance( EODescricao.class );

		assertThat( descricao.tipo(), is( "1" ) );
	}

	@Test
	public void inicializacaoInstrucao() throws Exception
	{
		EOInstrucao descricao = provider.createInstance( EOInstrucao.class );

		assertThat( descricao.tipo(), is( "2" ) );
	}

	@Test
	public void inicializacaoLocalPagamento() throws Exception
	{
		EOLocalPagamento localPagamento = provider.createInstance( EOLocalPagamento.class );

		assertThat( localPagamento.tipo(), is( "3" ) );
	}
}
