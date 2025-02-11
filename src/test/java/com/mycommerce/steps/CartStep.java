package com.mycommerce.steps;

import com.microsoft.playwright.Page;
import com.mycommerce.appdata.AppConstant;
import com.mycommerce.pages.*;
import com.mycommerce.utilities.ConfigReader;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Map;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class CartStep {
    private final Page page;
    private  HeaderComponent headerComponent;
    private BasePage homePage;
    private ProductSection productSection;
    private  CartPage cartPage;
    private  ProductDetailPage productDetailPage;
    private  AddedModal addedModal;
    private BasePage basePage;

    public CartStep() {
        this.page = Hooks.getPage();
        this.basePage = new BasePage(page);
        this.headerComponent = new HeaderComponent(page);
        this.homePage =new BasePage(page);
        this.productSection = new ProductSection(page);
        this.cartPage = new CartPage(page);
        this.productDetailPage = new ProductDetailPage(page);
        this.addedModal = new AddedModal(page);
    }

    // ======================== Add products to the cart =================


    @Then("the user hovers over the first product and clicks Add to cart")
    public void the_user_hovers_over_the_first_product_and_clicks_add_to_cart() {
        productSection.hoverOverAProductAndClickAddToCart(1);
    }

    @Then("the user clicks on Continue Shopping button on Added modal")
    public void the_user_clicks_on_continue_shopping_button_on_added_modal() {
        addedModal.clickContinueShoppingBtnOnAddedModal();
    }


    @Then("the user hovers over the second product and clicks Add to cart")
    public void the_user_hovers_over_the_second_product_and_clicks_add_to_cart() {
      productSection.hoverOverAProductAndClickAddToCart(2);
    }

    @When("the user clicks on View Cart button on Added modal")
    public void the_user_clicks_on_view_cart_button_on_added_modal() {
        addedModal.clickViewCartOnAddedModal();
    }


    @Then("the user verifies that both products are added to Cart")
    public void the_user_verifies_that_both_products_are_added_to_cart() {
        assertEquals(2, cartPage.getItemCount());
        assertEquals(ConfigReader.get("productName1"), cartPage.getItemDescriptions().get(0));
        assertEquals(ConfigReader.get("productName2"), cartPage.getItemDescriptions().get(1));

        System.out.println("Product info verified");
    }

    @Then("the user verifies their prices, quantity and total price")
    public void the_user_verifies_their_prices_quantity_and_total_price() {
        assertEquals(ConfigReader.get("productPrice1"), cartPage.getItemPrices().get(0));
        assertEquals(ConfigReader.get("productPrice2"), cartPage.getItemPrices().get(1));


        System.out.println("Prices are verified");
    }

    // =========Add specific number of products to the cart and Verify the quantity=========


    @When("the user clicks View Product for any product on the home page")
    public void the_user_clicks_view_product_for_any_product_on_the_home_page() {
        Random random = new Random();
        int randomIndex = random.nextInt(productSection.getProductCount());
        productSection.clickViewProductButtonByIndex(randomIndex);
        productDetailPage.getProductDetails().waitFor();
    }

    @When("the user increases the quantity to {int}")
    public void the_user_increases_the_quantity_to(Integer quantity) {
       String inputtedQuantity =productDetailPage.increaseProductQuantityTo(quantity);
        ConfigReader.set("productQuantity", inputtedQuantity);
    }

    @When("the user saves the product name, quantity and price")
    public void the_user_saves_the_product_name_quantity_and_price() {
        ConfigReader.set("productName",productDetailPage.getProductName().textContent().trim());

    }


    @When("the user clicks Add to cart button on the product detail page")
    public void the_user_clicks_add_to_cart_button_on_the_product_detail_page() {
        productDetailPage.clickAddToCartBtn();
    }

    @When("the user clicks View Cart button on the Added Modal")
    public void the_user_clicks_view_cart_button_on_the_added_modal() {
        addedModal.clickViewCartOnAddedModal();

    }

    @Then("the user verifies that the product is displayed in cart page with exact quantity")
    public void the_user_verifies_that_the_product_is_displayed_in_cart_page_with_exact_quantity() {

        Map<String, String>productDataOnTable =cartPage.getRowData(1);
        System.out.println(productDataOnTable.get("Quantity").trim());
        System.out.println(ConfigReader.get("productQuantity"));
        assertEquals(ConfigReader.get("productQuantity"), productDataOnTable.get("Quantity").trim());
    }

    // ========== Remove products from cart =================================================================
    @When("the user clicks X button corresponding to a particular product")
    public void the_user_clicks_x_button_corresponding_to_a_particular_product() {
        cartPage.clickXBtnInProductTable(1);
    }

    @Then("the user verifies that the product is removed from the cart")
    public void the_user_verifies_that_the_product_is_removed_from_the_cart() {
        assertEquals(AppConstant.EMPTY_CART_TEXT, cartPage.getEmptyCartText().trim());

    }
    // ===============Add to cart from Recommended items =============================

    @When("the user scrolls to the bottom of the page")
    public void the_user_scrolls_to_the_bottom_of_the_page() {
        System.out.println("Playwright is scrolling to the bottom of the page");
    }

    @When("the user clicks on Add To Cart on a recommended product")
    public void the_user_clicks_on_add_to_cart_on_a_recommended_product() {
       productSection.addARecommendedProductToCartByIndex(1);
    }

    @Then("the user verifies that the product is displayed on the cart page")
    public void the_user_verifies_that_the_product_is_displayed_on_the_cart_page() {
        assertTrue(cartPage.getItemDescriptions().toString().contains(ConfigReader.get("recommendProductName")));

    }


}
