package com.mycommerce.pages;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.mycommerce.appdata.AppConstant;
import com.mycommerce.utilities.ConfigReader;
import com.mycommerce.utilities.PlaywrightManager;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Getter
public class SideBarSection {
    private Page page;

        // =========left side bar =========
        private final Locator categories;
        private final Locator categoryToggles;
        private final Locator brands;


    public SideBarSection() {
        this.page = PlaywrightManager.getPage();
        // =========left side bar =========
        this.categories = page.locator("#accordian.panel-title");
        this.categoryToggles = page.locator("#accordian h4 a");
        this.brands = page.locator(".brands-name ul a");


    }

    public List<ElementHandle> getCategories(){
        return categories.elementHandles();
    }


    public void clickACategoryByText(String categoryText) {
        String cat = "text='" + categoryText + "'";
        Locator category = categoryToggles.locator(cat);
        category.click();

    }

    public void clickASubCategoryByText(String categoryText, String subcategoryText) {
        Locator subcategory  = page.locator("#"+categoryText+" ul").getByText(subcategoryText);
        subcategory.click();

    }


    public boolean isTheBrandVisible(String brand) {
        Locator brandLocator = brands.filter(new Locator.FilterOptions().setHasText(brand));
        return brandLocator.isVisible();
    }

    public void clickOnAnyBrandName() {

        int brandCount = brands.count();
        int brandIndex = new Random().nextInt(brandCount);
        ConfigReader.set("brandIndex", String.valueOf(brandIndex));
        brands.nth(brandIndex).click();
    }
    public List<String>getBrandNames() {
        List<String> brandNamesList = new ArrayList<>();
        int count = brands.count();
        for (int i=0; i<count; i++) {
            brandNamesList.add(brands.nth(i).textContent().substring(4));
        }
        return brandNamesList;
    }

    public String getExpectedBrandPageTitle(){
        String expectedPageTitle = AppConstant.HOME_PAGE_TITLE+" - " +getBrandNames().get(Integer.parseInt(ConfigReader.get("brandIndex")))+" Products";
        return expectedPageTitle;


    }
}
