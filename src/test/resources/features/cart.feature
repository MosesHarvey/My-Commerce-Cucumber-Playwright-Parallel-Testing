@cart @smoke
Feature: Shopping Cart Functionality
  @TC12
  Scenario: Add products to Cart and verify details
    Given the user is on the home page
    Then the user should see page title "Automation Exercise"
    When the user clicks on Products tab
    Then the user should see page title "Automation Exercise - All Products"
    And the user should see the heading "All Products"
    When the user hovers over the first product and clicks Add to cart
    When the user clicks on Continue Shopping button on Added modal
    Then the user should see page title "Automation Exercise - All Products"
    When the user hovers over the second product and clicks Add to cart
    And the user clicks on View Cart button on Added modal
    Then the user verifies that both products are added to Cart
    And the user verifies their prices, quantity and total price

  @TC13
  Scenario: Add specific number of products to the cart and Verify the quantity
    Given the user is on the home page
    Then the user should see page title "Automation Exercise"
    When the user clicks View Product for any product on the home page
    Then the user should see page title "Automation Exercise - Product Details"
    When the user increases the quantity to 4
    And the user saves the product name, quantity and price
    And the user clicks Add to cart button on the product detail page
    When the user clicks View Cart button on the Added Modal
    Then the user verifies that the product is displayed in cart page with exact quantity


  @TC17
  Scenario: Remove Products From Cart
    Given the user is on the home page
    Then the user should see page title "Automation Exercise"
    When the user adds products to the cart
    And the user clicks on Continue Shopping button on Added modal
    And the user clicks on Cart tab
    Then the user should see page title "Automation Exercise - Checkout"
    When the user clicks X button corresponding to a particular product
    Then the user verifies that the product is removed from the cart

  @TC22
  Scenario: Add to cart from Recommended items
    Given the user is on the home page
    Then the user should see page title "Automation Exercise"
    When the user scrolls to the bottom of the page
    Then the user should see the heading "recommended items"
    When the user clicks on Add To Cart on a recommended product
    And the user clicks on View Cart button on Added modal
    Then the user verifies that the product is displayed on the cart page





