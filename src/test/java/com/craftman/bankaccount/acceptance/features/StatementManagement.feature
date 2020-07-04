# Created by mewejp at 02/07/2020
Feature: Operations management

  Scenario: View operations history

    Given  I made the following operations in my account
      | operationType | date                | amount |
      | DEPOSIT       | 20/07/2020 11:00:00 | 100    |
      | WITHDRAWAL    | 20/07/2020 18:00:00 | 20     |
      | DEPOSIT       | 20/07/2020 20:00:00 | 500    |
      | DEPOSIT       | 22/07/2020 07:00:00 | 1000   |
    When I see the history of my operations
    Then Account statement should display all operations histories

  Scenario: View operations history

    Given  I made the following operations in my account
      | operationType | date | amount | balance |
    When I see the history of my operations
    Then No operation history should be be displayed
