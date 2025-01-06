Feature: Accessing /me

  Scenario: Without token will return 401 Unauthorized
    When requesting /me
    Then HTTP status is 401 Unauthorized
