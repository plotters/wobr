/*
 * Copyright 2008 Wen Tao. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.com.wobr.inject.chain.internal;

import br.com.wobr.inject.chain.api.DecoratorFactory;
import br.com.wobr.inject.chain.api.Next;

import com.google.inject.AbstractModule;
import com.google.inject.Injector;
import com.google.inject.Provider;

final class ClassDecoratorFactory implements DecoratorFactory
{

	private final Class type;

	private final Class decoratorClass;

	private Provider<Injector> injectorProvider;

	public ClassDecoratorFactory(Provider<Injector> injectorProvider, Class type, Class decoratorClass)
	{
		this.injectorProvider = injectorProvider;
		this.type = type;
		this.decoratorClass = decoratorClass;
	}

	public Object decorate(final Object next)
	{
		Injector childInjector = injectorProvider.get().createChildInjector(new AbstractModule()
		{
			protected void configure()
			{
				bind(type).annotatedWith(Next.class).toInstance(next);
			}
		});
		return childInjector.getInstance(decoratorClass);
	}
}
