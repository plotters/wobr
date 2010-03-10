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
package br.com.wobr.inject.chain.api;

/**
 * Used to control how to create a decorator. Can be used to partipate building
 * a chain, as input. Also could be result bound by DecoratorFactoryOf modules,
 * as output.
 * 
 * @author taowen@gmail.com (Wen Tao)
 * @param <T>
 *            the decorating type
 */
public interface DecoratorFactory<T>
{

	T decorate(T next);
}
