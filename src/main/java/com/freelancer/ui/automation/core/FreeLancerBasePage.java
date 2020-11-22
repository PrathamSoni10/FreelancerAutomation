package com.freelancer.ui.automation.core;

import com.freelancer.ui.automation.pages.HomePage;
import com.freelancer.ui.automation.pages.LoginPage;
import com.freelancer.ui.automation.pages.SignUpPage;
import org.openqa.selenium.WebDriver;

public class FreeLancerBasePage extends BasePage {

    public static SignUpPage signUpPage;
    public static LoginPage loginPage;
    public static HomePage homePage;

    public FreeLancerBasePage(WebDriver driver){
        super(driver);
    }

    public void setObjects() {
        signUpPage = signUpPage();
        loginPage = loginPage();
    }

    public SignUpPage signUpPage(){
        return initPage(SignUpPage.class);
    }

    public LoginPage loginPage(){
        return initPage(LoginPage.class);
    }


}
