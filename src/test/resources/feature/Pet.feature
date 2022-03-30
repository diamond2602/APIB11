Feature: Test all pet operations

  Scenario: Create pet
    Given I have valid url to create a pet
    When I send POST request to create a pet
    Then status code should be 200
    And response should be in json format

    Scenario: Get a pet
      Given I have valid url to get a pet
      When I send GET request to retrive a pet with 1876540 id
      Then status is 200
      And response body should contain 1876540 id

      Scenario: Update a pet
        Given I have valid url to update a pet
        When I send PUT request to update a pet
        Then status should be 200
        And response body should be application/json

        Scenario: Delete a pet
          Given  I have a valid url to delete a pet
          When  I send DELETE request to delete a pet with id 3743232
          Then status should be 200
          And pet with id 3743232 should not exist