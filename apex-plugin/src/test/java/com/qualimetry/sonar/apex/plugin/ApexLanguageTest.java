/*
 * Copyright 2026 SHAZAM Analytics Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.qualimetry.sonar.apex.plugin;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ApexLanguageTest {

    private final ApexLanguage language = new ApexLanguage();

    @Test
    void shouldHaveCorrectKey() {
        assertThat(language.getKey()).isEqualTo("apex");
    }

    @Test
    void shouldHaveCorrectName() {
        assertThat(language.getName()).isEqualTo("Apex");
    }

    @Test
    void shouldHaveClsAndTriggerSuffixes() {
        assertThat(language.getFileSuffixes()).containsExactly(".cls", ".trigger");
    }

    @Test
    void shouldReturnDefensiveCopyOfSuffixes() {
        String[] first = language.getFileSuffixes();
        String[] second = language.getFileSuffixes();
        assertThat(first).isNotSameAs(second);
    }
}
