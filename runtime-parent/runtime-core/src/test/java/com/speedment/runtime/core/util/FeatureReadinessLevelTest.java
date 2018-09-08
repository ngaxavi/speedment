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
package com.speedment.runtime.core.util;

import com.speedment.runtime.core.exception.SpeedmentException;
import static com.speedment.runtime.core.util.FeatureReadinessLevel.API_STABLE;
import static com.speedment.runtime.core.util.FeatureReadinessLevel.EXPERIMENTAL;
import static com.speedment.runtime.core.util.FeatureReadinessLevel.PREVIEW;
import static com.speedment.runtime.core.util.FeatureReadinessLevel.RELEASE_CANDIDATE;
import static org.junit.Assert.fail;
import org.junit.Test;

/**
 *
 * @author Per Minborg
 */
public class FeatureReadinessLevelTest {

    @Test
    public void testAssertAtLeastSame() {
        for (FeatureReadinessLevel level : FeatureReadinessLevel.values()) {
            level.assertAtLeast(level);
        }
    }

    @Test
    public void testAssertPositive() {
        PREVIEW.assertAtLeast(EXPERIMENTAL);
        API_STABLE.assertAtLeast(EXPERIMENTAL);
        API_STABLE.assertAtLeast(PREVIEW);
        RELEASE_CANDIDATE.assertAtLeast(EXPERIMENTAL);
        RELEASE_CANDIDATE.assertAtLeast(PREVIEW);
        RELEASE_CANDIDATE.assertAtLeast(API_STABLE);
    }

    @Test
    public void testAssertNegative() {
        try {
            EXPERIMENTAL.assertAtLeast(PREVIEW);
            fail();
        } catch (SpeedmentException ignore) {
            // ignore
        }
    }

}
