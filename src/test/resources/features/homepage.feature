@homepage  @smoke
Feature: Example Website
@nav
Scenario: Home page navigation
  Given the user is on the home page
  Then the user should see page title "Automation Exercise"
  Then the user should see following tabs:
   | Home           |
   | Products       |
   | Cart           |
   | Test Cases     |
   | Signup / Login |
   | Contact Us     |
   | API Testing    |
   | Video Tutorials|
  And the user should see following headings:
   | Features Items           |
   | recommended items       |
   | Category               |
   | Brands                 |
   And the user should Automation Exercise Logo


  @TC07
  Scenario: Verify Test Cases Page
    Given the user is on the home page
    Then the user should see page title "Automation Exercise"
    When the user clicks on Test Cases tab
    Then the user should see page title "Automation Practice Website for UI Testing - Test Cases"

    @TC18
  Scenario: View Products through Category and Subcategory
    Given the user is on the home page
    Then the user should see page title "Automation Exercise"
    Then the user should see the heading "Category"
    Then the user should see following categories on the left side bar:
      | Women |
      | Men   |
      | Kids  |
    When the user clicks on 'Women' category
    And the user clicks on any sub-category link under 'Women' category, for example: 'Dress'
    Then the user verifies that the category page is displayed
    And the user should see the heading which include both category and sub category
    When the user clicks on 'Men' category
    And the user clicks on any sub-category link under 'Men' category, for example: 'Jeans'
    Then the user verifies that the category page is displayed
    And the user should see the heading which include both category and sub category


  @TC25
  Scenario: Verify Scroll Up using 'Arrow' button and Scroll Down functionality
    Given the user is on the home page
    Then the user should see page title "Automation Exercise"
    When the user scrolls down the page to the bottom
    Then the user should see the heading 'Subscription'
    When the user clicks on the arrow at the bottom right side to move upward
    Then the user verifies that the page is scrolled up and 'Full-Fledged practice website for Automation Engineers' text is visible on screen



  @TC26
  Scenario: Verify Scroll Up without 'Arrow' button and Scroll Down functionality
    Given the user is on the home page
    Then the user should see page title "Automation Exercise"
    When the user scrolls down the page to the bottom
    Then the user should see the heading 'Subscription'
    When the user scrolls up the page to the top
    Then the user verifies that the page is scrolled up and 'Full-Fledged practice website for Automation Engineers' text is visible on screen


