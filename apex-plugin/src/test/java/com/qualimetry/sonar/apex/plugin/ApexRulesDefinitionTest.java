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

import com.qualimetry.sonar.apex.analyzer.checks.CheckList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.sonar.api.server.rule.RulesDefinition;

import static org.assertj.core.api.Assertions.assertThat;

class ApexRulesDefinitionTest {

    private static RulesDefinition.Repository repository;

    @BeforeAll
    static void setUp() {
        RulesDefinition.Context context = new RulesDefinition.Context();
        new ApexRulesDefinition().define(context);
        repository = context.repository(CheckList.REPOSITORY_KEY);
    }

    @Test
    void shouldCreateRepository() {
        assertThat(repository).isNotNull();
    }

    @Test
    void shouldHaveCorrectRepositoryKey() {
        assertThat(repository.key()).isEqualTo("qualimetry-apex");
    }

    @Test
    void shouldHaveCorrectRepositoryName() {
        assertThat(repository.name()).isEqualTo("Qualimetry Apex");
    }

    @Test
    void shouldHaveCorrectLanguage() {
        assertThat(repository.language()).isEqualTo("apex");
    }

    @Test
    void shouldLoadAllRules() {
        assertThat(repository.rules()).hasSize(CheckList.getAllChecks().size());
    }

    @Test
    void shouldHaveHtmlDescriptionForEveryRule() {
        for (RulesDefinition.Rule rule : repository.rules()) {
            assertThat(rule.htmlDescription())
                    .as("Missing HTML description for rule: " + rule.key())
                    .isNotNull()
                    .isNotEmpty();
        }
    }

    @Test
    void shouldHaveSeverityForEveryRule() {
        for (RulesDefinition.Rule rule : repository.rules()) {
            assertThat(rule.severity())
                    .as("Missing severity for rule: " + rule.key())
                    .isNotNull()
                    .isNotEmpty();
        }
    }

    @Test
    void shouldHaveHumanReadableNameForEveryRule() {
        for (RulesDefinition.Rule rule : repository.rules()) {
            assertThat(rule.name())
                    .as("Rule name should be human-readable, not kebab-case key: " + rule.key())
                    .isNotNull()
                    .isNotEqualTo(rule.key());
            assertThat(rule.name())
                    .as("Rule name should be a multi-word title: " + rule.key())
                    .contains(" ");
        }
    }

    @Test
    void shouldHaveTagsForEveryRule() {
        for (RulesDefinition.Rule rule : repository.rules()) {
            assertThat(rule.tags())
                    .as("Rule should have tags: " + rule.key())
                    .isNotNull()
                    .isNotEmpty();
        }
    }
}
