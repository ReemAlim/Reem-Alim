Feature: Register into Facebook
  Scenario: Sign up for Facebook
    Given Navigate to Facebook website
    And Fill the sign up form
    And Click sign up button
    Then Assert that the user homepage is opened
    Then Logout from facebook