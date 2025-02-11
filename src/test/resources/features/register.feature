@signup  @smoke
Feature: User Signup and Account Deletion
   @TC01
  Scenario: Register User and Delete User
    Given the user is on the home page
    Then the user should see page title "Automation Exercise"
    When the user clicks on SignupLogin tab
    Then the user should see the heading "New User Signup!"
    When the user enters name and email address
    And the user clicks Signup button
    Then the user should see the heading "Enter Account Information"
    When the user fills details: Title, Name, Email, Password, Date of birth
    And the user selects checkbox 'Sign up for our newsletter!'
    And the user selects checkbox 'Receive special offers from our partners!'
    And the user fills details: First name, Last name, Company, Address, Address2, Country, State, City, Zipcode, Mobile Number
    When the user clicks Create Account button
    Then the user verifies that 'Account Created!' is visible
    When the user clicks Continue button
     Then Logged in as Mr. with full Name should be visible
    When the user clicks Delete Account button
    Then the user verifies that 'Account Deleted!' is visible
     When the user clicks Continue button
     Then the user should see page title "Automation Exercise"


  @TC05
  Scenario: Register User with existing email
    Given the user is on the home page
    Then the user should see page title "Automation Exercise"
    When the user clicks on SignupLogin tab
    Then the user should see the heading "New User Signup!"
    When the user enters a name and an already registered email address
    And the user clicks Signup button
    Then the user verifies that the error 'Email Address already exist!' is visible

