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
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.speedment.runtime.core.internal.code.model.java;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 *
 * @author pemi
 */
public class SimpleModelTest extends SimpleModel {
    
    @Test
    public void testModel() {
        assertNotNull(project);
        assertNotNull(dbms);
        assertNotNull(schema);
        assertNotNull(table);
        assertNotNull(column);
        assertNotNull(pkColumn);
    }
       
}
