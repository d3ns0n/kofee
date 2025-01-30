Feature: CreateItem

  Scenario: Creating an item will return correct response
    Given logged in as a coffee farmer
    When creating an item with name "Nicaragua Blend" and price 17.77
    Then HTTP status is 200 Ok
    And create item response has an id
    And create item response has the name "Nicaragua Blend"
    And create item response has the price 17.77
    And create item event was sent
    And create item event has the name "Nicaragua Blend"
    And create item event has the price 17.77
    And create item event has publishedAt

  Scenario: Creating an item as a customer will be forbidden
    Given logged in as a customer
    When creating an item with name "Nicaragua Blend" and price 17.77
    Then HTTP status is 403 Forbidden
