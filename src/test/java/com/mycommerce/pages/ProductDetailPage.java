package com.mycommerce.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class ProductDetailPage {
    private final Page page;

    // ============== Locators ==================
    private final Locator productDetails;
    private final Locator productName;
    private final Locator productCategory;
    private final Locator productPrice;
    private final Locator availability;
    private final Locator condition;
    private final Locator brand;

    private final Locator quantityInput;
    private final Locator addToCartBtnOnProductDetailPage;
    // == write your review ====
    private final Locator reviewHeading;
    private final Locator reviewForm;



    public ProductDetailPage(Page page) {
        this.page = page;
        this.productDetails = page.locator(".product-details");
        this.productName = page.locator("div[class='product-information'] h2");
        this.productCategory = page.locator("div.product-information h2 >> xpath=following-sibling::p").first();
        this.productPrice = page.locator("div[class='product-information'] span span");
        this.availability = page.locator("//b[normalize-space()='Availability:']/parent::p");
        this.condition = page.locator("//b[normalize-space()='Condition:']/parent::p");
        this.brand = page.locator("//b[normalize-space()='Brand:']/parent::p");

        this.quantityInput = page.locator("#quantity");
        this.addToCartBtnOnProductDetailPage = page.locator("//button[normalize-space()='Add to cart']");

        // =========== write your review =================
        this.reviewHeading = page.locator("a[href='#reviews']");
        this.reviewForm = page.locator("#review-form");


    }


    public Locator getProductDetails() {
        return productDetails;
    }

    public Locator getProductName() {
        return productName;
    }

    public Locator getProductCategory() {
        return productCategory;
    }

    public Locator getProductPrice() {
        return productPrice;
    }

    public Locator getAvailability() {
        return availability;
    }

    public Locator getCondition() {
        return condition;
    }

    public Locator getBrand() {
        return brand;
    }

    public Locator getQuantityInput() {
        return quantityInput;
    }

    public Locator getAddToCartBtnOnProductDetailPage() {
        return addToCartBtnOnProductDetailPage;
    }

    public Locator getReviewHeading() {
        return reviewHeading;
    }

    public Locator getReviewForm() {
        return reviewForm;
    }

    public String increaseProductQuantityTo(int quantity) {
        quantityInput.clear();
        quantityInput.fill(String.valueOf(quantity));
        return quantityInput.inputValue();
    }


    public void clickAddToCartBtn() {
        addToCartBtnOnProductDetailPage.click();
    }

    public AddedModal navigateToAddedModal() {
        return new AddedModal(page);
    }

    // =========== write your review =================
    public boolean isWriteReviewHeadingVisible() {
        return reviewHeading.isVisible();
    }

    public void fillNameOnReview(String name) {
        reviewForm.locator("#name").fill(name);
    }
    public void fillEmailOnReview(String email) {
        reviewForm.locator("#email").fill(email);
    }
    public void fillReviewTextarea(String reviewText) {
        reviewForm.locator("#review").fill(reviewText);
    }
    public void clickSubmitReviewBtn() {
        reviewForm.locator("#button-review").click();
    }

    public String getWriteReviewHeading() {
        return reviewHeading.textContent();
    }
}