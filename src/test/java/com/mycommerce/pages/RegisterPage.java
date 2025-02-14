package com.mycommerce.pages;

import com.github.javafaker.Faker;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.mycommerce.utilities.PlaywrightManager;
import lombok.Getter;

@Getter
public class RegisterPage {

    private Page page;
    private Faker faker;
    private final  Locator enterAccounInformationHeading;
    private final Locator registerNameBox;
    private final Locator registerPasswordBox;
    private final Locator fistNameBox;
    private final Locator lastNameBox;
    private final Locator addressBox;
    private final Locator countryBox;
    private final Locator stateBox;
    private final Locator cityBox;
    private final Locator zipCodeBox;
    private final Locator mobileNumberBox;
    private  final Locator createAccountBtn;
    private final Locator continueButton;



    public RegisterPage() {
        this.page = PlaywrightManager.getPage();
        this.faker = new Faker();
        this.enterAccounInformationHeading =  page.getByText("ENTER ACCOUNT INFORMATION").first();
        this.registerNameBox = page.getByLabel("Name *", new Page.GetByLabelOptions().setExact(true));
        this.registerPasswordBox = page.getByLabel("Password *");
        this.fistNameBox = page.getByLabel("First name *");
        this.lastNameBox = page.getByLabel("Last name *");
        this.addressBox = page.getByLabel("Address * (Street address, P.");
        this.countryBox = page.getByLabel("Country *");
        this.stateBox = page.getByLabel("State *");
        this.cityBox = page.getByLabel("City *");
        this.zipCodeBox = page.locator("#zipcode");
        this.mobileNumberBox = page.getByLabel("Mobile Number *");
        this.createAccountBtn = page.locator("button[data-qa='create-account']");
        this.continueButton = page.locator("[data-qa='continue-button']");



    }
    public String getEnterAccountInformationHeading() {
        return enterAccounInformationHeading.innerText();
    }
    public void fillAccountInfo(String fullName, String password){

        // filling account info
        page.getByLabel("Mr.").check();
        registerNameBox.fill(fullName);
        registerPasswordBox.fill(password);

    }
    public void fillAddressInfo(String firstName, String lastName, String address, String city, String state, String zipCode, String phoneNumber){

        // filling address info
        fistNameBox.fill(firstName);
        lastNameBox.fill(lastName);
        addressBox.fill(address);
       // countryBox.fill(faker.address().country());
        stateBox.fill(state);
        cityBox.fill(city);
        zipCodeBox.fill(zipCode);
        mobileNumberBox.fill(phoneNumber);

    }

    public void clickCreateAccountButton(){
        createAccountBtn.click();
    }
    public void clickContinueButton(){
        continueButton.click();
    }

   public boolean isErrorMessageVisible(String errorMessage){
        Locator errMessage = page.getByText(errorMessage);
        return errMessage.isVisible();
   }


    public String getSignUpHeading(String visibleText) {
        return this.page.getByText(visibleText).textContent();
    }
}
