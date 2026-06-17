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
import org.junit.jupiter.api.Test;
import org.sonar.api.server.profile.BuiltInQualityProfilesDefinition;
import org.sonar.api.server.profile.BuiltInQualityProfilesDefinition.BuiltInQualityProfile;

import static org.assertj.core.api.Assertions.assertThat;

class ApexQualityProfileWayTest {

    @Test
    void shouldCreateProfile() {
        BuiltInQualityProfilesDefinition.Context context = new BuiltInQualityProfilesDefinition.Context();
        new ApexQualityProfileWay().define(context);

        BuiltInQualityProfile profile = context.profile("apex", "Qualimetry Way");
        assertThat(profile).isNotNull();
    }

    @Test
    void shouldNotBeDefaultProfile() {
        BuiltInQualityProfilesDefinition.Context context = new BuiltInQualityProfilesDefinition.Context();
        new ApexQualityProfileWay().define(context);

        BuiltInQualityProfile profile = context.profile("apex", "Qualimetry Way");
        assertThat(profile.isDefault()).isFalse();
    }

    @Test
    void shouldActivateAllRules() {
        BuiltInQualityProfilesDefinition.Context context = new BuiltInQualityProfilesDefinition.Context();
        new ApexQualityProfileWay().define(context);

        BuiltInQualityProfile profile = context.profile("apex", "Qualimetry Way");
        assertThat(profile.rules()).hasSize(CheckList.getAllRuleKeys().size());
    }

    @Test
    void shouldActivateRulesFromCorrectRepository() {
        BuiltInQualityProfilesDefinition.Context context = new BuiltInQualityProfilesDefinition.Context();
        new ApexQualityProfileWay().define(context);

        BuiltInQualityProfile profile = context.profile("apex", "Qualimetry Way");
        assertThat(profile.rules()).allSatisfy(rule ->
                assertThat(rule.repoKey()).isEqualTo(CheckList.REPOSITORY_KEY));
    }

    @Test
    void shouldActivateMoreRulesThanDefaultProfile() {
        BuiltInQualityProfilesDefinition.Context context = new BuiltInQualityProfilesDefinition.Context();
        new ApexQualityProfile().define(context);
        new ApexQualityProfileWay().define(context);

        BuiltInQualityProfile defaultProfile = context.profile("apex", "Qualimetry Apex");
        BuiltInQualityProfile wayProfile = context.profile("apex", "Qualimetry Way");
        assertThat(wayProfile.rules().size()).isGreaterThanOrEqualTo(defaultProfile.rules().size());
    }
}
