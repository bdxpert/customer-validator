Feature: Customer validation can be retrieved
  Scenario: client makes call to POST /customers/validate/with-all-of
    When the client calls /customers/validate/with-all-of
    Then the client receives status code of 200
    And the client get validation status true
