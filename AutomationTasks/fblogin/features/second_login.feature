Feature: Login to Facebook
  Scenario: Login to Facebook
    Given Navigate to Facebook website
    Then Fill in the username and password fields
    Then Click login button
    Then Assert that the user homepage is opened
    Then Logout from facebook