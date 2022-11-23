Feature: List all users

  Scenario: List a user
    Given Sam is allowed to call the API
    When he calls the api for the user with ID '1'
    Then he sees that the user has first name 'George' and last name 'Bluth'
