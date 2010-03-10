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
package br.com.wobr.inject.chain;

import br.com.wobr.inject.chain.api.DecoratorFactory;
import br.com.wobr.inject.chain.internal.ChainBuilder;
import br.com.wobr.inject.chain.internal.ChainModule;
import br.com.wobr.inject.chain.internal.Implementation;

import com.google.inject.Key;
import com.google.inject.Provider;
import com.google.inject.TypeLiteral;
import com.google.inject.binder.AnnotatedBindingBuilder;

/**
 * Used to bind a impl which decorated with several decorators: impl <-
 * decorator1 <- ... <- decoratorN you can bind it by:
 * impl(xxx).decoratedWith(decorator1, ..., decoratorN); or you can:
 * impl(xxx).decoratedWith(decorator1); //in module 1
 * decorate(decorator1).with(decorator2, ..., decoratorN); // in module 2
 * 
 * @author taowen@gmail.com (Wen Tao)
 * @param <T>
 *            the decorating type
 */
public abstract class ChainOf<T> extends ChainModule<T>
{

	protected void configureBuilder()
	{
		builder().bindChain(binder());
	}

	protected final BindImplClause<T> implProvidedBy(Key<? extends Provider<? extends T>> providerKey)
	{
		bind().annotatedWith(Implementation.class).toProvider(providerKey);
		return bindImplClause();
	}

	protected final BindImplClause<T> implProvidedBy(Class<? extends Provider<? extends T>> providerClass)
	{
		bind().annotatedWith(Implementation.class).toProvider(providerClass);
		return bindImplClause();
	}

	protected final BindImplClause<T> implProvidedBy(Provider<? extends T> provider)
	{
		bind().annotatedWith(Implementation.class).toProvider(provider);
		return bindImplClause();
	}

	protected final BindImplClause<T> implInstance(T impl)
	{
		bind().annotatedWith(Implementation.class).toInstance(impl);
		return bindImplClause();
	}

	protected final BindImplClause<T> impl(Class<? extends T> implClass)
	{
		bind().annotatedWith(Implementation.class).to(implClass);
		return bindImplClause();
	}

	protected final BindImplClause<T> impl(TypeLiteral<? extends T> implTypeLiteral)
	{
		bind().annotatedWith(Implementation.class).to(implTypeLiteral);
		return bindImplClause();
	}

	protected final BindImplClause<T> impl(Key<? extends T> implKey)
	{
		bind().annotatedWith(Implementation.class).to(implKey);
		return bindImplClause();
	}

	protected static final class BindImplClause<T>
	{

		private final ChainBuilder builder;

		public BindImplClause(ChainBuilder builder)
		{
			this.builder = builder;
		}

		public final void decoratedWith(DecoratorFactory<T> implDecoratorFactory, DecoratorFactory<T>... decoratorFactories)
		{
			builder.setImplDecoratorFactory(implDecoratorFactory);
			builder.addDecoratorFactories(implDecoratorFactory, decoratorFactories);
		}

		public final void decoratedWith(Class<? extends T> implDecoratorClass, Class<? extends T>... decoratorClasses)
		{
			builder.setImplDecoratorFactory(builder.toFactory(implDecoratorClass));
			builder.addDecoratorFactories(builder.toFactory(implDecoratorClass), builder.toFactories(decoratorClasses));
		}
	}

	private AnnotatedBindingBuilder<T> bind()
	{
		return binder().bind(type);
	}

	private BindImplClause<T> bindImplClause()
	{
		return new BindImplClause<T>(builder());
	}
}
