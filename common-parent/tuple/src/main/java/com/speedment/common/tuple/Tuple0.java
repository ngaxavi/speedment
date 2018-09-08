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
package com.speedment.common.tuple;

import com.speedment.common.tuple.getter.TupleGetter;

/**
 * This interface defines a generic {@link Tuple} of degree 0 that can hold
 * non-null values. A Tuple is type safe, immutable and thread safe. For tuples
 * that can hold null elements see {@link TupleOfNullables}. For mutable tuples
 * see {@link MutableTuple} 
 * 
 * This {@link Tuple} has a degree of 0
 * <p>
 * Generated by com.speedment.sources.pattern.tuple.TuplePattern
 * 
 * @author Per Minborg
 * 
 * @see Tuple
 * @see TupleOfNullables
 * @see MutableTuple
 */
public interface Tuple0 extends Tuple {
    
    @Override
    default int degree() {
        return 0;
    }
    
    default Object get(int index) {
        switch (index) {
            
            default : throw new IllegalArgumentException(String.format("Index %d is outside bounds of tuple of degree %s", index, degree()
            ));
        }
    }
}