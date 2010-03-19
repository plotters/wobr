package br.com.wobr.boleto.model;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Rule;
import org.junit.Test;

import br.com.wobr.unittest.TemporaryEditingContextProvider;

public class TestAbstractInformacao
{
	@Rule
	public final TemporaryEditingContextProvider provider = new TemporaryEditingContextProvider( "Boleto" );

	@Test
	public void inicializacaoDescricao() throws Exception
	{
		EODescricao descricao = EODescricao.createEODescricao( provider.editingContext(), null );

		assertThat( descricao.tipo(), is( "1" ) );
	}

	@Test
	public void inicializacaoInstrucao() throws Exception
	{
		EOInstrucao descricao = EOInstrucao.createEOInstrucao( provider.editingContext(), null );

		assertThat( descricao.tipo(), is( "2" ) );
	}

	@Test
	public void inicializacaoLocalPagamento() throws Exception
	{
		EOLocalPagamento localPagamento = EOLocalPagamento.createEOLocalPagamento( provider.editingContext(), null );

		assertThat( localPagamento.tipo(), is( "3" ) );
	}
}
