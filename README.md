# Country City Service

This is a simple Spring Boot application to manage Countries and Cities.

The project is developed using Java 21 and Spring Boot. Currently, data is stored in hardcoded lists, so there is no database required.

## Features

- Get all countries.
- Get cities by country ID.
- Get city details by city ID.
- Pagination for cities.
- Swagger API documentation.
- Basic API testing using JUnit and Mockito.

## Technologies Used

- Java 21
- Spring Boot
- Maven
- JUnit 5
- Mockito
- Swagger / OpenAPI
- Lombok

## Project Setup

**Prerequisites:**
- Java 21
- Maven
- IntelliJ IDEA or Eclipse

**Steps to run the project:**

1. Clone the repository.
2. Open the project in IntelliJ IDEA.
3. Make sure Java 21 is selected.
4. Run the main Spring Boot application.

Application URL:

`http://localhost:8080`

## API Endpoints

### 1. Get All Countries

**GET** `/countries`

Returns the list of all available countries.

### 2. Get Cities By Country

**GET** `/countries/1/cities?page=0&size=10`

Returns cities for the selected country.

Parameters:
- `countryId` - Country ID
- `page` - Page number (default 0)
- `size` - Number of records per page (default 10)

The response contains:
- City list
- Current page
- Page size
- Total records
- Total pages

### 3. Get City Details

**GET** `/cities/101`

Returns details of a city using its ID.

## Swagger Documentation

After starting the application, open the following URL:

`http://localhost:8080/swagger-ui/index.html`

You can check and test all APIs from Swagger UI.

## Running Test Cases

The project contains basic unit and API tests using JUnit 5 and Mockito.

To run all tests:

```bash
mvn test
```

For Windows:

```bash
mvnw.cmd test
```

Test reports will be available inside:

`target/surefire-reports/`

## Project Structure

- `controller` - Handles API requests.
- `service` - Contains business logic.
- `repository` - Provides country and city data.
- `model` - Contains Country and City classes.
- `dto` - Contains pagination response.
- `config` - Swagger configuration.
- `test` - Contains unit and controller tests.

## Note

This project uses in-memory data. Data is not saved permanently and will reset when the application restarts.