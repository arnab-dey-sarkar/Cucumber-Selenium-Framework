@UI
Feature: Free CRM Login Feature

  Background:
    Given A WorkBook Named "TestData" with SheetName "Sheet2" is Loaded

  Scenario Outline: GTPL-KCBPL Login
    Given User is on Homepage
    When User Enters Username as <Username> and Password as <Password>
    And User Clicks On Login
    Then User Snaps a Screenshot

    Examples:
      | Username | Password |
      | Username | Password |

