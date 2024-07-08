Feature: Employee creation
  As a recruiter
  I want to create a new employee from a successful application
  So that the employee can be added to the employee management system

  Scenario: Create a new employee from an application
    Given there is an application with candidate name "John Doe" and email "john.doe@example.com"
    When the application is validated
    Then a new employee should be created with first name "John", last name "Doe" and email "john.doe@example.com