package com.freelancer.ui.automation.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class DriverUtils {
    private final int waitTime = 10;
    private WebDriver driver;
    private WebDriverWait wait;

    public DriverUtils(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10L);
    }

    public void refresh() {
        this.driver.navigate().refresh();
    }

    public void getTitle() {
        this.driver.getTitle();
    }

    public void getCurrentUrl() {
        this.driver.getCurrentUrl();
    }

    public void selectOption(WebElement element, String option) {
        Select select = new Select(element);
        select.selectByValue(option);
    }

    public List<String> getAllDropDownValue(String elementId) {
        Select oSelect = new Select(this.driver.findElement(By.id(elementId)));
        List<WebElement> elementCount = oSelect.getOptions();
        int iSize = elementCount.size();
        List<String> optionsValue = null;

        for (int i = 0; i < iSize; ++i) {
            String sValue = ((WebElement) elementCount.get(i)).getText();
            ((List) optionsValue).add(sValue);
        }
        return (List) optionsValue;
    }

    public void waitForElementToBeClickable(WebElement element) {
        this.wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForVisibilityOfTheElement(WebElement element) {
        this.wait.until(ExpectedConditions.visibilityOf(element));

    }

    public void shortWaitTime(){
        try{
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
