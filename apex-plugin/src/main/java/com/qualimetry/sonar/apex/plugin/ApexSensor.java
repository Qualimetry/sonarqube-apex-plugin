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
import com.qualimetry.sonar.apex.analyzer.visitor.BaseCheck;
import org.sonar.api.batch.fs.FileSystem;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.batch.rule.CheckFactory;
import org.sonar.api.batch.rule.Checks;
import org.sonar.api.batch.sensor.Sensor;
import org.sonar.api.batch.sensor.SensorContext;
import org.sonar.api.batch.sensor.SensorDescriptor;
import org.sonar.api.batch.sensor.issue.NewIssue;
import org.sonar.api.batch.sensor.issue.NewIssueLocation;
import org.sonar.api.rule.RuleKey;
import org.sonar.check.Rule;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Sensor that runs Apex analysis on .cls and .trigger files.
 */
public class ApexSensor implements Sensor {

    private final FileSystem fileSystem;
    private final CheckFactory checkFactory;

    public ApexSensor(FileSystem fileSystem, CheckFactory checkFactory) {
        this.fileSystem = fileSystem;
        this.checkFactory = checkFactory;
    }

    @Override
    public void describe(SensorDescriptor descriptor) {
        descriptor.onlyOnLanguage(ApexLanguage.KEY)
                .name("Apex Analyzer Sensor")
                .onlyOnFileType(InputFile.Type.MAIN);
    }

    @Override
    public void execute(SensorContext context) {
        Checks<BaseCheck> checks = checkFactory.<BaseCheck>create(CheckList.REPOSITORY_KEY)
                .addAnnotatedChecks((Iterable<?>) CheckList.getAllChecks());

        List<BaseCheck> activeChecks = new ArrayList<>(checks.all());
        if (activeChecks.isEmpty()) {
            return;
        }

        Map<String, RuleKey> ruleKeyMap = new HashMap<>();
        for (BaseCheck check : activeChecks) {
            Rule r = check.getClass().getAnnotation(Rule.class);
            if (r != null) {
                RuleKey rk = checks.ruleKey(check);
                if (rk != null) {
                    ruleKeyMap.put(r.key(), rk);
                }
            }
        }

        for (InputFile inputFile : fileSystem.inputFiles(
                fileSystem.predicates().and(
                        fileSystem.predicates().hasType(InputFile.Type.MAIN),
                        fileSystem.predicates().hasLanguage(ApexLanguage.KEY)))) {

            String rawContent;
            try (InputStream is = inputFile.inputStream()) {
                rawContent = new String(is.readAllBytes(), StandardCharsets.UTF_8);
            } catch (IOException e) {
                continue;
            }

            for (BaseCheck check : activeChecks) {
                check.clearIssues();
                check.scan(inputFile, rawContent);

                for (BaseCheck.Issue issue : check.getIssues()) {
                    RuleKey ruleKey = ruleKeyMap.get(issue.ruleKey());
                    if (ruleKey == null) continue;
                    NewIssue newIssue = context.newIssue().forRule(ruleKey);
                    NewIssueLocation loc = newIssue.newLocation().on(inputFile).message(issue.message());
                    if (issue.line() > 0) {
                        loc.at(inputFile.selectLine(issue.line()));
                    }
                    newIssue.at(loc);
                    newIssue.save();
                }
            }
        }
    }
}
