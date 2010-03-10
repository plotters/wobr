package br.com.wobr.inject;

import com.google.inject.Injector;
import com.webobjects.appserver.WOContext;

import er.extensions.components.ERXComponent;

/**
 * @author <a href="mailto:hprange@gmail.com">Henrique Prange</a>
 */
public class InjectableComponent extends ERXComponent
{
	private static final long serialVersionUID = 1L;

	public InjectableComponent(WOContext context)
	{
		super(context);

		injector().injectMembers(this);
	}

	/**
	 * @return
	 */
	protected Injector injector()
	{
		return InjectorFactory.create();
	}
}
