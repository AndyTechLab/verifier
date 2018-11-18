package com.test.verifier.pageobjects;

import com.test.verifier.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private By closeButton = By.xpath(".//button[@class = 'sb-btn sb-btn--bare sb-btn--normal sb-modal-component__close']");
    private By passwordField = By.xpath(".//input[@id='login-form-password']");
    private By usernameFiled = By.xpath(".//input[@id='login-form-username']");
    private By loginButton = By.xpath(".//button[text()='Log in']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public boolean isPasswordFieldDisplayed() {
        waitForElementToAppear(passwordField);
        return driver.findElement(passwordField).isDisplayed();
    }


    public boolean isUsernameFieldDisplayed() {
        waitForElementToAppear(usernameFiled);
        return driver.findElement(usernameFiled).isDisplayed();
    }

    public boolean isLoginButtonDisplayed() {
        waitForElementToAppear(loginButton);
        return driver.findElement(loginButton).isDisplayed();
    }

    public MainPage clickCloseButton(){
        clickOnVisibleElement(closeButton);
        return new MainPage(driver);
    }
}
