@UI
Feature: Free CRM Login Feature

  Background:
    Given A WorkBook Named "Testdata" with SheetName "Sheet2" is Loaded

  Scenario Outline: Developer Salesforce
    Given User is on Homepage
    When User Clicks On Login
    Then Verify The Login Page
    When User Enters Username as <Username> and Password as <Password>
    Then User Snaps a Screenshot

    Examples:
      | Username | Password |
      | Username | Password |

