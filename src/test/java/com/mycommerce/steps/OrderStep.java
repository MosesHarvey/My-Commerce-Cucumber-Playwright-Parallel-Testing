package com.mycommerce.steps;

import com.mycommerce.appdata.AddressInfo;
import com.mycommerce.appdata.AppConstant;
import com.mycommerce.pages.*;
import com.mycommerce.utilities.ConfigReader;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderStep {
    private  BasePage basePage = new BasePage();
    private  CartPage cartPage = new CartPage();
    private  CheckoutModal checkoutModal = new CheckoutModal();
    private  CheckoutPage checkoutPage = new CheckoutPage();
    private  PaymentPage paymentPage = new PaymentPage();
    private  ProductSection productSection = new ProductSection();


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

         assertEquals( "Mr. "+AddressInfo.firstName+ " " + AddressInfo.lastName,checkoutPage.getNameOnDeliveryAddress().textContent());
        assertEquals( "Mr. "+AddressInfo.firstName+ " " + AddressInfo.lastName,checkoutPage.getNameOnBillingAddress().textContent());
        assertEquals(AddressInfo.mobileNumber, checkoutPage.getPhoneNumberOnDeliveryAddress().textContent());
        assertEquals(AddressInfo.mobileNumber, checkoutPage.getPhoneNumberOnBillingAddress().textContent());
        String expectedCityStateZipCode = (AddressInfo.city + AddressInfo.state + AddressInfo.zipCode).replaceAll(" ", "");
        assertEquals(expectedCityStateZipCode, checkoutPage.getCitySatePostCodeOnDeliveryAddress().textContent().replaceAll("\\s",""));
        assertEquals(expectedCityStateZipCode, checkoutPage.getCitySatePostCodeOnBillingAddress().textContent().replaceAll("\\s",""));

    }

    @When("the user enters a description in comment text area and clicks Place Order")
    public void the_user_enters_a_description_in_comment_text_area_and_clicks_place_order() {

     checkoutPage.fillCommentBox("this is a good product");
     checkoutPage.clickPlaceOrderButton();
    }

    @When("the user enters payment details: Name on Card, Card Number, CVC, Expiration date")
    public void the_user_enters_payment_details_name_on_card_card_number_cvc_expiration_date() {

        paymentPage.fillPaymentDetails(ConfigReader.get("cardHolder"),ConfigReader.get("cardNumber"),
                ConfigReader.get("cvc"), ConfigReader.get("expiryMonth"),ConfigReader.get("expiryYear"));

    }

    @When("the user clicks Pay and Confirm Order button")
    public void the_user_clicks_pay_and_confirm_order_button() {
       paymentPage.clickConfirmAndPayButton();
    }

    @Then("the user verifies success message {string}")
    public void the_user_verifies_success_message(String successMessage) {
        assertTrue(basePage.isElementWithTextVisible(successMessage));
    }

    // ================ Place order: Login while checkout ================

    @Then("the user verifies registered Address Details and Review Your Order")
    public void the_user_verifies_registered_address_details_and_review_your_order() {
       String expectedName ="Mr. "+ AppConstant.FIRST_NAME+" "+AppConstant.LAST_NAME;
        assertEquals(expectedName,checkoutPage.getNameOnDeliveryAddress().textContent());
        assertEquals(expectedName,checkoutPage.getNameOnBillingAddress().textContent());
        assertEquals(AppConstant.MOBILE_NUMBER, checkoutPage.getPhoneNumberOnDeliveryAddress().textContent());
        assertEquals(AppConstant.MOBILE_NUMBER, checkoutPage.getPhoneNumberOnBillingAddress().textContent());
        String expectedCityStateZipCode = (AppConstant.CITY + AppConstant.STATE + AppConstant.ZIP_CODE).replaceAll(" ", "");
        assertEquals(expectedCityStateZipCode, checkoutPage.getCitySatePostCodeOnDeliveryAddress().textContent().replaceAll("\\s",""));
        assertEquals(expectedCityStateZipCode, checkoutPage.getCitySatePostCodeOnBillingAddress().textContent().replaceAll("\\s",""));

    }




}
