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
package com.speedment.runtime.connector.postgres;

import com.speedment.common.injector.InjectBundle;
import com.speedment.runtime.connector.postgres.internal.PostgresComponentImpl;
import com.speedment.runtime.connector.postgres.internal.PostgresDbmsMetadataHandler;
import com.speedment.runtime.connector.postgres.internal.PostgresDbmsOperationHandler;
import com.speedment.runtime.connector.postgres.internal.PostgresDbmsType;
import com.speedment.runtime.connector.postgres.internal.PostgresSpeedmentPredicateView;
import java.util.stream.Stream;

/**
 *
 * @author Per Minborg
 * @since 3.0.0
 */
public class PostgresBundle implements InjectBundle {

    @Override
    public Stream<Class<?>> injectables() {
        return Stream.of(
            PostgresComponentImpl.class,
            PostgresDbmsType.class,
            PostgresDbmsMetadataHandler.class,
            PostgresDbmsOperationHandler.class,
            PostgresSpeedmentPredicateView.class
        );
    }
}
