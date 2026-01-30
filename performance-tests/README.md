# Performance tests (JMeter)

This folder contains JMeter test plans for load and performance testing of the ReqRes API. It sits next to **`backend-automation/`** (API tests) and is independent:

- Performance tests can be run independently (no Maven/Cucumber required)
- JMeter scenarios and tuning stay in one place
- CI can run API tests and performance tests in different jobs/stages if needed

## Layout

```
performance-tests/
├── jmeter/
│   └── users_create.jmx   # POST /api/users (create user) load test
└── README.md
```

## Prerequisites

- **JMeter 5.x** (e.g. 5.6.3 or later)  
  - Download: [Apache JMeter](https://jmeter.apache.org/download_jmeter.cgi)  
  - Or install via package manager (e.g. `brew install jmeter` on macOS)

## Running tests

### GUI (design / debug)

1. Open JMeter.
2. **File → Open** and select `jmeter/users_create.jmx`.
3. Adjust the Thread Group (number of threads, ramp-up, loops) if needed.
4. Click **Run → Start** (Ctrl+R).

### Command line (CI / headless)

Use the command that matches **where you run it from** so paths don’t nest and reports are writable.

**Option A — from the repository root** (recommended for CI):

```bash
mkdir -p performance-tests/results
jmeter -n -t performance-tests/jmeter/users_create.jmx -l performance-tests/results/users_create.jtl -e -o performance-tests/reports
```

**Option B — from inside `performance-tests/`**:

```bash
cd performance-tests
mkdir -p results
jmeter -n -t jmeter/users_create.jmx -l results/users_create.jtl -e -o reports
```

In both cases the HTML report ends up under `performance-tests/reports/`.

- `-n`: non-GUI mode  
- `-t`: test plan file  
- `-l`: CSV results file  
- `-e -o`: generate HTML report (JMeter creates the output folder)

## Test plan: `users_create.jmx`

- **Endpoint:** `POST https://reqres.in/api/users`
- **Body:** JSON with `email`, `first_name`, `last_name` (email uses a random string to reduce duplicates).
- **Defaults:** 5 threads, 5 s ramp-up, 10 iterations per thread (50 requests total).
- **Variables (Test Plan → User Defined Variables):**
  - `BASE_HOST`: `reqres.in`
  - `BASE_PATH`: `/api`

Change these variables to point at another host/path without editing the HTTP requests.

## Optional: add to `.gitignore`

If you run from the repo and don’t want to commit results or reports:

```
performance-tests/results/
performance-tests/reports/
```

## Relation to API tests

- **API tests** (e.g. in `src/`): functional checks with Maven + Cucumber + RestAssured.
- **Performance tests** (this folder): JMeter-only; no dependency on the main framework.

You can run both in the same repo: API tests for correctness, JMeter for load and performance.
