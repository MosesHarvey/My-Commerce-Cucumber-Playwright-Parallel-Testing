 @login @smoke
Feature: Login

 Background:
  Given the user is on the home page
  When the user clicks on SignupLogin tab
  Then the user should see page title "Automation Exercise - Signup / Login"

  @TC02
  Scenario: login with valid credentials
   Then the user should see the heading "Login to your account"
    When the user enters correct email address
    And the user enters correct password
    And the user clicks login button
    Then Logged in as "username" should be visible

 @TC03
 Scenario Outline: login with incorrect credentials
  Then the user should see the heading "Login to your account"
  When the user enters incorrect email address "<inEmail>" or incorrect password "<inPassword>"
  And the user clicks login button
  Then the user should see "Your email or password is incorrect!" error message
  Examples:
   | inEmail                 | inPassword         |
   | incorrect_email@abc.com | incorrect_password |
   | incorrect_email@abc.com | abc123             |
   | satang@abc.com          | incorrect_password  |

 @TC04
 Scenario: Logout User
 When the user logged in with valid credentials
  Then Logged in as registered username should be visible
  When the user clicks the Logout button
 Then the user should see the heading "Login to your account"










