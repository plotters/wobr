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

import com.google.inject.*;
import com.google.inject.util.Types;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public final class ChainBuilder
{

	private final static Set<ChainBuilder> builders = new HashSet<ChainBuilder>();

	private final Class type;

	private final DecoratorFactories decoratorFactories = new DecoratorFactories();

	private final ClassDecoratorFactories classDecoratorFactories;

	public ChainBuilder(Provider<Injector> injectorProvider, Class type)
	{
		this.type = type;
		classDecoratorFactories = new ClassDecoratorFactories(injectorProvider, type);
		builders.add(this);
	}

	public final void addDecoratorFactories(DecoratorFactory firstDecoratorFactory, DecoratorFactory[] decoratorFactories)
	{
		this.decoratorFactories.add(firstDecoratorFactory, Arrays.asList(decoratorFactories));
	}

	public final void setImplDecoratorFactory(DecoratorFactory implDecoratorFactory)
	{
		decoratorFactories.setHead(implDecoratorFactory);
	}

	public final DecoratorFactory toFactory(Class decoratorClass)
	{
		return classDecoratorFactories.get(decoratorClass);
	}

	public final DecoratorFactory[] toFactories(Class[] decoratorClasses)
	{
		return classDecoratorFactories.get(decoratorClasses);
	}

	public final void bindChain(Binder binder)
	{
		final Provider implProvider = binder.getProvider(Key.get(type, Implementation.class));
		binder.bind(type).toProvider(new Provider()
		{
			public Object get()
			{
				return decoratorFactories.decorate(implProvider.get());
			}
		}).in(Scopes.SINGLETON);
		binder.requestInjection(new CompletenessAssertion());
	}

	public final void bindDecoratorFactory(Binder binder)
	{
		TypeLiteral decoratorFactoryType = TypeLiteral.get(Types.newParameterizedType(DecoratorFactory.class, type));
		binder.bind(decoratorFactoryType).toInstance(decoratorFactories);
		binder.requestInjection(new CompletenessAssertion());
	}

	private class CompletenessAssertion
	{

		@Inject
		public void assertCompleteChain()
		{
			decoratorFactories.assertCompleteChain();
		}
	}
}
