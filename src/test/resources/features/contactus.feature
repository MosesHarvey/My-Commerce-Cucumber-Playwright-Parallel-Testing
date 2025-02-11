@contact @smoke
Feature: Contact Us Form Submission

   @TC06
  Scenario: User fills and submits a contact us form successfully
    Given the user is on the home page
    Then the user should see page title "Automation Exercise"
    When the user clicks on Contact us tab
    Then the user should see page title "Automation Exercise - Contact Us"
    Then the user should see the heading "Get In Touch"
    When the user enters their name, email, subject, and message
    And the user uploads a file
    And the user clicks the OK button
    And the user clicks the Submit button
    Then the success message "Success! Your details have been submitted successfully." should be visible on contact us page
    When the user clicks the Home button
    Then the user should see page title "Automation Exercise"
