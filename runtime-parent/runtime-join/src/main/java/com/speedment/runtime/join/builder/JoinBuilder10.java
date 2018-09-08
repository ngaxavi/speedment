package com.speedment.runtime.join.builder;

import com.speedment.common.function.Function10;
import com.speedment.common.tuple.Tuple;
import com.speedment.common.tuple.TuplesOfNullables;
import com.speedment.common.tuple.nullable.Tuple10OfNullables;
import com.speedment.runtime.join.Join;
import com.speedment.runtime.join.trait.HasDefaultBuild;
import com.speedment.runtime.join.trait.HasWhere;

/**
 * Join Builder stage used when only 10 tables has been specified so far.
 *
 * @param <T0>  the first entity type
 * @param <T1>  the second entity type
 * @param <T2>  the third entity type
 * @param <T3>  the fourth entity type
 * @param <T4>  the fifth entity type
 * @param <T5>  the sixth entity type
 * @param <T6>  the seventh entity type
 * @param <T7>  the eight entity type
 * @param <T8>  the ninth entity type
 * @param <T9>  the tenth entity type
 *
 * @author Per Minborg
 * @author Emil Forslund
 * @since  3.1.1
 */
public interface JoinBuilder10<T0, T1, T2, T3, T4, T5, T6, T7, T8, T9>
    extends HasWhere<T9, JoinBuilder10<T0, T1, T2, T3, T4, T5, T6, T7, T8, T9>>,
            HasDefaultBuild<Tuple10OfNullables<T0, T1, T2, T3, T4, T5, T6, T7, T8, T9>> {

    @Override
    default Join<Tuple10OfNullables<T0, T1, T2, T3, T4, T5, T6, T7, T8, T9>> build() {
        return build(TuplesOfNullables::ofNullables);
    }

    /**
     * Creates and returns a new Join object where elements in the Join
     * object's stream method is created using the provided {@code
     * constructor}.
     *
     * @param <T>         the type of element in the Join object's stream
     *                    method.
     * @param constructor to use to create stream elements.
     * @return a new Join object where elements in the Join object's stream
     *     method is of a default {@link Tuple} type
     *
     * @throws NullPointerException  if the provided {@code constructor } is
     *                               {@code null}
     * @throws IllegalStateException if fields that are added via the {@code
     *                               on() } method refers to tables that are
     *                               not a part of the join.
     */
    <T> Join<T> build(Function10<T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T> constructor);

}
