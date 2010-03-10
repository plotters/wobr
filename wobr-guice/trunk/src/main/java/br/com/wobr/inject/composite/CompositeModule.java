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

import com.google.inject.Binder;
import com.google.inject.Module;
import javaonhorse.auxiliary.identity.HasIdentity;
import javaonhorse.auxiliary.identity.IdentifiedBy;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class CompositeModule<T, B> implements Module {

    protected final Class<T> type;
    private final static Map<BuilderKey, Object> builders = new HashMap<BuilderKey, Object>();
    private final static List<ConfiguredType> configuredTypes = new ArrayList<ConfiguredType>();
    private Binder binder;
    private BuilderKey builderKey;

    public CompositeModule(Class<? extends B> builderType) {
        type = firstTypeArg(getClass());
        builderKey = new BuilderKey(type, builderType);
    }

    private Class firstTypeArg(Class clazz) {
        ParameterizedType parameterizedType = (ParameterizedType) clazz.getGenericSuperclass();
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        return (Class) actualTypeArguments[0];
    }

    public void configure(Binder binder) {
        this.binder = binder;
        configure();
        ConfiguredType configuredType = new ConfiguredType(type, binder);
        if (configuredTypes.contains(configuredType)) {
            return;
        }
        configuredTypes.add(configuredType);
        configureBuilder();
        Cleaner.bind(binder);
    }

    protected abstract void configure();

    protected abstract void configureBuilder();

    protected final Binder binder() {
        return binder;
    }

    protected final B builder() {
        if (!builders.containsKey(builderKey)) {
            builders.put(builderKey, createBuilder());
        }
        return (B) builders.get(builderKey);
    }

    protected abstract B createBuilder();

    public static void clean() {
        Cleaner.clean();
    }

    protected static final class Cleaner {

        private static boolean bound;

        public Cleaner() {
            clean();
        }

        public static void clean() {
            bound = false;
            configuredTypes.clear();
            builders.clear();
        }

        public static void bind(Binder binder) {
            if (bound) {
                return;
            }
            binder.bind(Cleaner.class).asEagerSingleton();
            bound = true;
        }
    }

    private static final class ConfiguredType extends HasIdentity {

        @IdentifiedBy
        final Class type;
        @IdentifiedBy
        final Binder binder;

        public ConfiguredType(Class type, Binder binder) {
            this.type = type;
            this.binder = binder;
        }
    }

    private static final class BuilderKey extends HasIdentity {

        @IdentifiedBy
        final Class buildingFor;
        @IdentifiedBy
        final Class builderType;

        public BuilderKey(Class buildingFor, Class builderType) {
            this.buildingFor = buildingFor;
            this.builderType = builderType;
        }
    }
}
