package com.mycommerce.pages;


import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import lombok.Getter;


@Getter
public class FooterComponent {

    private final Page page;

    private final Locator subscribeInputBox;
    private final Locator subscribeArrow;
    private final Locator subscribeSuccessMessage;

    public FooterComponent(Page page) {
        this.page = page;
        this.subscribeInputBox = page.locator("#susbscribe_email");
        this.subscribeArrow = page.locator("#subscribe");
        this.subscribeSuccessMessage = page.locator("#footer .alert-success.alert");
    }

    public void subscribeToNewsletter(String email){

        subscribeInputBox.fill(email);
        subscribeArrow.click();
    }

    public boolean isSubscribeSuccessMessageVisible(){
        return subscribeSuccessMessage.isVisible();
    }
    public String subscribeSuccessText(){
        if(isSubscribeSuccessMessageVisible()){
            return subscribeSuccessMessage.textContent();
        } else{
            return "Subscribe success message not visible";
        }
    }
}
