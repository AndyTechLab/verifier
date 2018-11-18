package com.test.verifier.core;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BasePage {

    private static final int TIMEOUT = 10;
    private static final int POLLING = 100;

    protected WebDriver driver;
    private WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, TIMEOUT, POLLING);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, TIMEOUT), this);
    }

    protected void waitForElementToAppear(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void waitForElementStale(By locator) {
        wait.until(ExpectedConditions.stalenessOf(driver.findElement(locator)));
    }

    protected void clickOnVisibleElement(By locator) {
        waitForElementToAppear(locator);
        driver.findElement(locator).click();
    }

    protected void moveToElement(By locator) {
        int smallWaitInterval = 2000;
        waitForElementStale(locator);
        Actions actions = new Actions(driver);
        actions.pause(smallWaitInterval).moveToElement(driver.findElement(locator)).pause(smallWaitInterval).build().perform();
    }

    protected void scrollToElement(By locator) {
        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }
}
