package br.com.wobr.guice;

import com.google.inject.Injector;

/**
 * @author <a href="mailto:hprange@gmail.com">Henrique Prange</a>
 */
public class InjectorFactory
{
	public static Injector create()
	{
		return InjectableApplication.application().injector();
	}
}
