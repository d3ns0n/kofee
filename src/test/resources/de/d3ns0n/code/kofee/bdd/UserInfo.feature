Feature: UserInfo endpoint
  The UserInfo endpoint allows an authenticated user to inspect his roles and JWT attributes

  Scenario: Logged in as a customer will the role 'customer'
    Given user is logged in as a customer
    When requesting /me
    Then HTTP status is 200 Ok
    And user info response has username "John Doe"
    And user info response contains authority "ROLE_customer"

  Scenario: Logged in as a coffee farmer will have correct roles 'customer' and 'coffee_farmer'
    Given user is logged in as a coffee farmer
    When requesting /me
    Then HTTP status is 200 Ok
    And user info response has username "Jane Doe"
    And user info response contains authority "ROLE_customer"
    And user info response contains authority "ROLE_coffee_farmer"

  Scenario: Without token will return 401 Unauthorized
    When requesting /me
    Then HTTP status is 401 Unauthorized
