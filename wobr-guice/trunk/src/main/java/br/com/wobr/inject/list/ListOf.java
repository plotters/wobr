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
package br.com.wobr.inject.list;

import br.com.wobr.inject.composite.CompositeModule;
import br.com.wobr.inject.composite.DefaultProviderFactory;
import br.com.wobr.inject.list.internal.ListBuilder;

import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.Provider;
import com.google.inject.TypeLiteral;
import com.google.inject.util.Types;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Used to bind a List of T in multiple modules
 * 
 * @author taowen@gmail.com (Wen Tao)
 * @param <T>
 *            the element type
 */
public abstract class ListOf<T> extends CompositeModule<T, ListBuilder>
{

	public ListOf()
	{
		super(ListBuilder.class);
	}

	protected void configureBuilder()
	{
		builder().bind(binder(), TypeLiteral.get(Types.listOf(type)));
	}

	protected ListBuilder createBuilder()
	{
		Provider<Injector> injectorProvider = binder().getProvider(Injector.class);
		DefaultProviderFactory providerFactory = new DefaultProviderFactory(injectorProvider);
		return new ListBuilder(providerFactory);
	}

	protected void add(Class<? extends T>... classes)
	{
		for(Class<? extends T> clazz : classes)
		{
			builder().add(builder().providedBy(clazz));
		}
	}

	protected void add(TypeLiteral<? extends T>... typeLiterals)
	{
		for(TypeLiteral<? extends T> typeLiteral : typeLiterals)
		{
			builder().add(builder().providedBy(typeLiteral));
		}
	}

	protected void add(Key<? extends T>... keys)
	{
		for(Key<? extends T> key : keys)
		{
			builder().add(builder().providedBy(key));
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
		for(Provider provider : providers)
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
		for(Key providerKey : providerKeys)
		{
			builder().add(builder().providedByProvider(providerKey));
		}
	}

	protected InsertClause insert(Class<? extends T>... classes)
	{
		List<Provider> providers = new ArrayList<Provider>();
		for(Class clazz : classes)
		{
			providers.add(builder().providedBy(clazz));
		}
		return new InsertClause(providers);
	}

	protected InsertClause insert(TypeLiteral<? extends T>... typeLiterals)
	{
		List<Provider> providers = new ArrayList<Provider>();
		for(TypeLiteral typeLiteral : typeLiterals)
		{
			providers.add(builder().providedBy(typeLiteral));
		}
		return new InsertClause(providers);
	}

	protected InsertClause insert(Key<? extends T>... keys)
	{
		List<Provider> providers = new ArrayList<Provider>();
		for(Key key : keys)
		{
			providers.add(builder().providedBy(key));
		}
		return new InsertClause(providers);
	}

	protected InsertClause insertInstance(T... instances)
	{
		List<Provider> providers = new ArrayList<Provider>();
		for(T instance : instances)
		{
			providers.add(builder().providedByInstance(instance));
		}
		return new InsertClause(providers);
	}

	protected InsertClause insertProvider(Provider<? extends T>... providers)
	{
		List list = Arrays.asList(providers);
		return new InsertClause(list);
	}

	protected InsertClause insertProvider(Class<? extends Provider<? extends T>>... providerClasses)
	{
		List<Provider> providers = new ArrayList<Provider>();
		for(Class providerClass : providerClasses)
		{
			providers.add(builder().providedByProvider(providerClass));
		}
		return new InsertClause(providers);
	}

	protected InsertClause insertProvider(Key<? extends Provider<? extends T>>... providerKeys)
	{
		List<Provider> providers = new ArrayList<Provider>();
		for(Key providerKey : providerKeys)
		{
			providers.add(builder().providedByProvider(providerKey));
		}
		return new InsertClause(providers);
	}

	protected class InsertClause
	{

		private List<Provider> providers;

		public InsertClause(List<Provider> providers)
		{
			this.providers = providers;
		}

		public void before(Class<? extends T> clazz)
		{
			builder().insertBefore(builder().providedBy(clazz), providers);
		}

		public void before(TypeLiteral<? extends T> typeLiteral)
		{
			builder().insertBefore(builder().providedBy(typeLiteral), providers);
		}

		public void before(Key<? extends T> key)
		{
			builder().insertBefore(builder().providedBy(key), providers);
		}

		public void beforeInstance(T instance)
		{
			builder().insertBefore(builder().providedByInstance(instance), providers);
		}

		public void beforeProvider(Provider<? extends T> provider)
		{
			builder().insertBefore(provider, providers);
		}

		public void beforeProvider(Class<? extends Provider<? extends T>> providerClass)
		{
			builder().insertBefore(builder().providedByProvider(providerClass), providers);
		}

		public void beforeProvider(Key<? extends Provider<? extends T>> providerKey)
		{
			builder().insertBefore(builder().providedByProvider(providerKey), providers);
		}

		public void after(Class<? extends T> clazz)
		{
			builder().insertAfter(builder().providedBy(clazz), providers);
		}

		public void after(TypeLiteral<? extends T> typeLiteral)
		{
			builder().insertAfter(builder().providedBy(typeLiteral), providers);
		}

		public void after(Key<? extends T> key)
		{
			builder().insertAfter(builder().providedBy(key), providers);
		}

		public void afterInstance(T instance)
		{
			builder().insertAfter(builder().providedByInstance(instance), providers);
		}

		public void afterProvider(Provider<? extends T> provider)
		{
			builder().insertAfter(provider, providers);
		}

		public void afterProvider(Class<? extends Provider<? extends T>> providerClass)
		{
			builder().insertAfter(builder().providedByProvider(providerClass), providers);
		}

		public void afterProvider(Key<? extends Provider<? extends T>> providerKey)
		{
			builder().insertAfter(builder().providedByProvider(providerKey), providers);
		}
	}
}
