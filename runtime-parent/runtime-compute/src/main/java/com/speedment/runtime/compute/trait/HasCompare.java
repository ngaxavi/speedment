/**
 *
 * Copyright (c) 2006-2018, Speedment, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); You may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.speedment.runtime.compute.trait;

import java.util.Comparator;

/**
 * Trait for expressions that can also be used as a {@link Comparator} since it
 * has a {@link #compare(Object, Object)} method.
 *
 * @author Emil Forslund
 * @since  3.1.0
 */
public interface HasCompare<T> extends Comparator<T> {

    @Override
    int compare(T first, T second);
}
