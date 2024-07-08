Feature: Authentification

  Scenario: Successful login
    Given a user with username "testUser" and password "testPass" exists
    When the user attempts to login with username "testUser" and password "testPass"
    Then the user should be redirected to the homepage

  Scenario: Failed login
    Given a user with username "testUser" and password "wrongPass" exists
    When the user attempts to login with username "testUser" and password "testPass"
    Then the user should be redirected to the login page