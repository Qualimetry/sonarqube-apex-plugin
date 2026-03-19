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
import org.sonar.api.server.profile.BuiltInQualityProfilesDefinition;

/**
 * Built-in quality profile that activates all Qualimetry Apex rules,
 * including those that may overlap with SonarQube's default Apex profile.
 */
public class ApexQualityProfileWay implements BuiltInQualityProfilesDefinition {

    static final String PROFILE_NAME = "Qualimetry Way";

    @Override
    public void define(Context context) {
        var profile = context.createBuiltInQualityProfile(PROFILE_NAME, CheckList.LANGUAGE_KEY);
        profile.setDefault(false);
        for (String ruleKey : CheckList.getAllRuleKeys()) {
            profile.activateRule(CheckList.REPOSITORY_KEY, ruleKey);
        }
        profile.done();
    }
}
