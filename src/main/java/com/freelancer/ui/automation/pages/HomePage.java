package com.freelancer.ui.automation.pages;

import com.freelancer.ui.automation.core.FreeLancerBasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends FreeLancerBasePage {

    @FindBy(xpath = "//a[contains(text(),'Dashboard')]")
    private WebElement dashboardButton;

    @FindBy(xpath = "//input[@type='password']")
    private WebElement passwordTextBox;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginSubmitBtn;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void verifyDashBoardLinkIsDisplayed(){
        waitForVisibilityOfTheElement(dashboardButton);
        verifyData(isDashBoardButtonDislpayed(), "Dashboard Button");
    }

    public boolean isDashBoardButtonDislpayed(){
        return isDisplayed(dashboardButton);
    }
}
