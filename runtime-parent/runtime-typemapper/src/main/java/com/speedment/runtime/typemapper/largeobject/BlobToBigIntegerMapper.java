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
package com.speedment.runtime.typemapper.largeobject;

import com.speedment.runtime.config.Column;
import com.speedment.runtime.typemapper.TypeMapper;
import com.speedment.runtime.typemapper.exception.SpeedmentTypeMapperException;

import javax.sql.rowset.serial.SerialBlob;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Blob;
import java.sql.SQLException;

/**
 * Maps between a large integer stored in binary form into a java
 * {@link BigDecimal}.
 *
 * @author Emil Forslund
 * @since  3.0.23
 */
public final class BlobToBigIntegerMapper
implements TypeMapper<Blob, BigInteger> {

    @Override
    public String getLabel() {
        return "Blob to BigInteger Mapper";
    }

    @Override
    public Type getJavaType(Column column) {
        return BigInteger.class;
    }

    @Override
    public Category getJavaTypeCategory(Column column) {
        return Category.COMPARABLE;
    }

    @Override
    public BigInteger toJavaType(Column column, Class<?> entityType, Blob value) {
        if (value == null) return null;
        try {
            final byte[] bytes = value.getBytes(1, Math.toIntExact(value.length()));
            return new BigInteger(bytes);
        } catch (final SQLException ex) {
            throw new SpeedmentTypeMapperException(
                "Error mapping Blob to BigInteger.", ex);
        }
    }

    @Override
    public Blob toDatabaseType(BigInteger value) {
        if (value == null) return null;
        try {
            return new SerialBlob(value.toByteArray());
        } catch (final SQLException ex) {
            throw new SpeedmentTypeMapperException(
                "Error mapping BigInteger to Blob.", ex);
        }
    }

    @Override
    public Ordering getOrdering() {
        return Ordering.UNSPECIFIED;
    }
}
