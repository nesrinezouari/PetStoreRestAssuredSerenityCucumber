# RestAssuredSerenityCucumber Framework

This is Maven project setup on serenity restassured dependencies
Feature File Pet.feature contains Worklow of Get, create, update and delete a pet according to query parameters and path parameters for delete operation.
Tests are written using a combination of SerenityBDD, RestAssured, Cucumber, Junit & Maven.

## Technology Stack

- Java
- Serenity BDD
- Maven
- RestAssured

## Prerequisites

* [Java 1.8](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) - Java Dev Kit
* [Maven](https://maven.apache.org/download.cgi) - Dependency Manager

## Application Under Test

We are using Petstore APIs as the Application Under Test.

* URL :https://petstore.swagger.io/v2/pet

## The project directory structure
The project follows the standard directory structure used in most Serenity projects:

```Gherkin
src
  + main
    + java                          
      + models                      All Objects of all endpoints
          .Pet
             .Pet.java
             .Pets.java
             .Tag.java
           .json                 data need to create Post and Put Json Object
      + utilities                Methods needed for handling Files and Messages
          .applicationProperties.java
           .ErrorMessage.java
  + test
    + java  
      .adidas
         .commun
      .api
         .adidas
            .endpoints                   endpoints of the services
       .features.steps            Package of stepdefinition (should be specified bu @Glue option on CucumberRunner )
       .features.steps.serenity    Steps serenity we can consider them as controler , steps can be shared... 
       .runners                     test runner(senerity runner/trigger configurations)
              
     
    + resources
      + features                  Feature files
      + properties                AUT properties files
      + logback.xml               for rebug logs
```
Following instructions will help you running the project. First, clone this project locally on your machine from the master branch.

### Installation and Test Execution

Open the project in any IDE Eclipse/IntelliJ. Run the following command in Terminal and build the project. It will automatically download all the required dependencies.

```sh
$ mvn clean verify
```

### Execute Tests

Run the following command in Terminal to execute tests.

```sh
$ mvn clean verify
```

### Test Report

You can find the Serenity reports in the following directory of the Project.

```sh
\target\site\serenity\
```

In the serenity directory, open 'index.html' file to view the report.

### How It Works
Runner Start with Feture File , parse Steps and look for stepDefinition path on @Glue option of runner
Every Stepdefinition call Steps that is layer to talk with Endpoint and checking Result 

### How To Run Projet

we set Maven Clean Verify Goal and project Run we found index.html under target/site folder
we can run project as Junit Project Also by setting JunitRunnerTest Class to CucumberTestSuite

### Next Step
We can generate data even form file or from Random data by using com.github.javafaker dependency
or we can Send them on Feture File which not recommanded for visibility purpose
