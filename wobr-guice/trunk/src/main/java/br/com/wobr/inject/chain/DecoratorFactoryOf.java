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

/**
 * Used to bind a DecoratorFactory of T which its impl unknown yet: decorator1
 * <- ... <- decoratorN you can bind it by: implDecoratedWith(decorator1, ...,
 * decoratorN); or you can: implDecoratedWith(decorator1); //in module 1
 * decorate(decorator1).with(decorator2, ..., decoratorN); // in module 2
 * 
 * @author taowen@gmail.com (Wen Tao)
 * @param <T>
 *            the decorating type
 */
public abstract class DecoratorFactoryOf<T> extends ChainModule<T>
{

	protected void configureBuilder()
	{
		builder().bindDecoratorFactory(binder());
	}

	protected final void implDecoratedWith(Class<? extends T> implDecoratorClass, Class<? extends T>... decoratorClasses)
	{
		ChainBuilder builder = builder();
		DecoratorFactory firstDecoratorFactory = builder.toFactory(implDecoratorClass);
		builder.setImplDecoratorFactory(firstDecoratorFactory);
		builder.addDecoratorFactories(firstDecoratorFactory, builder.toFactories(decoratorClasses));
	}

	protected final void implDecoratedWith(DecoratorFactory<T> implDecoratorFactory, DecoratorFactory<T>... decoratorFactories)
	{
		ChainBuilder builder = builder();
		builder.setImplDecoratorFactory(implDecoratorFactory);
		builder.addDecoratorFactories(implDecoratorFactory, decoratorFactories);
	}
}
