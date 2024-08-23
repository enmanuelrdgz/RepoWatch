# RepoWatch

**RepoWatch** is a command-line application designed to monitor and display the recent activity of any GitHub user. It fetches and summarizes the latest GitHub events for a specified user, such as pushes, repository creations, pull requests, and more.

## Features

- **Fetch Recent Events:** Retrieve the most recent activities of any GitHub user.
- **Event Summary:** Provides a summary of different types of activities including push events, repository creations, pull requests, issues, and more.
- **Customizable Event Limit:** Users can specify how many recent events to display using a command-line argument.

## Prerequisites

- **Java 11** or higher.
- **Maven** (optional, if you plan to build the project using Maven).

## Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/repowatch.git
2. Navigate to the project directory:
   ```bash
   cd repowatch
3. Compile the project:
   ```bash
   mvn compile
## Usage
Running the Application
You can run the application directly using Maven with the following command:
```bash
mvn exec:java -Dexec.mainClass="org.example.Main" -Dexec.args="<GitHub-username>"
```
By default, this command shows the last 6 events in the user's activity.

```
Customizing the Event Limit
You can specify the number of events to display (up to 100) using the following command:

```bash
mvn exec:java -Dexec.mainClass="org.example.Main" -Dexec.args="<GitHub-username> --limit <number-of-events>"
```
## Using the Executable JAR
To simplify usage, you can use the provided repowatch script (for Windows) if it has been set up as described in the Usage section.

### On Windows
Run the application with:

```batch
repowatch <GitHub-username>
```
Or with a custom event limit:

```batch
repowatch <GitHub-username> --limit <number-of-events>
