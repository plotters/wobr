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

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.inject.Binder;
import com.google.inject.Module;

public abstract class CompositeModule<T, B> implements Module
{

	private static final class BuilderKey extends HasIdentity
	{

		@IdentifiedBy
		final Class builderType;

		@IdentifiedBy
		final Class buildingFor;

		public BuilderKey(Class buildingFor, Class builderType)
		{
			this.buildingFor = buildingFor;
			this.builderType = builderType;
		}
	}

	protected static final class Cleaner
	{

		private static boolean bound;

		public static void bind(Binder binder)
		{
			if(bound)
			{
				return;
			}
			binder.bind(Cleaner.class).asEagerSingleton();
			bound = true;
		}

		public static void clean()
		{
			bound = false;
			configuredTypes.clear();
			builders.clear();
		}

		public Cleaner()
		{
			clean();
		}
	}

	private static final class ConfiguredType extends HasIdentity
	{

		@IdentifiedBy
		final Binder binder;

		@IdentifiedBy
		final Class type;

		public ConfiguredType(Class type, Binder binder)
		{
			this.type = type;
			this.binder = binder;
		}
	}

	private final static Map<BuilderKey, Object> builders = new HashMap<BuilderKey, Object>();

	private final static List<ConfiguredType> configuredTypes = new ArrayList<ConfiguredType>();

	public static void clean()
	{
		Cleaner.clean();
	}

	private Binder binder;

	private final BuilderKey builderKey;

	protected final Class<T> type;

	protected CompositeModule(Class<? extends B> builderType)
	{
		type = firstTypeArg(getClass());
		builderKey = new BuilderKey(type, builderType);
	}

	protected final Binder binder()
	{
		return binder;
	}

	protected final B builder()
	{
		if(!builders.containsKey(builderKey))
		{
			builders.put(builderKey, createBuilder());
		}
		return (B) builders.get(builderKey);
	}

	protected abstract void configure();

	public void configure(Binder binder)
	{
		this.binder = binder;
		configure();
		ConfiguredType configuredType = new ConfiguredType(type, binder);
		if(configuredTypes.contains(configuredType))
		{
			return;
		}
		configuredTypes.add(configuredType);
		configureBuilder();
		Cleaner.bind(binder);
	}

	protected abstract void configureBuilder();

	protected abstract B createBuilder();

	private Class firstTypeArg(Class clazz)
	{
		ParameterizedType parameterizedType = (ParameterizedType) clazz.getGenericSuperclass();
		Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
		return (Class) actualTypeArguments[0];
	}
}
