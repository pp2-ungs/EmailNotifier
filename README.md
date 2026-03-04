# EmailNotifier

A Java library module that provides email notification functionality for the **TASkOcupado** task management system. It integrates with the core application via the Observer pattern to send email notifications whenever a task is assigned to a user.

## Overview

EmailNotifier is a NetBeans J2SE library project built with Apache Ant. It acts as a plugin component that listens for task-assignment events emitted by the `TASkOcupadoCore` module and delivers personalized email notifications to the assigned user through Gmail's SMTP service.

## Project Structure

```
EmailNotifier/
├── src/
│   └── email/
│       ├── Email.java          # Low-level SMTP email sender
│       ├── EmailFinder.java    # Loads user → email mappings from JSON
│       └── EmailNotifier.java  # Observer that reacts to task-assignment events
├── lib/                        # Third-party JAR dependencies
├── nbproject/                  # NetBeans project metadata and build configuration
├── build.xml                   # Apache Ant build script
└── manifest.mf                 # JAR manifest
```

## How It Works

1. **Event subscription** – `EmailNotifier` implements the `Observer` interface from `TASkOcupadoCore`. When registered, it receives a notification every time a task is assigned to a person.

2. **Email address lookup** – On startup, `EmailFinder` reads an `Email.json` file (located in the application's resources directory) and builds a `Map<String, String>` that maps each person's name to their email address.

3. **Asynchronous delivery** – When an assignment event arrives, `EmailNotifier` looks up the recipient's address and spawns a background thread to send the email, so the main application thread is never blocked.

4. **SMTP transport** – `Email` composes a `MimeMessage` and delivers it through Gmail's SMTP server (`smtp.gmail.com`, port 465, SSL/TLS).

## Configuration

### Email address mapping (`Email.json`)

Place an `Email.json` file in the resources directory defined by `Settings.RESOURCES` in `TASkOcupadoCore`. The file must be a JSON object mapping each user's display name to their email address:

```json
{
  "Alice": "alice@example.com",
  "Bob":   "bob@example.com"
}
```

### SMTP credentials

The sender credentials are currently defined in `Email.java`:

| Setting | Value |
|---------|-------|
| Sender address | `taskocupado@gmail.com` |
| SMTP host | `smtp.gmail.com` |
| SMTP port | `465` (SSL) |
| Authentication | Gmail App Password |

> **Note:** Consider moving the credentials to an external configuration file or environment variable rather than hardcoding them in source.

## Dependencies

| Library | Version | Purpose |
|---------|---------|---------|
| `jakarta.mail-api` | 2.1.3 | Jakarta Mail API |
| `jakarta.mail` | 2.0.3 | Jakarta Mail implementation |
| `angus-activation` | 2.0.2 | MIME type activation |
| `gson` | 2.11.0 | JSON parsing for `Email.json` |
| `TASkOcupadoCore` | — | Provides `Observer` interface and `Settings` constants |

All JAR files are stored in the `lib/` directory.

## Building

The project uses Apache Ant via NetBeans' standard build system.

```bash
# Compile and package
ant jar

# Clean build artifacts
ant clean
```

You can also open the project directly in **NetBeans** and use the IDE's Build / Clean-and-Build commands.

## Requirements

- Java 22 or later
- Apache Ant
- `TASkOcupadoCore` module available on the classpath (referenced in `nbproject/project.properties`)
