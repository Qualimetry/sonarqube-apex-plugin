# Changelog

## [Unreleased]

- (None.)

## [1.9.17] - 2026-06-23

- Renamed the rules repository to "Qualimetry" so SonarQube no longer shows the language label twice.

## [1.9.16] - 2026-06-18

- Configurable rules (naming length/density, complexity and size thresholds, naming patterns) now expose native SonarQube rule parameters - editable in the UI and synced to the IDE in connected mode - instead of static documentation.

## [1.9.15] - 2026-06-17

- Added full-text, per-rule documentation pages for every rule.

## [1.9.13] - 2026-06-12

- Version alignment with IntelliJ plugin SonarQube import improvements.

## [1.9.11] - 2026-03-19

- Version alignment with IntelliJ plugin signing update.

## [1.9.10] - 2026-03-19

- Include README in published extension package.

## [1.9.9] - 2026-03-19

283 analysis rules covering convention, design, error handling, security, performance, testing, Salesforce best practices, and complexity metrics.

- 219 rules enabled in the default "Qualimetry Apex" quality profile.
- 64 additional rules available in the "Qualimetry Way" profile (overlap with SonarQube built-in analysis).
- Per-rule settings panel with enable/disable, severity override, and search filter under Settings > Tools.
- **Import from SonarQube** — fetch active rules from a SonarQube quality profile.
- Security rules with CWE and OWASP references for SOQL injection, XSS, SSRF, cryptographic misuse, and data exposure.
- Complexity metrics: cyclomatic complexity, cognitive complexity, NPath, NCSS, and coupling analysis.
- Governor limit enforcement: SOQL/DML in loops, @Future in loops, batch scope limits.
- Comprehensive HTML documentation with noncompliant and compliant Apex examples for every rule.

## [1.9.4] - 2026-03-02

283 analysis rules covering convention, design, error handling, security, performance, testing, Salesforce best practices, and complexity metrics.

- 219 rules enabled in the default "Qualimetry Apex" quality profile.
- 64 additional rules available in the "Qualimetry Way" profile (overlap with SonarQube built-in analysis).
- Security rules with CWE and OWASP references for SOQL injection, XSS, SSRF, cryptographic misuse, and data exposure.
- Complexity metrics: cyclomatic complexity, cognitive complexity, NPath, NCSS, and coupling analysis.
- Governor limit enforcement: SOQL/DML in loops, @Future in loops, batch scope limits.
- Comprehensive HTML documentation with noncompliant and compliant Apex examples for every rule.

## [1.0.0] - 2026-03-01

First public release.

- Static analysis of Salesforce Apex source files (`.cls`, `.trigger`) in SonarQube.
- Placeholder rule set for Salesforce Apex static analysis in SonarQube.
- Rule repository **qualimetry-apex** with quality profile (Qualimetry Apex).
