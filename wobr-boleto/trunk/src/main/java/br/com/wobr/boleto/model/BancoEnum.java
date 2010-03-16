package br.com.wobr.boleto.model;

import br.com.caelum.stella.boleto.Banco;
import br.com.caelum.stella.boleto.bancos.BancoDoBrasil;

public enum BancoEnum
{
	BANCO_DO_BRASIL( new BancoDoBrasil() ), BRADESCO( null ), CAIXA_ECONOMICA( null ), ITAU( null ), OUTRO( null ), REAL( null );

	private final Banco banco;

	private BancoEnum( final Banco banco )
	{
		this.banco = banco;
	}

	public Banco toStellaBanco()
	{
		return banco;
	}
}
