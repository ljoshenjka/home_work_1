@testSuite
@housing
#noinspection NonAsciiCharacters
Feature: Housing

  Description: Housing page validations

  Background:
    Given user opens Craigslist Finland website
    When user change site language to 'english'
    Then checks that Home page top banner header is 'Finland'
    When user navigates to Housing page

  Scenario: Verify Housing page price sorting functionality
    Then checks that Housing page is opened
    When user change Housing page sorting to 'price ↑'
    Then checks that Housing page results are sorted by price ascending
    When user change Housing page sorting to 'price ↓'
    Then checks that Housing page results are sorted by price descending

  Scenario: Verify Housing page default sorting possibilities
    Then checks that Housing page sorting is set to 'newest'
    And checks that Housing page sorting options are available
      | newest  |
      | price ↑ |
      | price ↓ |

  Scenario: Verify Hosing page sorting possibilities after using search
    When user search for 'house' on Housing page
    Then checks that Housing page sorting options are available
      | upcoming |
      | newest   |
      | relevant |
      | price ↑  |
      | price ↓  |