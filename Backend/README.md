# Saving Account (Spring Boot)

## Run locally

1. Configure database settings via environment variables:
   - `DB_URL` (optional)
   - `DB_USERNAME` (optional)
   - `DB_PASSWORD` (recommended)

2. Start the app:
   - Windows: `mvnw.cmd spring-boot:run`
   - macOS/Linux: `./mvnw spring-boot:run`

## Local-only properties (optional)

If you prefer a properties file for local credentials, copy:

`src/main/resources/application-local.properties.example`

to:

`src/main/resources/application-local.properties`

That file is ignored by git.

