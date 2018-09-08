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
package com.speedment.common.codegen.internal.java.view.trait;

import com.speedment.common.codegen.Generator;
import com.speedment.common.codegen.Transform;
import com.speedment.common.codegen.model.trait.HasImports;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import static com.speedment.common.codegen.internal.util.ModelTreeUtil.importsOf;
import static com.speedment.common.codegen.util.Formatting.nl;

/**
 * A trait with the functionality to render models with the trait 
 * {@link HasImports}.
 * 
 * @author     Emil Forslund
 * @param <M>  The model type
 * @see        Transform
 */
public interface HasImportsView<M extends HasImports<M>> extends 
    Transform<M, String> {
    
    /**
     * Render the imports-part of the model separated by new-line characters and
     * appended by two new-line characters.
     * 
     * @param gen    the generator
     * @param model  the model
     * @return       the generated code
     */
    default String renderImports(Generator gen, M model) {
        final List<String> customImports         = new ArrayList<>();
        final List<String> standardImports       = new ArrayList<>();
        final List<String> staticImports         = new ArrayList<>();
        final List<String> staticStandardImports = new ArrayList<>();

        gen.onEach(importsOf(model))
            .distinct().sorted()
            .forEachOrdered(line -> {
                if (line.startsWith("import static")) {
                    if (line.startsWith("import static java")) {
                        staticStandardImports.add(line);
                    } else {
                        staticImports.add(line);
                    }
                } else {
                    if (line.startsWith("import java")) {
                        standardImports.add(line);
                    } else {
                        customImports.add(line);
                    }
                }
            });

        final StringJoiner result = new StringJoiner(nl());
        customImports.forEach(result::add);
        if (!customImports.isEmpty() && !standardImports.isEmpty()) result.add("");
        standardImports.forEach(result::add);
        if (!standardImports.isEmpty() && (staticImports.size() + staticStandardImports.size()) > 0) result.add("");
        staticImports.forEach(result::add);
        staticStandardImports.forEach(result::add);
        if (result.length() > 0) result.add(nl());

        return result.toString();
    }
}