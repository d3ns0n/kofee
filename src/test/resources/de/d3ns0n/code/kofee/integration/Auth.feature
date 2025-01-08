Feature: Accessing /me

  Scenario: With token will return 200 Ok
    Given a valid JWT
    When requesting /me
    Then HTTP status is 200 Ok
    And user info response has username "John Doe"
    And user info response has authority "SCOPE_read"

  Scenario: Without token will return 401 Unauthorized
    When requesting /me
    Then HTTP status is 401 Unauthorized
