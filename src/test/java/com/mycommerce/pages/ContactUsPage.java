package com.mycommerce.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.mycommerce.utilities.PlaywrightManager;
import lombok.Getter;

import java.nio.file.Paths;

@Getter
public class ContactUsPage {
    private Page page;
    private final Locator nameField;
    private final Locator emailField;
    private final Locator subjectField;
    private final Locator messageField;
    private final Locator chooseFileBtn;
    private final Locator submitBtn;
    private final Locator homeBtn;
    private final Locator successMessage;


    public ContactUsPage() {
        this.page = PlaywrightManager.getPage();
        this.nameField = page.locator("input[placeholder='Name']");
        this.emailField = page.locator("input[placeholder='Email']");
        this.subjectField = page.locator("input[placeholder='Subject']");
        this.messageField = page.locator("#message");
        this.chooseFileBtn = page.locator("input[name='upload_file']");
        this.submitBtn = page.locator("input[name='submit']");
        this.homeBtn = page.locator("a[class='btn btn-success'] span");
        this.successMessage= page.locator(".status.alert.alert-success:above(a.btn.btn-success)");


    }

    public void fillContactForm(String name, String email, String subject, String message) {
        nameField.fill(name);
        emailField.fill(email);
        subjectField.fill(subject);
        messageField.fill(message);

    }

    public void uploadFile(String path) {

        page.setInputFiles("input[name='upload_file']", Paths.get(path));

    }
    public void submitForm() {

        submitBtn.click();
    }



    public void clickHomeBtn() {
        homeBtn.click();
    }
    public boolean isSuccessMessageVisible(String expectedSuccessMessage) {
        Locator locWithSuccessMessage = page.locator("#contact-page").getByText(expectedSuccessMessage);

        return locWithSuccessMessage.isVisible();
    }

}
