Feature: Users management
  As a client of the Users API
  I want to manage users
  So that user data can be retrieved and created

  Scenario: Create a new user
    When I create a user with the following details:
      | email      | john.doe@example.com |
      | first_name | John |
      | last_name  | Doe  |
    Then the response status code should be 201
    And the response should contain the following values:
      | email      | john.doe@example.com |
      | first_name | John |
      | last_name  | Doe  |
    And the response should contain the following keys:
      | id |
      | createdAt |
      | _meta |
    And the response field "id" should be a non-empty string
    And the response field "createdAt" should be a valid timestamp
    And the response should include service metadata

  Scenario: Create user with invalid JSON
   When I create a user with invalid JSON body
   Then the response status code should be 400

  Scenario: Retrieve an existing user
    When I request a user with id 2
    Then the response status code should be 200
    And the response should contain the following values:
      | data.id | 2 |
    And the response should contain the following keys:
      | data.id |
      | data.email |
      | data.first_name |
      | data.last_name |   

  Scenario: Retrieve a non-existing user
    When I request a user with id 12357
    Then the response status code should be 404
