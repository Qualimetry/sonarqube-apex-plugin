# Qualimetry Apex Analyzer - SonarQube Plugin

[![CI](https://github.com/Qualimetry/sonarqube-apex-plugin/actions/workflows/ci.yml/badge.svg)](https://github.com/Qualimetry/sonarqube-apex-plugin/actions/workflows/ci.yml)

**Author**: The [Qualimetry](https://qualimetry.com) team at SHAZAM Analytics Ltd

A SonarQube plugin that provides static analysis of Salesforce Apex source files (`.cls`, `.trigger`), powered by the same analysis engine as the [Qualimetry Apex Analyzer for VS Code](https://github.com/Qualimetry/vscode-apex-plugin). **283 rules** covering convention, naming, design, security, error handling, performance, testing, complexity, and Salesforce platform best practices. The plugin is licensed under the Apache License 2.0.

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

## Companion: VS Code extension

This plugin shares its analysis engine with the **[Qualimetry Apex Analyzer for VS Code](https://github.com/Qualimetry/vscode-apex-plugin)** - an extension that runs the same rules in the editor. Together they provide a consistent quality standard from the developer's editor through to the build pipeline:

| Tool | When it runs | Purpose |
|------|--------------|---------|
| **VS Code extension** | As you type | Catch issues before you commit |
| **This SonarQube plugin** | On CI/CD build | Enforce quality gate across the team |

Rule keys and severities align so findings are directly comparable.

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
