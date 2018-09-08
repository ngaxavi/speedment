package com.speedment.runtime.join.builder;

import com.speedment.common.function.QuadFunction;
import com.speedment.common.tuple.Tuple;
import com.speedment.common.tuple.TuplesOfNullables;
import com.speedment.common.tuple.nullable.Tuple4OfNullables;
import com.speedment.runtime.config.identifier.TableIdentifier;
import com.speedment.runtime.field.trait.HasComparableOperators;
import com.speedment.runtime.join.Join;
import com.speedment.runtime.join.trait.HasDefaultBuild;
import com.speedment.runtime.join.trait.HasJoins;
import com.speedment.runtime.join.trait.HasOnPredicates;
import com.speedment.runtime.join.trait.HasWhere;

/**
 * Join Builder stage used when only 4 tables has been specified so far.
 *
 * @param <T0>  the first entity type
 * @param <T1>  the second entity type
 * @param <T2>  the third entity type
 * @param <T3>  the fourth entity type
 *
 * @author Per Minborg
 * @author Emil Forslund
 * @since  3.1.1
 */
public interface JoinBuilder4<T0, T1, T2, T3>
    extends HasJoins<JoinBuilder4.AfterJoin<T0, T1, T2, T3, ?>, JoinBuilder5<T0, T1, T2, T3, ?>>,
            HasWhere<T3, JoinBuilder4<T0, T1, T2, T3>>,
            HasDefaultBuild<Tuple4OfNullables<T0, T1, T2, T3>> {

    @Override
    <T4> AfterJoin<T0, T1, T2, T3, T4> innerJoinOn(HasComparableOperators<T4, ?> joinedField);

    @Override
    <T4> AfterJoin<T0, T1, T2, T3, T4> leftJoinOn(HasComparableOperators<T4, ?> joinedField);

    @Override
    <T4> AfterJoin<T0, T1, T2, T3, T4> rightJoinOn(HasComparableOperators<T4, ?> joinedField);

//                    @Override
//                    <T4> AfterJoin<T0, T1, T2, T3, T4> fullOuterJoinOn(HasComparableOperators<T4, ?> joinedField);

    @Override
    <T4> JoinBuilder5<T0, T1, T2, T3, T4> crossJoin(TableIdentifier<T4> joinedTable);

    @Override
    default Join<Tuple4OfNullables<T0, T1, T2, T3>> build() {
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
    <T> Join<T> build(QuadFunction<T0, T1, T2, T3, T> constructor);

    interface AfterJoin<T0, T1, T2, T3, T4>
    extends HasOnPredicates<JoinBuilder5<T0, T1, T2, T3, T4>> {}
}
