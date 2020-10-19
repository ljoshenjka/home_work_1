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
    And checks that Housing page sorting is set to 'newest'
    When user change Housing page sorting to 'price ↑'
    Then checks that Housing page results are sorted by price ascending
    When user change Housing page sorting to 'price ↓'
    Then checks that Housing page results are sorted by price descending
    And checks that Housing page sorting options are available
      | newest  |
      | price ↑ |
      | price ↓ |