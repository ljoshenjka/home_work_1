# Selenium+Java+Cucumber framework for web application test automation #

This framework purpose is the implementation and execution of acceptance tests for web application in various browsers. It consists of predefined dependencies and API's that will help to describe the expected behaviour of web app.

Currently, 4 major web browsers are supported: Chrome, Firefox, Edge, IE.

### Core dependencies ###

* Selenium 3.141.59 - test automation framework for web applications
* Cucumber 6.8.1 - provides the natural Gherkin language used to describe application features, a basic API for binding that natural language to step definitions written in Java
* PageObject/PageElements - design patterns which helps you to encapsulate the expected structure and mechanics of your application's UI.
* JUnit4 - for asserting expectations of scenario outcomes
* Apache Maven - build automation tool

## Getting Started ##

### Architecture ###

Framework is divided in such subsections:

#### Main ####
* src/main/java/allure
* src/main/java/base
* src/main/java/constants
* src/main/java/elements
* src/main/java/exceptions
* src/main/java/helpers
* src/main/java/hook
* src/main/java/utils
* src/main/resources

**allure** - allure reporter custom scenario state listeners

**base** - core step class, with data storage to share between steps/scenarios

**constants** - config fields

**elements** - page elements like buttons, labels, text fields etc.

**exceptions** - custom exception classes to help understand test failures

**helpers** - helper functions for helping writing test cases and page objects

**hook** - "before" and "after" annotations with different setups either for driver or test cases

**utils** - WebDriver setup and special utilities

**resources** - run configurations stored here with environment settings 

#### Test ####
* src/test/java/custom_elements
* src/test/java/pages
* src/test/java/runners
* src/test/java/steps
* src/test/resources/Features

**custom_elements** - custom page elements which are specific to product under test

**pages** - web application page objects and components

**runners** - runners needed for execution

**steps** - cucumber step implementations mapped with gherkin

**Features** - feature files written in Gherkin language

### Writing Test Cases ###

Acceptance TC are written in Gherkin language inside feature file which are stored in "Features" directory and then mapped to a step implementation in Java inside step classes.

### Running Test Cases ###

##### Precondition ####
To run tests minimum requirements are JDK 8, Maven and web browser (Chrome, Firefox, IE, EDGE)

##### Execution ####
1. Check for the correct data in your config.properties file. You can create your own env config file and use it for execution specifying prefix in config.properties (Ex. app.environment=PROD means that there must be config-PROD.properties)
2. Open project dir in terminal and run "mvn clean test". Specify tags to run in TestRunner file or through terminal with -Dcucumber.filter.tags="@testSuite" - "mvn clean verify -Dcucumber.filter.tags="@testSuite""
2.1. Also you can execute test from your IDE with TestRunner class as it is JUnit executable class.
3. Allure results are stored in ./allure-results directory. To generate html report install 'allure-commandline' npm module and run 'allure serve'
3.1 Cucumber json report is in "target/reports/cucumber.json". It can be used to generate report with other reporting tools.
