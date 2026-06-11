# Qualimetry Apex Analyzer - SonarQube Plugin

[![CI](https://github.com/Qualimetry/sonarqube-apex-plugin/actions/workflows/ci.yml/badge.svg)](https://github.com/Qualimetry/sonarqube-apex-plugin/actions/workflows/ci.yml)

A SonarQube plugin that provides static analysis of Salesforce Apex source files (`.cls`, `.trigger`) as part of your CI/CD quality gate.

Powered by the same analysis engine as the [Qualimetry Apex Analyzer for VS Code](https://github.com/Qualimetry/vscode-apex-plugin) and the [Qualimetry Apex Analyzer for IntelliJ](https://github.com/Qualimetry/intellij-apex-plugin).

## Features

- **283 rules** covering convention, naming, design, security, error handling, performance, testing, complexity, and Salesforce platform best practices.
- **Quality profiles** — two built-in profiles (default and full coverage) with full customization in the SonarQube UI.
- **Consistent rule keys** — rule keys and severities align with the VS Code and IntelliJ plugins so findings are directly comparable.
- **SonarCloud compatible** — works with SonarCloud when the Apex language is available.

## Rule categories

| Category | Examples |
|----------|----------|
| Convention | Class/method/variable naming, braces, indentation |
| Design | Boolean parameters, law of demeter, collapsible if, ternary |
| Security | SOQL injection, field-level security, XSS, CSRF |
| Error Handling | Empty catch, generic catch, preserve stack trace |
| Performance | SOQL/DML in loops, eager describe, wrapper conversion |
| Complexity | Cyclomatic, cognitive, NCSS, coupling, god class |
| Testing | Assertions required, no SeeAllData, test method coverage |
| Salesforce | One trigger per object, no trigger logic, API versions |
| Unused Code | Unused variables, parameters, private fields/methods |
| Documentation | Apexdoc required, comment density |

## Compatibility

- **SonarQube 10.x or later** (plugin API version used at build time).
- Also compatible with **SonarCloud** when the Apex language is available.

## Installation

1. Download the latest `qualimetry-apex-plugin-<version>.jar` from [Releases](https://github.com/Qualimetry/sonarqube-apex-plugin/releases).
2. Place the JAR in `SONARQUBE_HOME/extensions/plugins/`.
3. Restart SonarQube.
4. In **Quality Profiles**, select **Qualimetry Apex** as the profile for the Apex language.

## Quality profiles

The plugin provides two built-in profiles:

- **Qualimetry Apex** — Primary rules enabled by default; excludes rules that overlap with SonarQube's built-in Apex analysis.
- **Qualimetry Way** — All 283 rules enabled; for teams that prefer full Qualimetry coverage.

You can customize rules (enable/disable, severity, parameters) in SonarQube under **Quality Profiles**. Rules use the repository key `qualimetry-apex` and the `qa-` prefix.

## Also available

The same analysis engine powers editor extensions for real-time feedback:

- **[VS Code extension](https://github.com/Qualimetry/vscode-apex-plugin)** — catch issues as you type, before you commit.
- **[IntelliJ plugin](https://github.com/Qualimetry/intellij-apex-plugin)** — real-time analysis in JetBrains IDEs and Qodana CI/CD.

Rule keys and severities align across all three tools so findings are directly comparable.

## Building from source

Requires JDK 17+ and Maven 3.6+.

```bash
mvn clean package -DskipTests
```

The packaged plugin JAR is at `apex-plugin/target/qualimetry-apex-plugin-<version>.jar`.

To run the full test suite:

```bash
mvn clean verify
```

## CI and releases

The [CI](https://github.com/Qualimetry/sonarqube-apex-plugin/actions/workflows/ci.yml) workflow runs on every push and pull request to `main`: it builds and runs tests on Java 17, and uploads the plugin JAR as an artifact. A **GitHub Release** (tag + release notes + JAR) is created **only when a commit message starts with `release:`** (e.g. `release: 1.0.0`).

## Contributing

Issues and feature requests are welcome. This project does not accept pull requests, commits, or other code contributions from third parties; the repository is maintained by the Qualimetry team only.

## License

This plugin is licensed under the [Apache License, Version 2.0](https://www.apache.org/licenses/LICENSE-2.0).

Copyright 2026 SHAZAM Analytics Ltd
