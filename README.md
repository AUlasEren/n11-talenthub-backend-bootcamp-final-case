# GeoTaste Microservice

This microservice is a Spring Boot application that allows users to make reviews about restaurants and facilitates the management of users and restaurants. It also provides recommendations to users based on their locations and the ratings of restaurants.

## Features
- **User Management**: Users can be added, updated, and deleted with their name, surname, latitude, and longitude information.
- **Review Management**: Users can post, update, and delete reviews for restaurants with a score between 1 and 5.
- **Restaurant Recommendations**: Users receive 3 restaurant recommendations within 40 km based on their location and the restaurant ratings.
- **Restaurant Management**: Restaurants can be added, updated, deleted, and listed with latitude and longitude information. Restaurant information is stored and queried in Apache Solr.

## Setup
### Requirements
- Java 11 or later.
- Docker and Docker Compose.
- Docker images for Apache Solr, PostgreSQL, and Kafka.

### Installation Steps
1. Start the necessary Docker containers:
    ```bash
    docker-compose up -d 
    ```

## Usage
### User Operations
- Add User: `POST /api/v1/users`(Port 8095)
- Delete User: `DELETE /api/v1/users/{id}`(Port 8095)
- Update User: `PUT /api/v1/users/update_user`(Port 8095)

### Review Operations
- Add Review: `POST /api/v1/user_reviews`(Port 8095)
- Delete Review: `DELETE /api/v1/user_reviews/{id}`(Port 8095)
- Update Review: `PATCH /api/v1/user_reviews/{id}`(Port 8095)

### Restaurant Operations
- Add Restaurant: `POST /api/v1/restaurants`(Port 8096)
- Delete Restaurant: `DELETE /api/v1/restaurants/{id}`(Port 8096)
- Update Restaurant: `PUT /api/v1/restaurants/update_restaurant`(Port 8096)
- List Restaurants: `GET /api/v1/restaurants`(Port 8096)

### Restaurant Recommendations
- Get Recommendations: `GET /api/v1/users/{userId}/restaurant-recommendations`(Port 8095)

## Tests
Unit and integration tests are included in the project, ensuring all tests are in working order.
### Note
If you want the User Controller and User Review Controller test classes to run, you can uncomment the User Review and User Controller test classes after running docker compose up -d --build. The test classes initially encounter an issue because they can’t find a PostgreSQL instance to connect to when the application first starts up in the Docker container. However, this issue disappears after a while.

## Documentation
API documentation created with Swagger or OpenAPI explains all endpoints and their use.

## Logging and Error Handling
An effective logging mechanism is established across the system, and best practices for exception handling are followed.# n11-talenthub-backend-bootcamp-final-case
