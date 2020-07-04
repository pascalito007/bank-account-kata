# Created by mewejp at 02/07/2020
Feature: Withdrawal management

  Scenario: Retrieve some of my savings

    Given  I have saved the following amounts in my account
      |100|
      |500|
    When I withdraw amount 100 from my account
    Then my bank account with amount 100 should be charged


  Scenario: Retrieve all my savings

    Given  I have saved the following amounts in my account
      |100|
      |500|
    When I withdraw amount 600 from my account
    Then my bank account with balance 0 should be remained

  Scenario: Retrieve incorrect amount

    Given  I have saved the following amounts in my account
      |100|
      |500|
    When I withdraw amount -1 from my account
    Then I should see an error message with "Amount not allowed"

  Scenario: Retrieve exceed amount

    Given  I have saved the following amounts in my account
      |100|
      |500|
    When I withdraw amount 601 from my account
    Then I should see another error message with "Insufficient funds to retrieve"

