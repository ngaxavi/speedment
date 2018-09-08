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

import com.speedment.runtime.config.util.DocumentUtil;
import com.speedment.tool.actions.ProjectTreeComponent;
import com.speedment.tool.actions.menues.ToggleColumnsEnabledAction;
import com.speedment.tool.actions.menues.ToggleTablesEnabledAction;
import com.speedment.tool.config.ColumnProperty;
import com.speedment.tool.config.TableProperty;
import javafx.scene.control.MenuItem;

import java.util.stream.Stream;

/**
 * Default implementation of the {@link ToggleTablesEnabledAction}-interface.
 *
 * @author Emil Forslund
 * @since  3.0.17
 */
public final class ToggleColumnsEnabledActionImpl
extends AbstractToolAction
implements ToggleColumnsEnabledAction {

    @Override
    void installMenuItems(ProjectTreeComponent projectTree) {
        projectTree.installContextMenu(TableProperty.class, (treeCell, node) -> {
            final MenuItem enableColumns  = new MenuItem("Enable All Columns");
            final MenuItem disableColumns = new MenuItem("Disable All Columns");

            enableColumns.setOnAction(ev ->
                DocumentUtil.traverseOver(node)
                    .filter(ColumnProperty.class::isInstance)
                    .forEach(doc -> ((ColumnProperty) doc).enabledProperty().setValue(true))
            );

            disableColumns.setOnAction(ev ->
                DocumentUtil.traverseOver(node)
                    .filter(ColumnProperty.class::isInstance)
                    .forEach(doc -> ((ColumnProperty) doc).enabledProperty().setValue(false))
            );

            return Stream.of(enableColumns, disableColumns);
        });
    }
}