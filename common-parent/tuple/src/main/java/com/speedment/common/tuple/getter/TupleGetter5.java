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
package com.speedment.common.tuple.getter;

/**
 * Specialization of {@link TupleGetter} that always returns the 5th element.
 * <p>
 * Generated by com.speedment.sources.pattern.tuple.TupleGetterPattern
 * 
 * @param <T>  the type of the tuple to get a value from
 * @param <T5> the type of the element to return
 * 
 * @author Emil Forslund
 * @since  3.1.0
 */
@FunctionalInterface
public interface TupleGetter5<T, T5> extends TupleGetter<T, T5> {
    
    @Override
    default int index() {
        return 5;
    }
}