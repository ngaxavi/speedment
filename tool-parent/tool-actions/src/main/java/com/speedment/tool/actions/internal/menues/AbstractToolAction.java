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
package com.speedment.tool.actions.internal.menues;

import com.speedment.common.injector.State;
import com.speedment.common.injector.annotation.ExecuteBefore;
import com.speedment.common.injector.annotation.WithState;
import com.speedment.tool.actions.ProjectTreeComponent;

import static com.speedment.common.injector.State.INITIALIZED;
import static com.speedment.common.injector.State.RESOLVED;

/**
 * Abstract base implementation of a tool action. The purpose of this class is
 * to standardize the dependency injection phases used in different actions.
 *
 * @author Emil Forslund
 * @since  3.0.17
 */
abstract class AbstractToolAction {

    /**
     * This method will be invoked before the {@link State#RESOLVED}-phase, but
     * before the {@link ProjectTreeComponent} is {@link State#INITIALIZED}.
     *
     * @param projectTree  the project tree component
     */
    @ExecuteBefore(RESOLVED)
    abstract void installMenuItems(
            @WithState(INITIALIZED) ProjectTreeComponent projectTree);

}