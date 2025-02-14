package com.mycommerce.pages;

import com.microsoft.playwright.Download;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.mycommerce.utilities.PlaywrightManager;
import lombok.Getter;

@Getter
public class CheckoutPage {
    private Page page;
    // ============== Locators ==================
    private final Locator addressDetailsHeading;



    private final Locator yourDeliveryAddress;
    private final Locator yourBillingAddress;
    private final Locator nameOnDeliveryAddress;
    private final Locator citySatePostCodeOnDeliveryAddress;
    private final Locator phoneNumberOnDeliveryAddress;

    private final Locator nameOnBillingAddress;
    private final Locator citySatePostCodeOnBillingAddress;
    private final Locator phoneNumberOnBillingAddress;

    private  final Locator totalPrice;
    private final Locator commentBox;
    private final Locator placeOrderBtn;

    private final Locator downloadButton;

    public CheckoutPage() {
        this.page = PlaywrightManager.getPage();
        this.addressDetailsHeading = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Address Details"));
        this.yourDeliveryAddress = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Your delivery address"));
        this.yourBillingAddress = page.getByRole(AriaRole.HEADING,new Page.GetByRoleOptions().setName("Your billing address"));
        this.nameOnDeliveryAddress = page.locator("#address_delivery .address_firstname");
        this.citySatePostCodeOnDeliveryAddress = page.locator("#address_delivery .address_city");
        this.phoneNumberOnDeliveryAddress = page.locator("#address_delivery .address_phone");

        this.nameOnBillingAddress = page.locator("#address_invoice .address_firstname");
        this.citySatePostCodeOnBillingAddress = page.locator("#address_invoice .address_city");
        this.phoneNumberOnBillingAddress = page.locator("#address_invoice .address_phone");

        this.totalPrice = page.locator(".cart_total .cart_total_price");
        this.commentBox = page.locator("#ordermsg .form-control");
        this.placeOrderBtn = page.locator("a[href='/payment'].btn");
        this.downloadButton = page.locator("#form a.check_out");

    }



    public void fillCommentBox(String yourComment){
        commentBox.fill(yourComment);
    }

    public void clickPlaceOrderButton(){
        placeOrderBtn.click();
    }

   public Download clickDownload(){
       Download download = page.waitForDownload(() -> {
           downloadButton.click();  // Replace with the actual selector
       });
       return download;
   }
   public boolean isFileDownloaded(Download download){
        return download.path().toFile().exists();
   }

}
