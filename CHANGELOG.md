# Changelog

## [Unreleased]

- (None.)

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
