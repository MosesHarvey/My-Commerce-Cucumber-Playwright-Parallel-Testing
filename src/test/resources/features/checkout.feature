@checkout @smoke
Feature: Checkout Functions

  @TC23
  Scenario: Verify address details in checkout page
    Given the user is on the home page
    Then the user should see page title "Automation Exercise"
    When the user clicks on SignupLogin tab
    And the user fills all details in Signup and creates an account
    Then the user verifies 'ACCOUNT CREATED!'
    When the user clicks Continue button
    And the user should see the heading 'Logged in as username'
    When the user adds products to the cart
    And the user clicks on Cart tab
    Then the user verifies that the cart page is displayed
    When the user clicks Proceed To Checkout button
    Then the user verifies that the delivery address is the same as the address filled at the time of registration of account
    And the user verifies that the billing address is the same as the address filled at the time of registration of account
    When the user clicks on "Delete Account" tab
    Then the user verifies 'ACCOUNT DELETED!'
    When the user clicks Continue button



  @TC24
  Scenario: Download invoice after purchase an order
    Given the user is on the home page
    Then the user should see page title "Automation Exercise"
    When the user adds products to the cart
    And the user clicks on Continue Shopping button on Added modal
    And the user clicks on Cart tab
    Then the user should see page title "Automation Exercise - Checkout"
    When the user clicks Proceed To Checkout button
    And the user clicks RegisterLogin button on Checkout modal
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
    When the user clicks on Cart tab
    And the user clicks Proceed To Checkout button
    Then the user verifies Address Details and Review Your Order
    When the user enters a description in comment text area and clicks Place Order
    And the user enters payment details: Name on Card, Card Number, CVC, Expiration date
    When the user clicks Pay and Confirm Order button
    Then the user verifies success message 'Congratulations! Your order has been confirmed!'
    When the user clicks Download Invoice button
    Then the user verifies that invoice is downloaded successfully
    When the user clicks Continue button
    When the user clicks Delete Account button
    Then the user verifies that 'Account Deleted!' is visible

