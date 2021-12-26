# Getting Started

## System Requirements
* Java 11+(if you have a different version, please update the pom file)
* Maven 3+

## Installation and Run
* In command line just issue mvn clean install in order to execute clean up and run all the tests,
* Then accordingly run the following command to run the service mvn spring-boot:run

## Tests
The project includes the following tests:
* Integration Test: PollsControllerTest.java that goes through all the implemented endpoints and tests them one by one those can be used as a proof of work
* Unit Test: PollServiceTest.java tests the service layer lightweight just terms of functionalities.

## Initial Data
The project was heavily invested in Integration tests that test all the expected functionalities in the task.
If you like to run the project int GUI then you need to follow the following steps:
* 1.run H2 Console @ http://localhost:8080/h2-polls
* 2.jdbc url: jdbc:h2:mem:testdb
* 3.username: sa
* 4.password: leave empty without any inputs
* the content of test-insert-statistics-data.sql into the console and execute all

## Endpoints
All the endpoints in the task are defined according to the rest standards. 
Furthermore, the ones that are expected only implemented, meaning you will find in controller:
* Unimplemented methods: return HttpStatus.NOT_IMPLEMENTED
* Implemented methods: all return the respective objects as well as the Http status codes
