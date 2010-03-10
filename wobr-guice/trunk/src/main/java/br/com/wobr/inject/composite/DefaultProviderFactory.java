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

import br.com.wobr.inject.composite.InstanceProvider;

import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.Provider;
import com.google.inject.TypeLiteral;

public class DefaultProviderFactory implements ProviderFactory {

    private final Provider<Injector> injectorProvider;

    public DefaultProviderFactory(Provider<Injector> injectorProvider) {
        this.injectorProvider = injectorProvider;
    }

    public Provider providedBy(final Class clazz) {
        return new Provider() {
            public Object get() {
                return injector().getInstance(clazz);
            }
        };
    }

    public Provider providedBy(final TypeLiteral typeLiteral) {
        return new Provider() {
            public Object get() {
                return injector().getInstance(Key.get(typeLiteral));
            }
        };
    }

    public Provider providedBy(final Key key) {
        return new Provider() {
            public Object get() {
                return injector().getInstance(key);
            }
        };
    }

    public Provider providedByInstance(final Object instance) {
        return new InstanceProvider(instance);
    }

    public Provider providedByProvider(final Class providerClass) {
        return new Provider() {
            public Object get() {
                Provider provider = (Provider) injector().getInstance(providerClass);
                return provider.get();
            }
        };
    }

    public Provider providedByProvider(final Key providerKey) {
        return new Provider() {
            public Object get() {
                Provider provider = (Provider) injector().getInstance(providerKey);
                return provider.get();
            }
        };
    }

    private Injector injector() {
        return injectorProvider.get();
    }
}
