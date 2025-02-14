package com.mycommerce.pages;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.mycommerce.utilities.PlaywrightManager;
import lombok.Getter;

import java.util.*;

@Getter
public class CartPage {
   private Page page;
   // ==================Locators =================
    private final Locator itemDescriptions;
    private final Locator itemPrices;
    private final Locator proceedToCheckoutBtn;

    public CartPage() {
        this.page = PlaywrightManager.getPage();
        this.itemDescriptions  = page.locator("table#cart_info_table tbody tr td.cart_description h4 a");
        this.itemPrices  = page.locator("table#cart_info_table tbody tr td.cart_price p");
        this.proceedToCheckoutBtn = page.locator(".btn.btn-default.check_out");
    }

    public int getItemCount() {
        return itemDescriptions.count();
    }
    //get items description
    public List<String> getItemDescriptions() {
        List<String> descriptions = new ArrayList<>();
        List<ElementHandle>elementHandles = itemDescriptions.elementHandles();
        for (ElementHandle elementHandle : elementHandles) {
            descriptions.add(elementHandle.innerText());
        }

        return descriptions;
    }

    //get items prices
    public List<String> getItemPrices() {
        List<String> prices = new ArrayList<>();
        List<ElementHandle>elementHandles = itemPrices.elementHandles();
        for (ElementHandle elementHandle : elementHandles) {
            prices.add(elementHandle.innerText());
        }
        return prices;
    }

    //remove item from cart
    public void removeItem(int index) {
        itemDescriptions.nth(index).locator("..").locator("button").click();
    }

    public CheckoutModal clickProceedToCheckoutBtn() {
        proceedToCheckoutBtn.click();
        return new CheckoutModal();
    }

   public Map<String,String> getRowData(int rowIndex) {
        Locator columnNames = page.locator("#cart_info_table").locator("thead").locator("tr").locator("td");
        Locator row = page.locator("#cart_info_table").locator("tbody").locator("tr").nth(rowIndex-1).locator("td");
        Map<String,String> rowData = new HashMap<>();
        int count = row.count();

        for (int i = 0; i <count-1; i++) {
            rowData.put(columnNames.nth(i).textContent(),row.nth(i).textContent());
        }
        return rowData;
   }

   public void clickXBtnInProductTable(int rowIndex) {
        if(page.locator("#cart_info_table").isVisible()) {
           Locator row= page.locator("#cart_info_table").locator("tbody").locator("tr").nth(rowIndex-1).locator("td");
           int count = row.count();
           row.nth(count-1).locator(".cart_quantity_delete").click();
       }else{
    throw new NoSuchElementException("The product table on cart page is not visible");
       }

   }

   public String getEmptyCartText(){
        return page.locator("#empty_cart").textContent();
   }

    //update item quantity
}
