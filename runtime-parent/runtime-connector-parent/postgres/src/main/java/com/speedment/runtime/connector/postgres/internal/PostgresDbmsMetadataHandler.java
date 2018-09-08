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
package com.speedment.runtime.connector.postgres.internal;

import com.speedment.runtime.config.Column;
import com.speedment.runtime.core.db.JavaTypeMap;
import com.speedment.runtime.core.db.metadata.ColumnMetaData;
import com.speedment.runtime.core.internal.db.AbstractDbmsMetadataHandler;

import java.sql.Blob;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Optional;

/**
 * Created by fdirlikl on 11/13/2015.
 *
 * @author Fatih Dirlikli
 * @author Emil Forslund
 */
public final class PostgresDbmsMetadataHandler extends AbstractDbmsMetadataHandler {

    @Override
    protected JavaTypeMap newJavaTypeMap() {
        final JavaTypeMap javaTypeMap = super.newJavaTypeMap();

        javaTypeMap.put("bytea", Blob.class);

        // https://www.postgresql.org/docs/9.2/static/infoschema-datatypes.html
        javaTypeMap.put("cardinal_number", Integer.class);
        javaTypeMap.put("character_data", String.class);
        javaTypeMap.put("sql_identifier", String.class);
        javaTypeMap.put("time_stamp", Timestamp.class);
        javaTypeMap.put("yes_or_no", String.class);

        javaTypeMap.put("json", String.class);
        javaTypeMap.put("jsonb", String.class);

        javaTypeMap.addRule((sqlTypeMapping, md) -> {
            // Map a BIT(1) to boolean
            if ("BIT".equalsIgnoreCase(md.getTypeName()) && md.getColumnSize() == 1) {
                return Optional.of(Boolean.class);
            } else return Optional.empty();
        });

        javaTypeMap.addRule((sqlTypeMapping, md) -> {
            if ("year".equalsIgnoreCase(md.getTypeName()) && md.getDataType() == 2001) {
                return Optional.of(Integer.class);
            } else return Optional.empty();
        });

        javaTypeMap.addRule((sqlTypeMapping, md) -> {
            if ("_text".equalsIgnoreCase(md.getTypeName()) && md.getDataType() == 2003) {
                return Optional.of(String.class);
            } else return Optional.empty();
        });

        javaTypeMap.addRule((sqlTypeMapping, md) -> {
            if ("tsvector".equalsIgnoreCase(md.getTypeName()) && md.getDataType() == 1111) {
                return Optional.of(String.class);
            } else return Optional.empty();
        });

        return javaTypeMap;
    }
    
    @Override
    protected void setAutoIncrement(Column column, ColumnMetaData md) throws SQLException {
        super.setAutoIncrement(column, md);
        final String defaultValue = md.getColumnDef();
        if (defaultValue != null && defaultValue.startsWith("nextval(")) {
            column.mutator().setAutoIncrement(true);
        }
    }
}