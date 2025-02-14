package com.mycommerce.utilities;

import com.microsoft.playwright.*;

import java.awt.*;

public class PlaywrightManager {

        private static ThreadLocal<Playwright> playwright = new ThreadLocal<>();
        private static ThreadLocal<Browser> browser = new ThreadLocal<>();
        private static ThreadLocal<BrowserContext> browserContext = new ThreadLocal<>();
        private static ThreadLocal<Page> page = new ThreadLocal<>();

        public static void init() throws Exception {
            // Initialize Playwright and Browser once for the entire test suite
            if (getPage() == null) {
                // Read browser type from configuration
                String browserName = ConfigReader.get("browser");
                boolean isHeadless = Boolean.parseBoolean(ConfigReader.get("isHeadless")); // Optional: default to false

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

            // Fit the browser to user screen size
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Dimension screenSize = toolkit.getScreenSize();
            int screenWidth = (int) screenSize.getWidth();
            int screenHeight = (int) screenSize.getHeight();

            browserContext.set(browser.get().newContext(new Browser.NewContextOptions()
                    .setViewportSize(screenWidth, screenHeight).setScreenSize(screenWidth, screenHeight)));
            page.set(browserContext.get().newPage());
        }

        public static Page getPage() {
            return page.get();
        }

        public static void close() {
            if (browser.get() != null) {
                browser.get().close();
                playwright.get().close();
            }
            playwright.remove();
            browser.remove();
            browserContext.remove();
            page.remove();
        }

}
