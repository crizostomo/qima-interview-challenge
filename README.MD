# QIMA Challenge


This repository contains the QIMA Challenge project, which includes both backend and frontend components. The backend is built with Java and utilizes Maven for dependency management, while the frontend is developed with Angular. Docker Compose is used to orchestrate the services.

## Index

- [Prerequisites](#prerequisites)
- [Project Structure](#project-structure)
- [Setting Up the Backend](#setting-up-the-backend)
- [Running the Backend](#running-the-backend)
- [Database Migration and Auto-Creation](#database-migration-and-auto-creation)
- [Setting Up the Frontend](#setting-up-the-frontend)
- [Accessing the Application](#accessing-the-application)
- [Stopping the Application](#stopping-the-application)
- [Notes](#notes)
- [Points for Improvement](#points-for-improvement)
- [Highlights](#highlights)

## Prerequisites

Before running the project, ensure you have the following installed:

- [Docker](https://docs.docker.com/get-docker/)
- [Docker Compose](https://docs.docker.com/compose/install/)
- [Node.js 22 and npm](https://nodejs.org/) (for frontend development)

## Project Structure

- `frontend/`: Contains the React frontend application.
- `docker-compose.yml`: Docker Compose configuration file to orchestrate the services.
- `build.sh`: Shell script to build the backend application.
- `start.sh`: Shell script to start the services using Docker Compose.
- `stop.sh`: Shell script to stop the services.

## Setting Up the Backend

1. **In the Source build the backend application:**

   Use the provided `build.sh` script to build the backend:

   ```bash
   ./build.sh
   ```

   This script will execute Maven commands to package the application.

## Running the Backend

1. **Start the backend service:**

   ```bash
   ./start.sh
   ```

   This script will start the backend application using Docker Compose.

## Database Migration and Auto-Creation

The backend supports automatic database creation and migration using Liquibase.

1. **Ensure the database container is running:**

   If using Docker Compose, the database should start automatically. Otherwise, ensure your database is running manually.

2. **Apply migrations:**

   The application will automatically apply migrations on startup. If needed, manually trigger flyway updates:

   ```bash
   mvn flyway:update
   ```

   This ensures the database schema is up-to-date.

## Setting Up the Frontend

1. **Navigate to the frontend directory:**

   ```bash
   cd frontend
   ```

2. **Install dependencies:**

   ```bash
   npm install
   ```

3. **Run the frontend application:**

   ```bash
   ng serve
   ```

   This will start the development server and serve the Angular application.

## Accessing the Application

Once the services are up and running, access the frontend application by navigating to `http://localhost:4200` in your web browser.

## Stopping the Application

To stop the running services, use the provided `stop.sh` script:

```bash
./stop.sh
```

This script will stop and remove the Docker containers associated with the application.

## Notes

- Ensure that the ports defined in the `docker-compose.yml` file do not conflict with other services running on your machine.
- The frontend is built using Angular 16 and Angular Material.
- If you encounter any issues, check the logs of the respective services using Docker commands or refer to the documentation of the tools and frameworks used.


## Highlights

- **Modern Tech Stack:** Uses Java with Spring Boot for the backend and Angular 16 with Angular Material for the frontend.
- **Containerized Deployment:** Utilizes Docker and Docker Compose for easy setup and deployment.
- **Shell Scripts for Automation:** Includes `build.sh`, `start.sh`, and `stop.sh` for seamless backend management.
- **Scalability Potential:** With improvements, the project can be adapted for larger-scale applications.
- **User-Friendly UI:** The frontend is designed with Ant Design components for a clean and professional user experience.
- **Automatic Database Migration:** Uses flyway to automatically create and migrate the database schema.
- **CI flow based on Github actions, this is a WIP since the version is having some issues

By following these steps, you should be able to set up and run the QIMA Challenge project successfully.

