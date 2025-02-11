@subscribe  @smoke
Feature: Subscription to Newsletter
  @TC10
  Scenario: Verify Subscription on Home Page
    Given the user is on the home page
    Then the user should see page title "Automation Exercise"
    When the user scrolls down to the footer
    Then the user should see the heading "Subscription"
    When the user enters an email address in the input field and clicks the arrow button
    Then the success message 'You have been successfully subscribed!' should be visible on subscribe page

  @TC11
  Scenario: Verify Subscription on Cart Page
    Given the user is on the home page
    Then the user should see page title "Automation Exercise"
    When the user clicks on Cart tab
    And the user scrolls down to the footer
    Then the user should see the heading "Subscription"
    When the user enters an email address in the input field and clicks the arrow button
    Then the success message 'You have been successfully subscribed!' should be visible on subscribe page


