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
import br.com.wobr.inject.composite.CompositeModule;

import com.google.inject.Injector;

public abstract class ChainModule<T> extends CompositeModule<T, ChainBuilder>
{

	public ChainModule()
	{
		super(ChainBuilder.class);
	}

	protected ChainBuilder createBuilder()
	{
		return new ChainBuilder(binder().getProvider(Injector.class), type);
	}

	protected final DecorateClause<T> decorate(Class<? extends T> previousDecoratorClass)
	{
		ChainBuilder builder = builder();
		return new DecorateClause<T>(builder, builder.toFactory(previousDecoratorClass));
	}

	protected final DecorateClause<T> decorate(DecoratorFactory<T> previousDecoratorFactory)
	{
		return new DecorateClause<T>(builder(), previousDecoratorFactory);
	}

	protected static final class DecorateClause<T>
	{

		private ChainBuilder builder;

		private DecoratorFactory previousDecoratorFactory;

		public DecorateClause(ChainBuilder builder, DecoratorFactory previousDecoratorFactory)
		{
			this.builder = builder;
			this.previousDecoratorFactory = previousDecoratorFactory;
		}

		public final void with(Class<? extends T>... decoratorClasses)
		{
			builder.addDecoratorFactories(previousDecoratorFactory, builder.toFactories(decoratorClasses));
		}

		public final void with(DecoratorFactory<T>... decoratorFactories)
		{
			builder.addDecoratorFactories(previousDecoratorFactory, decoratorFactories);
		}
	}
}
