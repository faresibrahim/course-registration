# spring-boot-project

Full-stack starter: Spring Boot (Java 17, Maven) backend + React (Vite) frontend.

```
backend/   Spring Boot 4.1 app (spring-boot-starter-web, devtools) — runs on :8080
frontend/  React app built with Vite — dev server on :5173
```

## Backend

```bash
cd backend
./mvnw spring-boot:run
```

On Windows PowerShell use `.\mvnw.cmd spring-boot:run`. Requires JDK 17+ (`JAVA_HOME` must point to it).

## Frontend

```bash
cd frontend
npm install
npm run dev
```

The Vite dev server proxies any request to `/api/*` to `http://localhost:8080`, so the React app can call the backend with relative URLs (e.g. `fetch('/api/hello')`).
