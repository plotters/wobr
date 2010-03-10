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
package br.com.wobr.inject.list.internal;

import br.com.wobr.inject.composite.CachedProviderFactory;
import br.com.wobr.inject.composite.ProviderFactory;

import com.google.inject.*;
import com.google.inject.spi.Message;

import java.util.*;

public class ListBuilder extends CachedProviderFactory
{

	private final static Set<ListBuilder> builders = new HashSet<ListBuilder>();

	private final Map<Provider, List<Provider>> beforeProviders = new HashMap<Provider, List<Provider>>();

	private final Map<Provider, List<Provider>> afterProviders = new HashMap<Provider, List<Provider>>();

	private final List<Provider> normalProviders = new ArrayList<Provider>();

	public ListBuilder(ProviderFactory providerFactory)
	{
		super(providerFactory);
		builders.add(this);
	}

	public void add(Provider provider)
	{
		normalProviders.add(provider);
	}

	public void insertBefore(Provider provider, List<Provider> providers)
	{
		insert(provider, providers, beforeProviders);
	}

	public void insertAfter(Provider provider, List<Provider> providers)
	{
		insert(provider, providers, afterProviders);
	}

	private List get(Provider provider)
	{
		List list = new ArrayList();
		list.addAll(get(provider, beforeProviders));
		list.add(provider.get());
		list.addAll(get(provider, afterProviders));
		return list;
	}

	private List get(Provider provider, Map<Provider, List<Provider>> target)
	{
		List list = new ArrayList();
		if(target.containsKey(provider))
		{
			for(Provider before : target.get(provider))
			{
				list.addAll(get(before));
			}
		}
		return list;
	}

	private void insert(Provider provider, List<Provider> providers, Map<Provider, List<Provider>> target)
	{
		if(!target.containsKey(provider))
		{
			target.put(provider, new ArrayList<Provider>());
		}
		target.get(provider).addAll(providers);
	}

	private boolean contains(Map<Provider, List<Provider>> target, Provider provider)
	{
		for(List<Provider> list : target.values())
		{
			if(list.contains(provider))
			{
				return true;
			}
		}
		return false;
	}

	public void bind(Binder binder, TypeLiteral typeLiteral)
	{
		binder.bind(typeLiteral).toProvider(new Provider()
		{
			public Object get()
			{
				List list = new ArrayList();
				for(Provider provider : normalProviders)
				{
					list.addAll(ListBuilder.this.get(provider));
				}
				return list;
			}
		});
		binder.requestInjection(new CompletenessAssertion());
	}

	private class CompletenessAssertion
	{

		@Inject
		public void assertCompleteChain()
		{
			List<Message> messages = new ArrayList<Message>();
			messages.addAll(assertCompleteChain(beforeProviders.keySet()));
			messages.addAll(assertCompleteChain(afterProviders.keySet()));
			if(!messages.isEmpty())
			{
				throw new ConfigurationException(messages);
			}
		}

		private List<Message> assertCompleteChain(Set<Provider> asserting)
		{
			List<Message> messages = new ArrayList<Message>();
			for(Provider provider : asserting)
			{
				boolean foundInBefore = contains(beforeProviders, provider);
				boolean foundInAfter = contains(beforeProviders, provider);
				boolean foundInNormal = normalProviders.contains(provider);
				boolean found = foundInBefore || foundInAfter || foundInNormal;
				if(!found)
				{
					messages.add(new Message(String.format("provider %s can not be found in the list", provider)));
				}
			}
			return messages;
		}
	}
}
