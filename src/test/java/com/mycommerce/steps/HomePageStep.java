package com.mycommerce.steps;

import com.microsoft.playwright.Page;
import com.mycommerce.appdata.AppConstant;
import com.mycommerce.pages.HeaderComponent;
import com.mycommerce.pages.BasePage;
import com.mycommerce.pages.SideBarSection;
import com.mycommerce.utilities.ConfigReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.SoftAssertions;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class HomePageStep  {

    private final Page page;
    private HeaderComponent headerComponent;
    private SideBarSection sideBar ;

    private static String categoryName;
    private static String subcategoryName;

    SoftAssertions soft;
    private BasePage basePage;

    public HomePageStep() {
        page = Hooks.getPage();
        basePage = new BasePage(page);
        basePage.navigateToGivenUrl(page,ConfigReader.get("url"));
        headerComponent = new HeaderComponent(page);
        //page.waitForLoadState();

       sideBar = new SideBarSection(page);
       soft =   new SoftAssertions();
    }


    @Given("the user is on the home page")
    public void the_user_is_on_the_home_page() {

        basePage.clickConsentBtn();
        System.out.println("the user on the home page");


    }


    @Then("the user should see page title {string}")
    public void the_User_Should_See_Page_Title(String title) {
        System.out.println("Expected: "+title);
        System.out.println("Actual: "+basePage.getPageTitle(page));

        assertEquals(title, basePage.getPageTitle(page));
    }

    @Then("the user should see following tabs:")
    public void the_user_should_see_following_tabs(List<String> tabNames) {
        tabNames.forEach(tabName-> soft.assertThat(headerComponent.isTheTabWithGivenNameVisible(tabName)));

    }

    @Then("the user should see following headings:")
    public void the_user_should_see_following_headings(List<String>headings) {
        headings.forEach(heading-> System.out.println(heading));
        headings.forEach(heading-> soft.assertThat(basePage.isTheHeadingWithGivenTextVisible(heading)));
        soft.assertAll();
    }

    @Then("the user should Automation Exercise Logo")
    public void the_user_should_automation_exercise_logo() {
       assertTrue(headerComponent.isLogoVisible(), "Logo is not visible");
    }

    // ================== Verify Test Cases Page =================
    @When("the user clicks on Test Cases tab")
    public void the_user_clicks_on_test_cases_tab() {
       headerComponent.navigateToTestCasesPage();
    }

    // ============== View Products through categories and sub categories =================
    @Then("the user should see following categories on the left side bar:")
    public void the_user_should_see_following_categories_on_the_left_side_bar(List<String> categories) {
        sideBar.getCategories().forEach(category -> {
           assertTrue(categories.toString().contains(category.textContent().trim()));

       });
    }

    @When("the user clicks on {string} category")
    public void the_user_clicks_on_category(String categoryText) {
        sideBar.clickACategoryByText(categoryText);
    }



    @When("the user clicks on any sub-category link under {string} category, for example: {string}")
    public void the_user_clicks_on_any_sub_category_link_under_category_for_example(String category, String subcategory) {
        categoryName = category;
        sideBar.clickASubCategoryByText(category,subcategory);
        subcategoryName = subcategory;

    }

    @Then("the user verifies that the category page is displayed")
    public void the_user_verifies_that_the_category_page_is_displayed() {
        String expectedPageTitle = AppConstant.HOME_PAGE_TITLE+" - "+subcategoryName+" Products";
        System.out.println(expectedPageTitle);
        assertEquals(expectedPageTitle, basePage.getPageTitle(page));
    }

    @Then("the user should see the heading which include both category and sub category")
    public void the_user_should_see_the_heading_which_include_both_category_and_sub_category() {
        String expectedHeading = categoryName+" - "+subcategoryName +" Products";
        System.out.println(expectedHeading);
        assertTrue(basePage.isTheHeadingWithGivenTextVisible(expectedHeading));
    }

    // ============== Verify Scroll Up using 'Arrow' button and Scroll Down functionality =================
    @When("the user scrolls down the page to the bottom")
    public void the_user_scrolls_down_the_page_to_the_bottom() {
        basePage.scrollToBottom();
    }

    @When("the user clicks on the arrow at the bottom right side to move upward")
    public void the_user_clicks_on_the_arrow_at_the_bottom_right_side_to_move_upward() {
       basePage.scrollToTop();
    }

    @Then("the user verifies that the page is scrolled up and {string} text is visible on screen")
    public void the_user_verifies_that_the_page_is_scrolled_up_and_text_is_visible_on_screen(String fullFledgedHeading) {
     basePage.isTheHeadingWithGivenTextVisible(fullFledgedHeading);
    }

    // ============== Verify Scroll Up without using 'Arrow' button and Scroll Down functionality =================


    @When("the user scrolls up the page to the top")
    public void the_user_scrolls_up_the_page_to_the_top() {
       basePage.scrollToTopWithoutArrow();
    }








}
