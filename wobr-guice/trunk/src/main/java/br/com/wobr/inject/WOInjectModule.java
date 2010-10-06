package br.com.wobr.inject;

import com.google.inject.AbstractModule;

public class WOInjectModule extends AbstractModule
{

	@Override
	protected void configure()
	{
		bindScope(WOSessionScoped.class, new WOSessionScope());
	}

}
