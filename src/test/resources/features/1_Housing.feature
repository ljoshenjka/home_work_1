@testSuite
@housing
Feature: Housing

  Description: Housing page validations

  Scenario: Housing page sorting check
    Given user opens Craigslist Finland website
    Then checks that Home page is displayed
    Then checks that Home page top banner header is 'suomi'
    When user change site language to 'english'
    Then checks that Home page top banner header is 'Finland'
    When user navigates to Housing page
    Then checks that Housing page is opened