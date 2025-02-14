package com.mycommerce.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.mycommerce.utilities.PlaywrightManager;
import lombok.Getter;

@Getter
public class PaymentPage {

    private Page page;
     // ============== Locators ==================
    private final Locator paymentHeading;

    private final Locator nameOnCardInput;
    private final Locator cardNumberInput;
    private final Locator civicNumberInput;
    private final Locator expirationMonthInput;
    private final Locator expirationYearInput;
    private final Locator confirmAndPayOrder;


    public PaymentPage() {
        this.page = PlaywrightManager.getPage();

        // ============== Locators ==================
        this.paymentHeading = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Payment"));
        this.nameOnCardInput = page.locator("input[name='name_on_card']");
        this.cardNumberInput = page.locator("input[name='card_number']");
        this.civicNumberInput = page.locator("input[name='cvc']");
        this.expirationMonthInput = page.locator("input[name='expiry_month']");
        this.expirationYearInput = page.locator("input[name='expiry_year'][data-qa='expiry-year']");
        this.confirmAndPayOrder = page.locator("button#submit[data-qa='pay-button']");

    }

    public void fillPaymentDetails(String cardHolder, String cardNumber, String cvc, String expiryMonth, String expiryYear){
        nameOnCardInput.fill(cardHolder);
        cardNumberInput.fill(cardNumber);
        civicNumberInput.fill(cvc);
        expirationMonthInput.fill(expiryMonth);
        expirationYearInput.fill(expiryYear);
    }

    public void clickConfirmAndPayButton(){
        confirmAndPayOrder.click();
    }

}
