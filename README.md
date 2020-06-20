# BirthdayRestAPI

**Description**: REST API to check birthdays.

This project was generated with https://start.spring.io/

* Requirements:
  * Java 1.8
  * mvn

## Development server
Run this commands to deploy local development server.
 * `mvn clean install`
 * `java -jar target/test-0.0.1-SNAPSHOT.jar`
 
or direclty using mvn:

 * `mvn spring-boot:run`
 
 ## Unit tests

For run tests:
 * `mvn clean test`

## Endpoint

   * Base URL: http://localhost:8080/api/v1
   * User Birthday endpoint (POST): http://localhost:8080/api/v1/users/create