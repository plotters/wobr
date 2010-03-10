package br.com.wobr.inject;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.Stage;
import com.webobjects.appserver.WOApplication;

import er.extensions.appserver.ERXApplication;

/**
 * @author <a href="mailto:hprange@gmail.com">Henrique Prange</a>
 */
public class InjectableApplication extends ERXApplication
{
	public static InjectableApplication application()
	{
		return (InjectableApplication) WOApplication.application();
	}

	private final Injector injector;

	public InjectableApplication()
	{
		super();

		injector = createInjector();
	}

	/**
	 * @return
	 */
	protected Injector createInjector()
	{
		return Guice.createInjector(injectorStage(), injectorModules());
	}

	/**
	 * @return
	 */
	public Injector injector()
	{
		return injector;
	}

	/**
	 * @return
	 */
	protected Module[] injectorModules()
	{
		return new Module[] {};
	}

	/**
	 * @return
	 */
	protected Stage injectorStage()
	{
		return isDevelopmentMode() ? Stage.DEVELOPMENT : Stage.PRODUCTION;
	}
}
