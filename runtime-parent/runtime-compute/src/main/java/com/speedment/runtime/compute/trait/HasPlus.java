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

import com.speedment.runtime.compute.ToByte;
import com.speedment.runtime.compute.ToDouble;
import com.speedment.runtime.compute.ToInt;
import com.speedment.runtime.compute.ToLong;

/**
 * Trait that describes an expression that has several
 * {@link #plus(int)}-methods for generating new expressions for the sum of this
 * value and something else.
 *
 * @param <T>         the input type
 * @author Emil Forslund
 * @since  3.1.0
 */
public interface HasPlus<T> {

    /**
     * Creates and returns an expression that returns the sum of the result from
     * the current expression and the other term. For an example, if the result
     * of the current expression was {@code 9} and the other term was set to
     * {@code 3}, then the result of the returned expression would be {@code
     * 12}.
     * <p>
     * If this expression is nullable and the result was {@code null}, then the
     * result of the returned expression will also be {@code null}.
     *
     * @param other the other term used for the addition
     * @return the new expression
     */
    HasPlus<T> plus(byte other);

    /**
     * Creates and returns an expression that returns the sum of the result from
     * the current expression and the other term. For an example, if the result
     * of the current expression was {@code 9} and the other term was set to
     * {@code 3}, then the result of the returned expression would be {@code
     * 12}.
     * <p>
     * If this expression is nullable and the result was {@code null}, then the
     * result of the returned expression will also be {@code null}.
     *
     * @param other the other term used for the addition
     * @return the new expression
     */
    HasPlus<T> plus(ToByte<T> other);

    /**
     * Creates and returns an expression that returns the sum of the result from
     * the current expression and the other term. For an example, if the result
     * of the current expression was {@code 9} and the other term was set to
     * {@code 3}, then the result of the returned expression would be {@code
     * 12}.
     * <p>
     * If this expression is nullable and the result was {@code null}, then the
     * result of the returned expression will also be {@code null}.
     *
     * @param other the other term used for the addition
     * @return the new expression
     */
    HasPlus<T> plus(int other);

    /**
     * Creates and returns an expression that returns the sum of the result from
     * the current expression and the other term. For an example, if the result
     * of the current expression was {@code 9} and the other term was set to
     * {@code 3}, then the result of the returned expression would be {@code
     * 12}.
     * <p>
     * If this expression is nullable and the result was {@code null}, then the
     * result of the returned expression will also be {@code null}.
     *
     * @param other the other term used for the addition
     * @return the new expression
     */
    HasPlus<T> plus(ToInt<T> other);

    /**
     * Creates and returns an expression that returns the sum of the result from
     * the current expression and the other term. For an example, if the result
     * of the current expression was {@code 9} and the other term was set to
     * {@code 3}, then the result of the returned expression would be {@code
     * 12}.
     * <p>
     * If this expression is nullable and the result was {@code null}, then the
     * result of the returned expression will also be {@code null}.
     *
     * @param other the other term used for the addition
     * @return the new expression
     */
    HasPlus<T> plus(long other);

    /**
     * Creates and returns an expression that returns the sum of the result from
     * the current expression and the other term. For an example, if the result
     * of the current expression was {@code 9} and the other term was set to
     * {@code 3}, then the result of the returned expression would be {@code
     * 12}.
     * <p>
     * If this expression is nullable and the result was {@code null}, then the
     * result of the returned expression will also be {@code null}.
     *
     * @param other the other term used for the addition
     * @return the new expression
     */
    HasPlus<T> plus(ToLong<T> other);

    /**
     * Creates and returns an expression that returns the sum of the result from
     * the current expression and the other term. For an example, if the result
     * of the current expression was {@code 9} and the other term was set to
     * {@code 3}, then the result of the returned expression would be {@code
     * 12}.
     * <p>
     * If this expression is nullable and the result was {@code null}, then the
     * result of the returned expression will also be {@code null}.
     *
     * @param other the other term used for the addition
     * @return the new expression
     */
    ToDouble<T> plus(double other);

    /**
     * Creates and returns an expression that returns the sum of the result from
     * the current expression and the other term. For an example, if the result
     * of the current expression was {@code 9} and the other term was set to
     * {@code 3}, then the result of the returned expression would be {@code
     * 12}.
     * <p>
     * If this expression is nullable and the result was {@code null}, then the
     * result of the returned expression will also be {@code null}.
     *
     * @param other the other term used for the addition
     * @return the new expression
     */
    ToDouble<T> plus(ToDouble<T> other);

}