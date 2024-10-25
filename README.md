# KenectContactAggregator

KenectContactAggregator is an API aggregation service, developed in **Java** using **Spring Boot**. The purpose of this project is to consume a paginated external API, consolidate the contact information, and make it available through a REST endpoint.

## Technologies Used

- **Java 21**: Main programming language for the project.
- **Spring Boot 3.3.5**: Framework used for rapid Java application development.
- **JUnit 5**: Testing framework.
- **Mockito**: Framework for creating mocks and simulating dependencies during tests.
- **Maven**: Build and dependency management tool.

## Features

- **/contacts**: GET endpoint that consolidates and returns a list of contacts paginated from the external API.
- **Automatic Pagination**: Fetches all pages provided by the external API and compiles them into a single list.

## Prerequisites

Ensure you have the following installed on your machine:

- **Java 21** or later
- **Maven** 3.8.1 or later
- **Git**

## Project Setup

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/kenectcontactaggregator.git
cd kenectcontactaggregator
```

### 2. Install Dependencies

Make sure all project dependencies are installed by running:

```bash
mvn clean install
```

## Running the Project Locally

To run the application locally, use the following command:

```bash
mvn spring-boot:run
```

## Testing the Project

1. **Main Endpoint**:
   - Use a REST client (like **Postman** or **Insomnia**) to make a GET request to the endpoint:

```bash
GET http://localhost:8080/contacts
```

```bash
Authorization: Bearer J7ybt6jv6pdJ4gyQP9gNonsY
```
2. **Automated Tests**:
- To run unit and integration tests, use the following command:
```bash
mvn test
```

## Building the project
- To create the .jar package of the application, run the following command:
```bash
mvn clean package
```
The generated .jar file will be located in the target/ directory with the name kenectcontactaggregator-0.0.1-SNAPSHOT.jar.
## Running the Built .jar
- You can run the generated .jar file using the following command:
```bash
java -jar target/kenectcontactaggregator-0.0.1-SNAPSHOT.jar
```
