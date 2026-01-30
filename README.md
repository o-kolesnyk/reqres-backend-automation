# reqres-backend-automation

Repository for **backend API automation** and **performance tests** around the [Reqres](https://reqres.in) API. Two independent areas:

- **`backend-automation/`** — functional API tests (Maven, Cucumber, RestAssured).
- **`performance-tests/`** — load tests (JMeter).

## Repository structure

```
reqres-backend-automation/
├── backend-automation/          # API tests (Maven + Cucumber + RestAssured)
│   ├── pom.xml
│   ├── src/
│   │   ├── main/                # Config, properties
│   │   └── test/                # Features, stepdefs, clients, runners
│   └── README.md
├── performance-tests/           # Performance / load tests (JMeter)
│   ├── jmeter/
│   │   └── users_create.jmx
│   └── README.md
├── Jenkinsfile                  # CI: runs backend-automation tests, publishes Cucumber report
└── README.md                    # This file
```

## Quick start

| Area | Command | From |
|------|---------|------|
| **API tests** | `mvn test` | `backend-automation/` |
| **Performance tests** | `jmeter -n -t performance-tests/jmeter/users_create.jmx -l ...` | Repo root |

## Details

- **Backend automation:** See [backend-automation/README.md](backend-automation/README.md) for structure, run instructions, and how to extend.
- **Performance tests:** See [performance-tests/README.md](performance-tests/README.md) for JMeter setup and how to run the create-user load test.

Both folders are self-contained; you can run API tests without JMeter and performance tests without Maven.
