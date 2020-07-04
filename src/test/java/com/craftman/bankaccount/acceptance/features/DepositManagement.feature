# Created by mewejp at 02/07/2020
Feature: Deposit management

  Scenario: Save amount

    Given  I have amount 100
    When I add that amount in my account
    Then my bank account with amount 100 should be credited

  Scenario: Save negative amount

    Given  I have amount -5
    When I add that amount in my account
    Then I should see an error message with "Amount not allowed"
