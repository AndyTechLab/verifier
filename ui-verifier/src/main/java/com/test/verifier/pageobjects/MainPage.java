package com.test.verifier.pageobjects;

import com.test.verifier.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage extends BasePage {

    private By magniferButton = By.xpath(".//button[@data-test = 'game-search-button']");
    private By searchField = By.xpath(".//input[@data-test = 'game-search-field']");
    private By playNowButton = By.xpath((".//button[@data-test = 'tile-details-button-play']"));
    private By moreButton = By.xpath(".//button[@data-test = 'tile-menu-button-more']");
    private String tileXpathTemplate = ".//img[@alt='%s']";

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public MainPage clickGameSearchButton() {
        clickOnVisibleElement(magniferButton);
        return this;
    }

    public MainPage fillSearchField(String searchValue) {
        waitForElementToAppear(searchField);
        driver.findElement(searchField).sendKeys(searchValue);
        return this;
    }

    public MainPage navigateToGameTile(String game) {
        By tileLocator = By.xpath(String.format(tileXpathTemplate, game));
        moveToElement(tileLocator);
        return this;
    }

    public MainPage clickMoreButton() {
        clickOnVisibleElement(moreButton);
        return this;
    }

    public LoginPage clickPlayNowButton() {
        scrollToElement(playNowButton);
        clickOnVisibleElement(playNowButton);
        return new LoginPage(driver);
    }
}
