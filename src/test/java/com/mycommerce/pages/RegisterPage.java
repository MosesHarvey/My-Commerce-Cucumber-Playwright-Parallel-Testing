package com.mycommerce.pages;

import com.github.javafaker.Faker;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.mycommerce.appdata.AccountInfo;
import com.mycommerce.utilities.ConfigReader;
import lombok.Getter;

@Getter
public class RegisterPage {

    private final Page page;
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



    public RegisterPage(Page page) {
        this.page = page;
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
    public void fillAccountInfo(){
page.getByLabel("Mr.").check();
        String fullName = faker.name().fullName();
        registerNameBox.fill(fullName);
        AccountInfo.name =fullName;
      registerPasswordBox.fill(faker.internet().password());

    }
    public void fillAddressInfo(){
        fistNameBox.fill(faker.name().firstName());
        lastNameBox.fill(faker.name().lastName());
        addressBox.fill(faker.address().streetAddress());
       // countryBox.fill(faker.address().country());
        stateBox.fill(faker.address().state());
        cityBox.fill(faker.address().city());
        zipCodeBox.fill(faker.address().zipCode());
        mobileNumberBox.fill(faker.phoneNumber().cellPhone());

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
