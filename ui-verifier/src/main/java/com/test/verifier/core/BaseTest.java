package com.test.verifier.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    WebDriver driver;
    private static String baseUrl = "https://vegas.williamhill.com/";

    @BeforeClass
    public void beforeSuite() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/target/classes/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://vegas.williamhill.com/");
    }

    @AfterClass
    public void afterSuite() {
        if (null != driver) {
            driver.close();
            driver.quit();
        }
    }

    public WebDriver getDriver() {
        return driver;
    }
}
