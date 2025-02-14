package com.mycommerce.steps;


import com.microsoft.playwright.*;
import com.microsoft.playwright.options.ScreenshotType;

import com.mycommerce.utilities.ConfigReader;
import com.mycommerce.utilities.PlaywrightManager;
import io.cucumber.java.*;

import java.awt.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Hooks {


    @Before
    public void setUp() throws Exception {

        PlaywrightManager.init();
    }

    @After
    public void tearDown(Scenario scenario) {
     captureScreenShotForFailedScenario(scenario);
     PlaywrightManager.close();
    }



    private void captureScreenShotForFailedScenario(Scenario scenario){
        if (scenario.isFailed()) {
            byte[] screenshot = PlaywrightManager.getPage().screenshot(new Page.ScreenshotOptions()
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

