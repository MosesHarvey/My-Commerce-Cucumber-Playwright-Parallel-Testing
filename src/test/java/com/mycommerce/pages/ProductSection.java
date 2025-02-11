package com.mycommerce.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.LoadState;
import com.mycommerce.appdata.AppConstant;
import com.mycommerce.utilities.ConfigReader;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Getter
public class ProductSection {
    private final Page page;
    private final Locator products;
    private final Locator productNames;
    private final Locator searchBox;
    private final Locator searchIcon;
    private final Locator productOverlay;
    private final Locator addToCartBtnOnOverlay;
    private final Locator viewProducts;

    // =========Products =========
    private final Locator featuredProductsCards;
    private final Locator recommendedsProductsCards;

    public ProductSection(Page page) {
        this.page = page;

        this.products = page.locator(".single-products");
        this.productOverlay = page.locator(".product-overlay");
        this.addToCartBtnOnOverlay = page.locator(".product-overlay .overlay-content a");
        this.productNames = page.locator("(//div[@class='productinfo text-center'])/p");
        this.searchBox = page.locator("#search_product");
        this.searchIcon = page.locator("#submit_search");
       this.viewProducts = page.locator(".choose a");

        // =========Products =========
        this.featuredProductsCards = page.locator(".features_items .product-image-wrapper");
        this.recommendedsProductsCards = page.locator(".recommended_items .product-image-wrapper");

    }

    public void inputSearchTerm(String searchTerm) {
        searchBox.fill(searchTerm);
    }

    public void clickSearchIcon() {
        searchIcon.click();
        page.waitForLoadState(LoadState.LOAD);
    }

    public int getProductCount() {
        return featuredProductsCards.count();
    }

    public List<String> getProductNames() {
        List<String> productNamesList = new ArrayList<>();
        int count = productNames.count();
        for (int i = 0; i < count; i++) {

            System.out.println(productNames.nth(i).textContent());
            products.nth(i).hover();
            productNamesList.add(productNames.nth(i).textContent());
            if (i == count - 1) {
                page.mouse().move(0, 0);
            }
        }
        return productNamesList;
    }


    public void hoverOverAProductAndClickAddToCart(int nthProduct) {
        if (products.count() > 0) {
            products.nth(nthProduct - 1).hover();
            productOverlay.nth(nthProduct - 1).waitFor();
            ConfigReader.set("productName" + nthProduct, productOverlay.nth(nthProduct - 1).locator("p").textContent());
            ConfigReader.set("productPrice" + nthProduct, productOverlay.nth(nthProduct - 1).locator("h2").textContent());
            addToCartBtnOnOverlay.nth(nthProduct - 1).click();
        } else {
            throw new IllegalStateException("Product list is empty");
        }
    }


    public void clickViewProductButtonByIndex(int index) {
        Locator viewProductBtn = featuredProductsCards.nth(index).getByRole(AriaRole.LINK, new Locator.GetByRoleOptions().setName("View Product"));
        viewProductBtn.click();

    }

    public void addProductToCartByIndex(int index) {
        featuredProductsCards.nth(index).locator(".single-products").hover();
        featuredProductsCards.nth(index).locator(".product-overlay").waitFor();
        Locator addToCartBtn = featuredProductsCards.nth(index).locator(".overlay-content").getByText("Add to cart");
        addToCartBtn.click();

    }

    public void clickViewProductButtonOfAnyProductOnHomePage() {

        int count = viewProducts.count();
        System.out.println("count: " + count);
        Random random = new Random();
        int index;
        if (count > 0) {
            index = random.nextInt(count);
            viewProducts.nth(index).click();
            page.waitForLoadState(LoadState.LOAD);
        } else {
            throw new IllegalStateException("ViewProduct count should be greater than 0");
        }

    }

    public void addARecommendedProductToCartByIndex(int index) {

        Locator addToCartBtn = recommendedsProductsCards.nth(index).getByText("Add to cart");
        String recommendProductName = recommendedsProductsCards.nth(index).locator("p").textContent().trim();
        ConfigReader.set("recommendProductName", recommendProductName);
        addToCartBtn.click();

    }


}
