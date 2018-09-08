package com.speedment.runtime.field.internal.predicate.enums;

import com.speedment.runtime.compute.trait.ToNullable;
import com.speedment.runtime.field.EnumField;
import com.speedment.runtime.field.predicate.FieldIsNotNullPredicate;
import com.speedment.runtime.field.predicate.FieldIsNullPredicate;

import static java.util.Objects.requireNonNull;

/**
 * @author Emil Forslund
 * @since  3.1.2
 */
public final class EnumIsNullPredicate<ENTITY, D, E extends Enum<E>>
implements FieldIsNullPredicate<ENTITY, E> {

    private final EnumField<ENTITY, D, E> field;

    public EnumIsNullPredicate(EnumField<ENTITY, D, E> field) {
        this.field = requireNonNull(field);
    }

    @Override
    public boolean test(ENTITY value) {
        return field.apply(value) == null;
    }

    @Override
    public FieldIsNotNullPredicate<ENTITY, E> negate() {
        return new EnumIsNotNullPredicate<>(field);
    }

    @Override
    public ToNullable<ENTITY, E, ?> expression() {
        return field;
    }

    @Override
    public EnumField<ENTITY, D, E> getField() {
        return field;
    }
}
