package br.com.wobr.boleto.model;

import java.util.Calendar;

import br.com.caelum.stella.boleto.Datas;

import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSTimestamp;

/**
 * @author <a href="mailto:hprange@gmail.com">Henrique Prange</a>
 */
public class Boleto extends _Boleto
{
	public static Boleto createBoleto(EOEditingContext editingContext)
	{
		return (Boleto) EOUtilities.createAndInsertInstance(editingContext, ENTITY_NAME);
	}

	@Override
	public void awakeFromInsertion(EOEditingContext ec)
	{
		super.awakeFromInsertion(ec);

		if(aceite() == null)
		{
			setAceite(Boolean.FALSE);
		}
	}

	private Calendar convertDate(NSTimestamp date)
	{
		if(date == null)
		{
			return null;
		}

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(date);

		return calendar;
	}

	@SuppressWarnings("unchecked")
	private String[] stringArrayDe(NSArray<? extends AbstractInformacao> informacoes)
	{
		return ((NSArray<String>) informacoes.valueForKeyPath(AbstractInformacao.VALOR_KEY)).toArray(new String[] {});
	}

	public br.com.caelum.stella.boleto.Boleto toStellaBoleto()
	{
		br.com.caelum.stella.boleto.Boleto boleto = br.com.caelum.stella.boleto.Boleto.newBoleto();

		Datas datas = Datas.newDatas();

		if(dataDocumento() != null)
		{
			datas.withDocumento(convertDate(dataDocumento()));
		}

		if(dataProcessamento() != null)
		{
			datas.withProcessamento(convertDate(dataProcessamento()));
		}

		if(dataVencimento() != null)
		{
			datas.withVencimento(convertDate(dataVencimento()));
		}

		boleto.withDatas(datas);
		boleto.withDescricoes(stringArrayDe(descricoes()));
		boleto.withInstrucoes(stringArrayDe(instrucoes()));
		boleto.withLocaisDePagamento(stringArrayDe(locaisPagamento()));

		return boleto;
	}
}
