# bank-account-kata  
Bank account usual operations (Withdrawal, Deposit, Logs...) project

## Description

The goal of this project is to implement common account operations:

- Deposit and Withdrawal
-   Account statement (date, amount, balance)
-   Statement printing

more details available at this [link](https://gist.github.com/abachar/d20bdcd07dac589feef8ef21b487648c)

## Prerequisites
This is a gradle project created using [Spring Initalizr](https://start.spring.io/) . To run this project, you need to:

- Install [JDK8](https://www.oracle.com/fr/java/technologies/javase/javase-jdk8-downloads.html) on your computer (this project has been tested using JDK8)
- [Gradle](https://gradle.org/install/) to be able to run in command line

## Project files

- `build.gradle`: This is required to build this project using Gradle.
- features files under `test/../features` directory: These are cucumber scenarios files writen using [Gherkin](https://cucumber.io/docs/gherkin/reference/)
- steps definition files under `test/../stepdefinitions` directory: These are Steps definition files that match above test scenarios.
- `AccountManagementTest` and `AccountTest` are test runners and account unit test files that **follows BDD+TDD pricinples and AAA technique** .
- `Dockerfile`: This file let you build an image of the application and then a running instance of the image (container).

## Running

- Run `gradle cucumber` to run cucumber scenarios+steps in command line. The Gradle task `cucumber` is a custum task I have created in `build.gradle`.
- You can hit run configuration of your IDE (IntelliJ) tu also run scenarios+steps

## Results

 Below some sample results by running `gradle cucumber` task. The built-in **pretty** plugin is used to have beautiful output :)
 
 <img src="https://live.staticflickr.com/65535/50076136141_915c2a4918_z.jpg">
 
 Below some result when running the main application file 
<img src="https://live.staticflickr.com/65535/50075576273_3c947b694c_z.jpg" width="500">

Sample execution HTML report is available in `target/SystemTestReports/html.html` and also uploaded in AWS S3  at this link [this link](https://scalpfolder.s3.amazonaws.com/html.html)

## Containerize app with Docker

There is a small Dockerfile that let you run the jar file inside a container. You first need to have [Docker](https://docs.docker.com/engine/install/ubuntu/) in your computer or virtual machine.
Just hit below commands to run the app inside a container.

    docker build -t bank-account .
    docker run -it bank-account
