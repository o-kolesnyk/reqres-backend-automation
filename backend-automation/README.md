# Backend automation (API tests)

Backend API automation using **Java 21**, **Maven**, **Cucumber**, and **RestAssured**. Functional tests for the Reqres API (user creation, retrieval, validation).

## Project structure

```
backend-automation/
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/com/reqres/
│   │   │   └── config/
│   │   │       └── PropertiesConfig.java   # Loads baseUrl, basePath, apiKey from config
│   │   └── resources/
│   │       └── config.properties          # Environment/config (baseUrl, basePath, etc.)
│   └── test/
│       ├── java/com/reqres/
│       │   ├── client/                     # API clients (RestAssured-based)
│       │   │   ├── BaseApiClient.java     # Base request spec from RestAssuredConfig
│       │   │   └── UserApiClient.java     # User endpoints (get, create, etc.)
│       │   ├── config/
│       │   │   └── RestAssuredConfig.java # Base URI, base path, headers for requests
│       │   ├── context/
│       │   │   └── ScenarioContext.java   # Shared state per scenario (e.g. last response)
│       │   ├── runners/
│       │   │   └── RunCucumberTest.java   # JUnit 5 suite that runs all Cucumber features
│       │   └── stepdefs/
│       │       ├── Hooks.java             # @Before / @After (clear context, attach response on failure)
│       │       ├── common/                 # Shared step definitions
│       │       │   ├── CommonResponseSteps.java
│       │       │   └── CommonValidationSteps.java
│       │       └── users/
│       │           ├── UserCreationSteps.java
│       │           ├── UserRetrievalSteps.java
│       │           └── UserValidationSteps.java
│       └── resources/
│           ├── features/
│           │   └── reqres_users.feature   # Gherkin scenarios (GET/CREATE user)
│           └── junit-platform.properties  # JUnit Platform parallel execution config
└── target/
    └── cucumber/                          # Reports (after mvn test)
        ├── cucumber.html
        └── cucumber.json
```

- **Main (`src/main`):** Configuration and shared config loading; tests depend on this for base URL and API key.
- **Test (`src/test`):** Cucumber BDD glue: feature files, step definitions, API clients, RestAssured config, and a JUnit runner.

## Tech stack

- Java 21
- Maven 3.9+
- Cucumber
- RestAssured
- JUnit 5
- AssertJ

## Run tests

From the **`backend-automation`** directory:

```bash
cd backend-automation
mvn test
```

### Parallel execution

Tests run in parallel by default using **JUnit Platform parallel execution**. Scenarios execute concurrently within a single JVM, which is more efficient than Maven Surefire forks for Cucumber tests.

**Configuration:**
- Parallel execution is configured in `src/test/resources/junit-platform.properties`
- By default, scenarios run concurrently
- Thread count is configurable via `junit.platform.execution.parallel.config.fixed.parallelism`

**To customize parallel threads:**
Edit `junit-platform.properties` and set:
```properties
junit.platform.execution.parallel.config.fixed.parallelism=4
```
Or remove the `parallelism` property to use the number of CPU cores automatically.

**To disable parallel execution:**
Set `junit.platform.execution.parallel.enabled=false` in `junit-platform.properties`.

### Override base URL

```bash
mvn test -DbaseUrl=https://reqres.in
```

## Reports

After a run:

- `target/cucumber/cucumber.html`
- `target/cucumber/cucumber.json`

## Configuration

- **`src/main/resources/config.properties`**: baseUrl, basePath, apiKey, timeout.
  - Override at runtime: `mvn test -DbaseUrl=https://reqres.in`

- **`src/test/resources/junit-platform.properties`**: JUnit Platform parallel execution settings.
  - Controls how many threads run scenarios concurrently
  - Default: parallel execution enabled with configurable thread count

## Extending the framework

| What to add | How |
|-------------|-----|
| **New API domain** | New client in `client/`, new feature in `resources/features/`, new stepdef package under `stepdefs/`. |
| **New endpoints** | New methods on existing client; step definitions that call them and put response in `ScenarioContext`. |
| **New environments** | Extend `config.properties` and `PropertiesConfig`; keep `RestAssuredConfig` as single place for request config. |
| **Shared assertions** | Add steps in `stepdefs/common/`. |

## API testing scope

- **Functional:** HTTP status codes, request–response flows (user creation, retrieval), key response fields, handling of malformed requests.
- **Contract-style:** Presence of service-generated fields (id, createdAt), expected formats (non-empty strings, ISO-8601 timestamps), service metadata.

Performance and load testing live in the sibling folder **`performance-tests/`** (JMeter).
