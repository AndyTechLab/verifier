package com.test.verifier.tests;

import com.test.verifier.core.BaseTest;
import com.test.verifier.pageobjects.LoginPage;
import com.test.verifier.pageobjects.MainPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchTest extends BaseTest {

    private static String defaultGame = "Rome: Rise of an Empire";
    private static String targetGame = System.getProperty("target.game", defaultGame);

    @Test
    public void gameExistAndCanBeAccessed() {
        MainPage mainPage = new MainPage(getDriver());
        LoginPage loginPage = mainPage.clickGameSearchButton().
                fillSearchField(targetGame).
                navigateToGameTile(targetGame).
                clickMoreButton().
                clickPlayNowButton();

        Assert.assertTrue(loginPage.isLoginButtonDisplayed());
        Assert.assertTrue(loginPage.isPasswordFieldDisplayed());
        Assert.assertTrue(loginPage.isUsernameFieldDisplayed());
    }
}
