Feature: Customer validation can be retrieved
  Scenario: client makes call to POST /customers/validate
    When the client calls /customers/validate
    Then the client receives status code of 200
