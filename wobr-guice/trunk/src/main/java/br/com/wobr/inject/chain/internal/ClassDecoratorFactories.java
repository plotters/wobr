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

import com.google.inject.Injector;
import com.google.inject.Provider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

final class ClassDecoratorFactories {

    private final Map<Class, DecoratorFactory> decoratorClasses = new HashMap<Class, DecoratorFactory>();
    private final Provider<Injector> injectorProvider;
    private final Class type;

    public ClassDecoratorFactories(Provider<Injector> injectorProvider, Class type) {
        this.injectorProvider = injectorProvider;
        this.type = type;
    }

    public DecoratorFactory get(Class decoratorClass) {
        if (!decoratorClasses.containsKey(decoratorClass)) {
            ClassDecoratorFactory factory = new ClassDecoratorFactory(injectorProvider, type, decoratorClass);
            decoratorClasses.put(decoratorClass, factory);
        }
        return decoratorClasses.get(decoratorClass);
    }

    public DecoratorFactory[] get(Class[] decoratorClasses) {
        List<DecoratorFactory> factories = new ArrayList<DecoratorFactory>();
        for (Class decoratorClass : decoratorClasses) {
            factories.add(get(decoratorClass));
        }
        return factories.toArray(new DecoratorFactory[factories.size()]);
    }
}
