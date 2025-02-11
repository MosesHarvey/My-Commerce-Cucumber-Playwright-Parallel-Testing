@product  @smoke
Feature: Products

  @TC08
  Scenario: Verify All Products and product detail page
    Given the user is on the home page
    Then the user should see page title "Automation Exercise"
    When the user clicks on Products tab
    Then the user should see page title "Automation Exercise - All Products"
    And the products list is visible
    When the user clicks on View Product of the first product
    Then the user should be landed on the product detail page
    And the following details should be visible:
      | Detail      |
      | Product Name|
      | Category    |
      | Price       |
      | Availability|
      | Condition   |
      | Brand       |


  @TC09 @search
  Scenario: Search Product
    Given the user is on the home page
    Then the user should see page title "Automation Exercise"
    When the user clicks on Products tab
    Then the user should see page title "Automation Exercise - All Products"
    When the user enters a product name in the search input and clicks the search button
    Then the page url including the product name should be correct
    And the user should see the heading "Searched Products"
    And all products related to the search should be visible



  @TC19
  Scenario: View Products by Brand
    Given the user is on the home page
    Then the user should see page title "Automation Exercise"
    When the user clicks on Products tab
    Then the user should see page title "Automation Exercise - All Products"
    And the user should see the heading "Brands"
    And the user verifies that Brands are visible under Brands heading:
      | Polo |
      | H&M |
      | Madame |
      | Mast & Harbour |
      | Babyhug |
      | Allen Solly Junior |
      | Kookie Kids |
      | Biba |
    When the user clicks on any brand name
    Then the user verifies that they are navigated to the brand page
     And the user should see that the brand products are displayed




  @TC20
  Scenario: Search Products and Verify Cart After Login
    Given the user is on the home page
    Then the user should see page title "Automation Exercise"
    And the user clicks on Products tab
    Then the user should see page title "Automation Exercise - All Products"
    When the user enters a product name in the search input and clicks the search button
    Then the user should see the heading 'Searched Products'
    And all products related to the search should be visible
    When the user adds those products to cart
    And the user clicks on Cart tab
    Then the user verifies that products are visible in cart
    When the user clicks on SignupLogin tab
    When the user enters correct email address
    And the user enters correct password
    And the user clicks login button
    And the user clicks on Cart tab
    Then the user verifies that products are visible in cart


  @TC21
  Scenario: Add Review on a Products
    Given the user is on the home page
    Then the user should see page title "Automation Exercise"
    And the user clicks on Products tab
    Then the user should see page title "Automation Exercise - All Products"
    When the user clicks on View Product button on any product
    Then the user verifies a heading text 'Write Your Review' to be visible
    When the user enters name, email, and review
    And the user clicks Submit button under review form
    Then the user verifies success message 'Thank you for your review.'



