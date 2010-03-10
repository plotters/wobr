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

import java.util.List;

final class ChainedDecoratorFactory implements DecoratorFactory {

    private final List<DecoratorFactory> decoratorFactories;

    public ChainedDecoratorFactory(List<DecoratorFactory> decoratorFactories) {
        this.decoratorFactories = decoratorFactories;
    }

    public final Object decorate(Object beginning) {
        Object next = beginning;
        for (DecoratorFactory decoratorFactory : decoratorFactories) {
            next = decoratorFactory.decorate(next);
        }
        return next;
    }
}
