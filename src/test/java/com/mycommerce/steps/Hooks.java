package com.mycommerce.steps;


import com.microsoft.playwright.*;
import com.microsoft.playwright.options.ScreenshotType;

import com.mycommerce.utilities.ConfigReader;
import io.cucumber.java.*;

import java.awt.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Hooks {

    private static ThreadLocal<Playwright> playwright = new ThreadLocal<>();
    private static ThreadLocal<Browser> browser = new ThreadLocal<>();
    private static ThreadLocal<BrowserContext> context = new ThreadLocal<>();
    private static ThreadLocal<Page> page = new ThreadLocal<>();

    public static Page getPage() {
        return page.get();
    }

    @BeforeAll
    public static void globalSetUp() throws Exception {
        // Initialize Playwright and Browser once for the entire test suite
        if (getPage() == null) {
            // Read browser type from configuration
            String browserName = ConfigReader.get("browser");
            boolean isHeadless = Boolean.parseBoolean(ConfigReader.get("isHeadless")); // Optional: default to false

            // Initialize Playwright
            playwright.set(Playwright.create());

            switch (browserName.toLowerCase()) {
                case "chromium":
                    browser.set(playwright.get().chromium().launch(new BrowserType.LaunchOptions().setHeadless(isHeadless)));
                    break;
                case "chrome":
                    browser.set(playwright.get().chromium().launch(new BrowserType.LaunchOptions()
                            .setChannel("chrome") // Use Chrome specifically
                            .setHeadless(isHeadless)));
                    break;
                case "firefox":
                    browser.set(playwright.get().firefox().launch(new BrowserType.LaunchOptions().setHeadless(isHeadless)));

                    break;
                case "webkit":
                    browser.set(playwright.get().webkit().launch(new BrowserType.LaunchOptions().setHeadless(isHeadless)));
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported browser type: " + browserName);
            }


        }else{
            throw new Exception("A page is already set, please check!");
        }
        System.out.println("Global setup completed: Playwright and Browser initialized");
    }

    @Before
    public void setUp() {

        Toolkit toolkit = Toolkit.getDefaultToolkit();

        // Get the screen size as a Dimension object
        Dimension screenSize = toolkit.getScreenSize();

        // Extract the width and height
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();
       // Create a new browser context and page before each scenario


        context.set(browser.get().newContext(new Browser.NewContextOptions()
                .setViewportSize(screenWidth, screenHeight).setScreenSize(screenWidth, screenHeight)));
        page.set(browser.get().newPage());


        System.out.println("Scenario setup: Browser context and page created");
    }

    @After
    public void tearDown(Scenario scenario) {
     captureScreenShotForFailedScenario(scenario);

     // Close the browser context and page after each scenario
        if (getPage() != null) {
            getPage().close();
            System.out.println("Scenario teardown: Page closed");
        }
        if (context.get() != null) {
            context.get().close();
            System.out.println("Scenario teardown: Browser context closed");
        }
    }

    @AfterAll
    public static void globalTearDown() {
        // Close the browser and Playwright after all tests
        if (browser.get() != null) {
            browser.get().close();
            System.out.println("Global teardown: Browser closed");
        }
        if (playwright.get() != null) {
            playwright.get().close();
            System.out.println("Global teardown: Playwright closed");
        }

        //Remove the threadlocal values
        playwright.remove();
        browser.remove();
        context.remove();
        page.remove();
    }




    private void captureScreenShotForFailedScenario(Scenario scenario){
        if (scenario.isFailed()) {
            byte[] screenshot = getPage().screenshot(new Page.ScreenshotOptions()
                    .setFullPage(true) // Capture full page
                    .setType(ScreenshotType.PNG));

            // Attach screenshot to Cucumber report (if using reporting tools)
            scenario.attach(screenshot, "image/png", "Screenshot on Failure");

            // Save locally
            Path screenshotPath = Paths.get("screenshots", scenario.getName() + ".png");
            try {
                java.nio.file.Files.write(screenshotPath, screenshot);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}

