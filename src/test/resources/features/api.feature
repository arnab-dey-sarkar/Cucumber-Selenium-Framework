@API
Feature: API Feature

  Background: 
  Given A WorkBook Named "Testdata" with SheetName "Sheet1" is Loaded

  @Test1
  Scenario Outline: Test1
    Given The BaseURL is <baseURI>
    And Static "Headers" Information Are Loaded
    When User Performs GET Operation
    Then Verify <statusCode> In Response

    Examples: 
      | baseURI  | statusCode |
      | "/imghp" |        200 |
