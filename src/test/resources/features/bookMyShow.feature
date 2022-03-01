@UI1
Feature: BookMyShow Feature

  Background:
    Given A WorkBook Named "TestData" with SheetName "Sheet2" is Loaded

  Scenario Outline: Book My Show Check Batman Availability
    Given User is on Movie page
    Then Check If Tickets Are Available For <MovieTheatre>
    Examples:
      | MovieTheatre |
      | City Centre  |