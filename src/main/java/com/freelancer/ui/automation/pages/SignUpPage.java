package com.freelancer.ui.automation.pages;


import com.freelancer.ui.automation.core.FreeLancerBasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignUpPage extends FreeLancerBasePage {

    public SignUpPage(WebDriver driver) {
        super(driver);
    }

//    @FindBy(class = "LinkElement ng-star-inserted")
//    private WebElement signUpLink1;

    @FindBy(xpath = "//a[@class = 'LinkElement ng-star-inserted']")
    private WebElement signUpLink;

    @FindBy(xpath = "//button[@text = ' Join Freelancer ']")
    private WebElement joinFreelancerText;

    @FindBy(xpath = "//button[@text = ' Sign Up ']")
    private WebElement signUpText;

    @FindBy(xpath = "//input[@type='email']")
    private WebElement emailTextBox;

    @FindBy(xpath = "//input[@type='password']")
    private WebElement passwordTextBox;

    @FindBy(xpath = "//label[@class='CheckboxLabel']")
    private WebElement agreeCheckBox;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement joinFreelancerSubmitBtn;

    @FindBy(xpath = "//input[@class='NativeElement ng-untouched ng-pristine ng-invalid ng-star-inserted']")
    private WebElement userNameTextBox;

    @FindBy(xpath = "//button[@class='ButtonElement ng-star-inserted' and @type='submit']")
    private WebElement nextBtn;

    @FindBy(xpath = "//div[contains(text(),' I want to work ')]")
    private WebElement iWantToWorkBtn;

    @FindBy(xpath = "//div[contains(text(),' I want to hire ')]")
    private WebElement iWantToHireBtn;

    @FindBy(xpath = "//h1[contains(text(),' Select account type ')]")
    private WebElement selectAccountTypeText;

    @FindBy(xpath = "//div[contains(text(),' Welcome back, ')]")
    private WebElement welcomeBackText;



    public void performSignUp() {
        emailTextBox.sendKeys("pratham.soni10@gmail.com");
        passwordTextBox.sendKeys("");
        agreeCheckBox.click();
        joinFreelancerSubmitBtn.click();
    }


    public void clickOnSignUpLink() {
        click(signUpLink);
    }


}
