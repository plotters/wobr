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
package br.com.wobr.inject.composite;

import com.google.inject.Key;
import com.google.inject.Provider;
import com.google.inject.TypeLiteral;

import java.util.HashMap;
import java.util.Map;

public class CachedProviderFactory implements ProviderFactory
{

	private final ProviderFactory providerFactory;

	private final Map<Object, Provider> providers = new HashMap<Object, Provider>();

	public CachedProviderFactory(ProviderFactory providerFactory)
	{
		this.providerFactory = providerFactory;
	}

	public Provider providedBy(TypeLiteral typeLiteral)
	{
		if(!providers.containsKey(typeLiteral))
		{
			providers.put(typeLiteral, providerFactory.providedBy(typeLiteral));
		}
		return providers.get(typeLiteral);
	}

	public Provider providedBy(Key key)
	{
		if(!providers.containsKey(key))
		{
			providers.put(key, providerFactory.providedBy(key));
		}
		return providers.get(key);
	}

	public Provider providedBy(Class clazz)
	{
		if(!providers.containsKey(clazz))
		{
			providers.put(clazz, providerFactory.providedBy(clazz));
		}
		return providers.get(clazz);
	}

	public Provider providedByInstance(Object instance)
	{
		if(!providers.containsKey(instance))
		{
			providers.put(instance, providerFactory.providedByInstance(instance));
		}
		return providers.get(instance);
	}

	public Provider providedByProvider(Class providerClass)
	{
		if(!providers.containsKey(providerClass))
		{
			providers.put(providerClass, providerFactory.providedByProvider(providerClass));
		}
		return providers.get(providerClass);

	}

	public Provider providedByProvider(Key providerKey)
	{
		if(!providers.containsKey(providerKey))
		{
			providers.put(providerKey, providerFactory.providedByProvider(providerKey));
		}
		return providers.get(providerKey);
	}
}
