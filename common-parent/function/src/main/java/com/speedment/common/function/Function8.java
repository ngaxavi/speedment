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
package com.speedment.common.function;

/**
 * Represents a function that accepts eight arguments and produces a result.
 * This is the eight-arity specialization of {@link
 * java.util.function.Function}.
 * <p>
 * This class has been generated.
 * 
 * @param <T0> the type of the first argument to the function
 * @param <T1> the type of the second argument to the function
 * @param <T2> the type of the third argument to the function
 * @param <T3> the type of the fourth argument to the function
 * @param <T4> the type of the fifth argument to the function
 * @param <T5> the type of the sixth argument to the function
 * @param <T6> the type of the seventh argument to the function
 * @param <T7> the type of the eighth argument to the function
 * @param <R>  the type of the result of the function
 * 
 * @author Per Minborg
 * 
 * @see java.util.function.Function
 */
@FunctionalInterface
public interface Function8<T0, T1, T2, T3, T4, T5, T6, T7, R> {
    
    /**
     * Applies this function to the given arguments.
     * 
     * @param t0 the first function argument
     * @param t1 the second function argument
     * @param t2 the third function argument
     * @param t3 the fourth function argument
     * @param t4 the fifth function argument
     * @param t5 the sixth function argument
     * @param t6 the seventh function argument
     * @param t7 the eighth function argument
     * @return   the function result
     */
    R apply(
    T0 t0,
            T1 t1,
            T2 t2,
            T3 t3,
            T4 t4,
            T5 t5,
            T6 t6,
            T7 t7);
}