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
- features files under `test/../features` directory: These are cucumber scenarios files
- steps definition files under `test/../stepfinitions` directory: These are Steps definition files that match above test scenarios.
- `AccountManagementTest` and `AccountTest` are test runners and account unit test files that follows BDD+TDD pricinples.

## Running

- Run `gradle cucumber` to run cucumber scenarios+steps in command line. The Gradle task `cucumber` is a custum task I created in `build.gradle`.
- You can hit run configuration of your IDE (IntelliJ) tu also run scenarios+steps

## Results

 Below some sample results by running gradle cucumber task. The built-in **pretty** plugin is used to have beautifull output :)
 
 <img src="https://live.staticflickr.com/65535/50074226858_e8bf266e15_z.jpg" width="500">
 
 Below some result when running the main application file 
<img src="https://live.staticflickr.com/65535/50074238213_41904a43d7_z.jpg" width="500">
