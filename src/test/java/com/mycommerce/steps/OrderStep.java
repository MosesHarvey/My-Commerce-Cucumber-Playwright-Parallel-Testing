package com.mycommerce.steps;

import com.microsoft.playwright.Page;

import com.mycommerce.pages.BasePage;
import com.mycommerce.pages.CartPage;
import com.mycommerce.pages.CheckoutModal;
import com.mycommerce.pages.CheckoutPage;
import com.mycommerce.pages.PaymentPage;
import com.mycommerce.pages.ProductSection;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.jupiter.api.Assertions.*;

public class OrderStep {
    private final Page page;
    private  BasePage basePage;
    private  CartPage cartPage;
    private  CheckoutModal checkoutModal;
    private  CheckoutPage checkoutPage;
    private  PaymentPage paymentPage;
    private  ProductSection productSection;

    public OrderStep() {
        this.page = Hooks.getPage();
        this.basePage = new BasePage(page);
        this.cartPage = new CartPage(page);
        this.checkoutModal = new CheckoutModal(page);        this.checkoutPage = new CheckoutPage(page);
        this.paymentPage = new PaymentPage(page);
        this.productSection = new ProductSection(page);

    }

    // ================ Place order: Register while checkout ================
    @When("the user adds products to the cart")
    public void the_user_adds_products_to_the_cart() {
     productSection.addProductToCartByIndex(1);
    }

    @When("the user clicks Proceed To Checkout button")
    public void the_user_clicks_proceed_to_checkout_button() {

        cartPage.clickProceedToCheckoutBtn();
    }


    @When("the user clicks RegisterLogin button on Checkout modal")
    public void the_user_clicks_register_login_button_on_checkout_modal() {
        checkoutModal.clickRegisterAndLoginBtn();
    }

    @Then("the user verifies Address Details and Review Your Order")
    public void the_user_verifies_address_details_and_review_your_order() {
        System.out.println("The user verifies Address Details and Review Your Order");
    }

    @When("the user enters a description in comment text area and clicks Place Order")
    public void the_user_enters_a_description_in_comment_text_area_and_clicks_place_order() {

     checkoutPage.fillCommentBox("this is a good product");
     checkoutPage.clickPlaceOrderButton();
    }

    @When("the user enters payment details: Name on Card, Card Number, CVC, Expiration date")
    public void the_user_enters_payment_details_name_on_card_card_number_cvc_expiration_date() {
        paymentPage.fillPaymentDetails();

    }

    @When("the user clicks Pay and Confirm Order button")
    public void the_user_clicks_pay_and_confirm_order_button() {
       paymentPage.clickConfirmAndPayButton();
    }

    @Then("the user verifies success message {string}")
    public void the_user_verifies_success_message(String successMessage) {
        assertTrue(BasePage.isElementWithTextVisible(Hooks.getPage(),successMessage));
    }



}
