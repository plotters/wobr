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

import com.google.inject.ConfigurationException;
import com.google.inject.internal.collect.ImmutableSet;
import com.google.inject.spi.Message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

final class DecoratorFactories implements DecoratorFactory {

    private DecoratorFactory head;
    private final Map<DecoratorFactory, DecoratorFactory> body = new
            HashMap<DecoratorFactory, DecoratorFactory>();

    public void setHead(DecoratorFactory head) {
        this.head = head;
    }

    public void add(DecoratorFactory firstDecoratorFactory,
                    List<DecoratorFactory> decoratorFactories) {
        DecoratorFactory previousDecoratorFactory = firstDecoratorFactory;
        for (DecoratorFactory decoratorFactory : decoratorFactories) {
            add(previousDecoratorFactory, decoratorFactory);
            previousDecoratorFactory = decoratorFactory;
        }
    }

    public Object decorate(Object next) {
        return toFactory().decorate(next);
    }

    public void assertCompleteChain() {
        List<Message> messages = new ArrayList<Message>();
        for (DecoratorFactory decorating : body.keySet()) {
            if (decorating.equals(head)) {
                continue;
            }
            if (!body.values().contains(decorating)) {
                messages.add(new Message(String.format("decorator factory %s can not be found in the chain",
                        decorating)));
            }

        }
        if (!messages.isEmpty()) {
            throw new ConfigurationException(messages);
        }
    }

    private void add(DecoratorFactory decorating, DecoratorFactory decoratedBy) {
        if (body.containsKey(decorating)) {
            throw new ConfigurationException(ImmutableSet.of(new Message(decorating + " being decorated twice.")));
        }
        body.put(decorating, decoratedBy);
    }

    private DecoratorFactory toFactory() {
        List<DecoratorFactory> factories = new ArrayList<DecoratorFactory>();
        for (DecoratorFactory decorator : toList()) {
            factories.add(decorator);
        }
        return new ChainedDecoratorFactory(factories);
    }

    private List<DecoratorFactory> toList() {
        assertCompleteChain();
        List<DecoratorFactory> list = new ArrayList<DecoratorFactory>();
        DecoratorFactory previous = head;
        for (int i = 0; i <= body.size(); i++) {
            if (previous == null) {
                break;
            }
            list.add(previous);
            previous = body.get(previous);
        }
        return list;
    }
}
