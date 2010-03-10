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
package br.com.wobr.inject.set;

import br.com.wobr.inject.composite.CompositeModule;
import br.com.wobr.inject.composite.DefaultProviderFactory;
import br.com.wobr.inject.set.internal.SetBuilder;

import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.Provider;
import com.google.inject.TypeLiteral;
import com.google.inject.util.Types;

/**
 * Used to bind a Set of T in multiple modules
 * 
 * @author taowen@gmail.com (Wen Tao)
 * @param <T>
 *            the element type
 */
public abstract class SetOf<T> extends CompositeModule<T, SetBuilder>
{

	public SetOf()
	{
		super(SetBuilder.class);
	}

	protected SetBuilder createBuilder()
	{
		Provider<Injector> injectorProvider = binder().getProvider(Injector.class);
		DefaultProviderFactory providerFactory = new DefaultProviderFactory(injectorProvider);
		return new SetBuilder(providerFactory);
	}

	protected void configureBuilder()
	{
		binder().bind(TypeLiteral.get(Types.setOf(type))).toProvider(builder());
	}

	protected void add(Class<? extends T>... classes)
	{
		for(Class<? extends T> clazz : classes)
		{
			builder().add(builder().providedBy(clazz));
		}
	}

	protected void add(Key<? extends T>... keys)
	{
		for(Key<? extends T> key : keys)
		{
			builder().add(builder().providedBy(key));
		}
	}

	protected void add(TypeLiteral<? extends T>... typeLiterals)
	{
		for(TypeLiteral<? extends T> typeLiteral : typeLiterals)
		{
			builder().add(builder().providedBy(typeLiteral));
		}
	}

	protected void addInstance(T... instances)
	{
		for(T instance : instances)
		{
			builder().add(builder().providedByInstance(instance));
		}
	}

	protected void addProvider(Provider<? extends T>... providers)
	{
		for(Provider<? extends T> provider : providers)
		{
			builder().add(provider);
		}
	}

	protected void addProvider(Class<? extends Provider<? extends T>>... providerClasses)
	{
		for(Class providerClass : providerClasses)
		{
			builder().add(builder().providedByProvider(providerClass));
		}
	}

	protected void addProvider(Key<? extends Provider<? extends T>>... providerKeys)
	{
		for(Key<? extends Provider<? extends T>> providerKey : providerKeys)
		{
			builder().add(builder().providedByProvider(providerKey));
		}
	}
}
