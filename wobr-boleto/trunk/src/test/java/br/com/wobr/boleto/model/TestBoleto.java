package br.com.wobr.boleto.model;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItems;

import java.math.BigDecimal;
import java.util.Calendar;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import br.com.wobr.unittest.CanBeSavedMatcher;
import br.com.wobr.unittest.TemporaryEditingContextProvider;

import com.webobjects.foundation.NSTimestamp;

/**
 * @author <a href="mailto:hprange@gmail.com">Henrique Prange</a>
 */
public class TestBoleto
{
	private Boleto boleto;

	@Rule
	public final TemporaryEditingContextProvider editingContextProvider = new TemporaryEditingContextProvider( "Boleto" );

	@Test
	public void converteCamposBoletoParaStellaBoleto() throws Exception
	{
		boleto.setAceite( true );
		boleto.setBanco( BancoEnum.BANCO_DO_BRASIL );
		boleto.setEspecieDocumento( "especie" );
		boleto.setNumeroDocumento( "1234" );
		boleto.setQuantidadeMoeda( BigDecimal.ONE );
		boleto.setValor( new BigDecimal( "15.76" ) );
		boleto.setValorMoeda( new BigDecimal( "7.99" ) );

		br.com.caelum.stella.boleto.Boleto result = boleto.toStellaBoleto();

		assertThat( result.getAceite(), is( true ) );
		assertThat( result.getBanco(), is( BancoEnum.BANCO_DO_BRASIL.toStellaBanco() ) );
		assertThat( result.getEspecieDocumento(), is( "especie" ) );
		assertThat( result.getNoDocumento(), is( "1234" ) );
		assertThat( result.getQtdMoeda(), is( BigDecimal.ONE ) );
		assertThat( result.getValorBoleto(), is( new BigDecimal( "15.76" ) ) );
		assertThat( result.getValorMoeda(), is( new BigDecimal( "7.99" ) ) );
	}

	@Test
	public void converteDatasParaStellaBoleto() throws Exception
	{
		boleto.setDataDocumento( newNSTimestamp( 1, 5, 2008 ) );
		boleto.setDataProcessamento( newNSTimestamp( 2, 5, 2008 ) );
		boleto.setDataVencimento( newNSTimestamp( 3, 5, 2008 ) );

		br.com.caelum.stella.boleto.Boleto result = boleto.toStellaBoleto();

		assertThat( result.getDatas().getDocumento(), is( newDate( 1, 5, 2008 ) ) );
		assertThat( result.getDatas().getProcessamento(), is( newDate( 2, 5, 2008 ) ) );
		assertThat( result.getDatas().getVencimento(), is( newDate( 3, 5, 2008 ) ) );
	}

	@Test
	public void converteDescricoesParaStellaBoleto() throws Exception
	{
		boleto.addToDescricoesRelationship( Descricao.createDescricao( editingContextProvider.editingContext(), "descricao 1" ) );
		boleto.addToDescricoesRelationship( Descricao.createDescricao( editingContextProvider.editingContext(), "descricao 2" ) );
		boleto.addToDescricoesRelationship( Descricao.createDescricao( editingContextProvider.editingContext(), "descricao 3" ) );
		boleto.addToDescricoesRelationship( Descricao.createDescricao( editingContextProvider.editingContext(), "descricao 4" ) );
		boleto.addToDescricoesRelationship( Descricao.createDescricao( editingContextProvider.editingContext(), "descricao 5" ) );

		br.com.caelum.stella.boleto.Boleto result = boleto.toStellaBoleto();

		assertThat( result.getDescricoes(), hasItems( "descricao 1", "descricao 2", "descricao 3", "descricao 4", "descricao 5" ) );
	}

	@Test
	public void converteEmissorNuloToStellaBoleto() throws Exception
	{
	}

	@Test
	public void converteEmissorParaStellaBoleto() throws Exception
	{

	}

	@Test
	public void converteInstrucoesParaStellaBoleto() throws Exception
	{
		boleto.addToInstrucoesRelationship( Instrucao.createInstrucao( editingContextProvider.editingContext(), "instrucao 1" ) );
		boleto.addToInstrucoesRelationship( Instrucao.createInstrucao( editingContextProvider.editingContext(), "instrucao 2" ) );
		boleto.addToInstrucoesRelationship( Instrucao.createInstrucao( editingContextProvider.editingContext(), "instrucao 3" ) );
		boleto.addToInstrucoesRelationship( Instrucao.createInstrucao( editingContextProvider.editingContext(), "instrucao 4" ) );
		boleto.addToInstrucoesRelationship( Instrucao.createInstrucao( editingContextProvider.editingContext(), "instrucao 5" ) );

		br.com.caelum.stella.boleto.Boleto result = boleto.toStellaBoleto();

		assertThat( result.getInstrucoes(), hasItems( "instrucao 1", "instrucao 2", "instrucao 3", "instrucao 4", "instrucao 5" ) );
	}

	@Test
	public void converteLocaisDePagamentoParaStellaBoleto() throws Exception
	{
		boleto.addToLocaisPagamentoRelationship( LocalPagamento.createLocalPagamento( editingContextProvider.editingContext(), "local 1" ) );
		boleto.addToLocaisPagamentoRelationship( LocalPagamento.createLocalPagamento( editingContextProvider.editingContext(), "local 2" ) );

		br.com.caelum.stella.boleto.Boleto result = boleto.toStellaBoleto();

		assertThat( result.getLocaisDePagamento(), hasItems( "local 1", "local 2" ) );
	}

	@Test
	public void inicializacaoBoleto() throws Exception
	{
		Boleto boleto = Boleto.createBoleto( editingContextProvider.editingContext() );

		assertThat( boleto.aceite(), is( false ) );
	}

	@Test
	public void naoPodeTerMaisDeDoisLocaisDePagamento() throws Exception
	{
		populateBoletoWithSampleData();

		for( int i = 0; i < 3; i++ )
		{
			boleto.addToLocaisPagamentoRelationship( LocalPagamento.createLocalPagamento( editingContextProvider.editingContext(), "local " + i ) );
		}

		CanBeSavedMatcher.verify( boleto, CanBeSavedMatcher.cannotBeSavedBecauseOf( "O boleto pode conter no m\u00e1ximo 2 locais de pagamento" ) );
	}

	private Calendar newDate( final int day, final int month, final int year )
	{
		Calendar calendar = Calendar.getInstance();

		calendar.setTime( new LocalDate( year, month, day ).toDateMidnight().toDate() );

		return calendar;
	}

	private NSTimestamp newNSTimestamp( final int day, final int month, final int year )
	{
		return new NSTimestamp( new LocalDate( year, month, day ).toDateMidnight().toDate() );
	}

	private void populateBoletoWithSampleData()
	{
		boleto.setBanco( BancoEnum.ITAU );
		boleto.setDataVencimento( new NSTimestamp() );
		boleto.setNumeroDocumento( "1234" );
		boleto.setValor( BigDecimal.TEN );
	}

	@Before
	public void setup()
	{
		boleto = Boleto.createBoleto( editingContextProvider.editingContext() );
	}
}
