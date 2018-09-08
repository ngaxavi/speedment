package com.speedment.runtime.field.internal.comparator;

import com.speedment.runtime.field.comparator.BooleanFieldComparator;
import com.speedment.runtime.field.comparator.FieldComparator;
import com.speedment.runtime.field.comparator.NullOrder;
import com.speedment.runtime.field.trait.HasBooleanValue;

import java.util.Objects;

import static com.speedment.common.invariant.NullUtil.requireNonNulls;
import static java.util.Objects.requireNonNull;

/**
 * @param <ENTITY> entity type
 * @param <D>      database type
 *
 * @author Emil Forslund
 * @since  3.1.4
 */
public class BooleanFieldComparatorImpl<ENTITY, D>
extends AbstractFieldComparator<ENTITY>
implements BooleanFieldComparator<ENTITY, D> {

    private final HasBooleanValue<ENTITY, D> field;
    private final boolean reversed;

    public BooleanFieldComparatorImpl(HasBooleanValue<ENTITY, D> field) {
        this(field, false);
    }

    BooleanFieldComparatorImpl(HasBooleanValue<ENTITY, D> field, boolean reversed) {
        this.field    = requireNonNull(field);
        this.reversed = reversed;
    }

    @Override
    public HasBooleanValue<ENTITY, D> getField() {
        return field;
    }

    @Override
    public NullOrder getNullOrder() {
        return NullOrder.NONE;
    }

    @Override
    public boolean isReversed() {
        return reversed;
    }

    @Override
    public BooleanFieldComparatorImpl<ENTITY, D> reversed() {
        return new BooleanFieldComparatorImpl<>(field, !reversed);
    }

    @Override
    public int compare(ENTITY first, ENTITY second) {
        requireNonNulls(first, second);
        final boolean a = field.getAsBoolean(first);
        final boolean b = field.getAsBoolean(second);
        return applyReversed(Boolean.compare(a, b));
    }

    @Override
    public int hashCode() {
        return (4049 + Objects.hashCode(this.field.identifier())) * 3109
            + Boolean.hashCode(reversed);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof FieldComparator)) return false;

        @SuppressWarnings("unchecked")
        final FieldComparator<ENTITY> casted =
            (FieldComparator<ENTITY>) obj;

        return reversed == casted.isReversed()
            && Objects.equals(
            field.identifier(),
            casted.getField().identifier()
        );
    }

    @Override
    public String toString() {
        return "(order by " + field.identifier() + " " +
            (reversed ? "descending" : "ascending") + ")";
    }

    private int applyReversed(int compare) {
        if (compare == 0) {
            return 0;
        } else {
            if (reversed) {
                if (compare > 0) {
                    return -1;
                } else {
                    return 1;
                }
            } else {
                if (compare > 0) {
                    return 1;
                } else {
                    return -1;
                }
            }
        }
    }
}