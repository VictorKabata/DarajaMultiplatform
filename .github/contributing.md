# Contribution Guidelines

Thank you for your interest in contributing to Daraja Multiplatform! This project is aims to streamline the integration of M-Pesa services across multiple platforms by providing a simplified interface to the [Daraja API](https://developer.safaricom.co.ke/APIs). All kinds of contributions are welcome, whether it's fixing bugs, adding new features, or improving documentation.

## Getting Started

To get started with contributing to this project:

- Familiarize yourself with the project by going over the project [documentation](https://victorkabata.github.io/DarajaMultiplatform/) and exploring the [codebase](https://github.com/VictorKabata/DarajaMultiplatform/tree/main).
- Click the ["Fork"](https://github.com/VictorKabata/DarajaMultiplatform/fork) button on the Daraja Multiplatform repository page on GitHub. This creates a copy of the repository in your own account.
- Clone your forked repository to your local machine using Git.
```curl
git clone https://github.io/your-username/DarajaMultiplatform.git
```
- Follow the [project's setup instructions]() to set up the development enviroment.

## How to Contribute

### Reporting Bugs

If you encounter a bug, please [create a new issue](https://github.com/VictorKabata/DarajaMultiplatform/issues/new/choose) and include as much detail as possible, such as:

- Steps to reproduce the issue.
- Expected outcome vs actual outcome.
- Relevant screenshots or error logs (if applicable).

### Suggesting Features
For feature requests, [submit a new issue](https://github.com/VictorKabata/DarajaMultiplatform/issues/new/choose) and outline:
- The feature you’re proposing.
- Why it would improve the project.
- Any ideas on implementation(if available).

### Making Changes
If you'd like to contribute code:
- Open an issue to discuss your idea and get feedback before you begin.
- Create a new branch for your specific contribution with a descriptive name that reflects the changes you plan to make.
```bash
git checkout -b feature/your-feature-name
```

- Make your changes: Write clear, concise, and well-documented code. Use clear and concise commit messages that describe the changes you've made in each commit to maintain a clean commit history.
- Ensure your changes don't break any existing functionalities by running the tests provided in the project and adding new test cases.
- Push your changes to your forked repository branch and open a pull request from your branch to the __develop__ branch of the upstream repository. All pull requests description must follow this [PR template](https://github.com/VictorKabata/DarajaMultiplatform/blob/develop/.github/pull_request_template.md).

### Code Style

This project uses ktLint and detekt to enforce code style and quality standards in all Kotlin files. Following these standards helps maintain a consistent codebase and makes the code easier to read and review.

Run the lint check commands locally before commiting changes to catch any linting issues early

```bash
./gradlew :daraja:ktlintFormat
```

```bash
./gradlew :daraja:detekt
```

## License
By contributing, you agree that your contributions will be licensed under the same license as the project.
