package com.freelancer.ui.automation.pages;

import com.freelancer.ui.automation.core.FreeLancerBasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends FreeLancerBasePage {

    @FindBy(xpath = "//input[@type='email']")
    private WebElement userNameTextBox;

    @FindBy(xpath = "//input[@type='password']")
    private WebElement passwordTextBox;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginSubmitBtn;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(String userId, String password) {
        waitForElementToBeClickable(userNameTextBox);
        sendKeys(userNameTextBox, userId);
        waitForElementToBeClickable(passwordTextBox);
        sendKeys(passwordTextBox, password);
        waitForElementToBeClickable(loginSubmitBtn);
        click(loginSubmitBtn);
    }
}
