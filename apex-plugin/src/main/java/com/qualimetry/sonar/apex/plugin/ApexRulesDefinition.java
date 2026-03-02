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
import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.api.server.rule.RulesDefinitionAnnotationLoader;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

/**
 * Defines the Apex rules repository for SonarQube.
 */
public class ApexRulesDefinition implements RulesDefinition {

    private static final String RESOURCE_BASE =
            "/com/qualimetry/sonar/apex/analyzer/checks/";

    @Override
    public void define(Context context) {
        var repo = context.createRepository(CheckList.REPOSITORY_KEY, ApexLanguage.KEY)
                .setName(CheckList.REPOSITORY_NAME);

        new RulesDefinitionAnnotationLoader()
                .load(repo, CheckList.getAllChecks().toArray(new Class<?>[0]));

        for (var rule : repo.rules()) {
            String htmlPath = RESOURCE_BASE + rule.key() + ".html";
            try (InputStream is = getClass().getResourceAsStream(htmlPath)) {
                if (is != null) {
                    rule.setHtmlDescription(readStream(is));
                }
            } catch (java.io.IOException e) {
                throw new IllegalStateException("Failed to read rule description: " + htmlPath, e);
            }
        }

        repo.done();
    }

    private static String readStream(InputStream is) {
        try (var reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
            return reader.lines().collect(Collectors.joining("\n"));
        } catch (Exception e) {
            throw new IllegalStateException("Failed to read rule description", e);
        }
    }
}
