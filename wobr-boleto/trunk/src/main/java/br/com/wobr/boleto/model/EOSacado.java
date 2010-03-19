package br.com.wobr.boleto.model;

import br.com.caelum.stella.boleto.Sacado;

public class EOSacado extends _EOSacado
{
	public Sacado toStellaSacado()
	{
		Sacado sacado = Sacado.newSacado();

		sacado.withBairro( bairro() );
		sacado.withCep( cep() );
		sacado.withCidade( cidade() );
		sacado.withCpf( cpf() );
		sacado.withEndereco( endereco() );
		sacado.withNome( nome() );
		sacado.withUf( uf() );

		return sacado;
	}
}
