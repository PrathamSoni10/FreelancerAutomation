package com.freelancer.ui.automation.core;

import cucumber.api.Scenario;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class ActionDriver {

    protected WebDriver driver;

    private static final Logger LOGGER = LoggerFactory.getLogger(ActionDriver.class);
    public static Properties properties = new Properties();

    public ActionDriver(WebDriver driver) {
        this.driver = driver;
    }

    public <T> T initPage(Class<T> pageClass) {
        LOGGER.info("Initializing all web elements of page :: {}", pageClass);
        return PageFactory.initElements(driver, pageClass);
    }

    public HashMap<String, String> getproperties(String elementName) {
        String values = properties.getProperty(elementName);
        HashMap<String, String> locatorsMap = new HashMap();
        String[] locatorArray = values.split(":", 2);
        locatorsMap.put("locatorType", locatorArray[0]);
        locatorsMap.put("locatorValue", locatorArray[1]);
        return locatorsMap;
    }

    public WebElement findElement(String elementName) {
        HashMap<String, String> locatorsMap = getproperties(elementName);
        return findElement(locatorsMap);
    }

    public List<WebElement> findElements(String elementName) {
        HashMap<String, String> locatorsMap = getproperties(elementName);
        return findElements(locatorsMap);
    }

    public WebElement findElement(HashMap<String, String> locatorsMap) {
        By by = getLocatorType(locatorsMap.get("locatorType"), locatorsMap.get("locatorValue"));
        return driver.findElement(by);
    }

    public List<WebElement> findElements(HashMap<String, String> locatorsMap) {
        By by = getLocatorType(locatorsMap.get("locatorType"), locatorsMap.get("locatorValue"));
        return driver.findElements(by);
    }

    public WebElement findElement(String locatorType, String locatorValue) {
        By by = getLocatorType(locatorType, locatorValue);
        return driver.findElement(by);
    }

    public List<WebElement> findElements(String locatorType, String locatorValue) {
        By by = getLocatorType(locatorType, locatorValue);
        return driver.findElements(by);
    }

    public boolean isPresent(String locatorType, String locatorValue) {
        By by = getLocatorType(locatorType, locatorValue);
        try {
            driver.findElement(by);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public By getLocatorType(String locatorType, String locatorValue) {
        locatorType = locatorType.toUpperCase();
        By by = null;
        switch (locatorType) {
            case "ID":
                by = By.id(locatorValue);
                break;
            case "CLASS":
                by = By.className(locatorValue);
                break;
            case "XPATH":
                by = By.xpath(locatorValue);
                break;
            case "LINK":
                by = By.linkText(locatorValue);
                break;
            case "NAME":
                by = By.name(locatorValue);
                break;
            case "TAG":
                by = By.tagName(locatorValue);
                break;
            case "CSS":
                by = By.cssSelector(locatorValue);
                break;
            case "PARTIAL":
                by = By.partialLinkText(locatorValue);
                break;
            default:
                return null;
        }
        return by;
    }

    public WebElement findElementById(String locatorValue) {
        return findElement("id", locatorValue);
    }

    public WebElement findElementByName(String locatorValue) {
        return findElement("name", locatorValue);
    }

    public WebElement findElementByXpath(String locatorValue) {
        return findElement("xpath", locatorValue);
    }

    public WebElement findElementByClass(String locatorValue) {
        return findElement("class", locatorValue);
    }

    public WebElement findElementByLink(String locatorValue) {
        return findElement("link", locatorValue);
    }

    public List<WebElement> findElementsById(String locatorValue) {
        return findElements("id", locatorValue);
    }

    public List<WebElement> findElementsByName(String locatorValue) {
        return findElements("name", locatorValue);
    }

    public List<WebElement> findElementsByXpath(String locatorValue) {
        return findElements("xpath", locatorValue);
    }

    public List<WebElement> findElementsByClass(String locatorValue) {
        return findElements("class", locatorValue);
    }

    public List<WebElement> findElementsByLink(String locatorValue) {
        return findElements("link", locatorValue);
    }

    public void waitForElementToBeClickable(String elementName) {
        waitForElementToBeClickable(findElement(elementName));
    }

    public void waitForElementToBeClickable(String locatorType, String locatorValue) {
        waitForElementToBeClickable(findElement(locatorType, locatorValue));
    }

    public void waitForVisibilityOfTheElement(String elementName) {
        waitForVisibilityOfTheElement(findElement(elementName));
    }

    public void waitForVisibilityOfTheElement(String locatorType, String locatorValue) {
        waitForVisibilityOfTheElement(findElement(locatorType, locatorValue));
    }

    public void waitForElementToBeClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 10L);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForVisibilityOfTheElement(WebElement element) {
        waitForElement();
        WebDriverWait wait = new WebDriverWait(driver, 10L);
        wait.until(ExpectedConditions.visibilityOf(element));
    }


    public void click(String elementName) {
        WebElement element = findElement(elementName);
        click(elementName);
    }


    public void click(WebElement element) {
        waitForVisibilityOfTheElement(element);
        waitForElementToBeClickable(element);
        String tagName = element.getTagName();
        element.click();
        LOGGER.info("Click on element " + tagName);

    }

    public void click(String locatorType, String locatorValue) {
        WebElement element = findElement(locatorType, locatorValue);
        click(element);
    }

    public void sendKeys(WebElement element, String value) {
        waitForVisibilityOfTheElement(element);
        clear(element);
        element.sendKeys(value);
        LOGGER.info("Enter the text \"" + value + "\" on WebElement " + element.getTagName());
    }

    public void sendKeys(WebElement element, Keys keys) {
        waitForVisibilityOfTheElement(element);
        element.sendKeys(keys);
        LOGGER.info("Enter the text \"" + keys.name() + "\" on WebElement " + element.getTagName());
    }

    public void sendKeys(String locatorType, String locatorValue, String value) {
        WebElement element = findElement(locatorType, locatorValue);
        sendKeys(element, value);
    }

    public void sendKeys(String elementName, String value) {
        WebElement element = findElement(elementName);
        sendKeys(element, value);
    }

    public String getText(WebElement element) {
        waitForVisibilityOfTheElement(element);
        return element.getAttribute("textContent");
    }

    public String getText(String locatorType, String locatorValue) {
        WebElement element = findElement(locatorType, locatorValue);
        return getText(element);
    }

    public String getText(String elementName) {
        WebElement element = findElement(elementName);
        return getText(element);
    }

    public String getAttribute(WebElement element, String attributeValue) {
        waitForVisibilityOfTheElement(element);
        return element.getAttribute(attributeValue);
    }

    public String getAttribute(String locatorType, String locatorValue, String attributeValue) {
        WebElement element = findElement(locatorType, locatorValue);
        return getAttribute(element, attributeValue);
    }

    public String getAttribute(String elementName, String attributeValue) {
        WebElement element = findElement(elementName);
        return getAttribute(element, attributeValue);

    }

    public void clear(WebElement element) {
        waitForVisibilityOfTheElement(element);
        element.clear();
    }

    public void clear(String locatorType, String locatorValue) {
        WebElement element = findElement(locatorType, locatorValue);
        clear(element);
    }

    public void clear(String elementName) {
        WebElement element = findElement(elementName);
        clear(element);
    }

    public boolean isSelected(WebElement element) {
        waitForVisibilityOfTheElement(element);
        return element.isSelected();
    }

    public boolean isSelected(String locatorType, String locatorValue) {
        WebElement element = findElement(locatorType, locatorValue);
        return isSelected(element);
    }

    public boolean isSelected(String elementName) {
        WebElement element = findElement(elementName);
        return isSelected(element);
    }

    public boolean isEnabled(WebElement element) {
        waitForVisibilityOfTheElement(element);
        return element.isEnabled();
    }

    public boolean isEnabled(String locatorType, String locatorValue) {
        WebElement element = findElement(locatorType, locatorValue);
        return isEnabled(element);
    }

    public boolean isEnabled(String elementName) {
        WebElement element = findElement(elementName);
        return isEnabled(element);
    }

    public boolean isDisplayed(WebElement element) {
        waitForVisibilityOfTheElement(element);
        return element.isDisplayed();
    }

    public boolean isDisplayed(String locatorType, String locatorValue) {
        WebElement element = findElement(locatorType, locatorValue);
        return isDisplayed(element);
    }

    public boolean isDisplayed(String elementName) {
        WebElement element = findElement(elementName);
        return isDisplayed(element);
    }

    public void selectDropdownByText(WebElement element, String text) {
        waitForVisibilityOfTheElement(element);
        Select select = new Select(element);
        select.selectByVisibleText(text);
    }

    public void selectDropdownByText(String locatorType, String locatorValue, String text) {
        WebElement element = findElement(locatorType, locatorValue);
        selectDropdownByText(element, text);
    }

    public void selectDropdownByValue(WebElement element, String value) {
        waitForVisibilityOfTheElement(element);
        Select select = new Select(element);
        select.selectByValue(value);
    }

    public void selectDropdownByValue(String locatorType, String locatorValue, String value) {
        WebElement element = findElement(locatorType, locatorValue);
        selectDropdownByValue(element, value);
    }

    public void selectDropdownByIndex(WebElement element, int index) {
        waitForVisibilityOfTheElement(element);
        Select select = new Select(element);
        select.selectByIndex(index);
    }

    public void selectDropdownByValue(String locatorType, String locatorValue, int index) {
        WebElement element = findElement(locatorType, locatorValue);
        selectDropdownByIndex(element, index);
    }

    public List<String> getTextOfFindElements(List<WebElement> elementList) {
        List<String> elementValues = new ArrayList<>();
        if (elementList != null && elementList.size() > 0) {
            elementList.forEach(item -> elementValues.add(item.getAttribute("textContent")));
        }
        return elementValues;
    }

    public List<WebElement> findChildElements(WebElement element, String locatorType, String locatorValue) {
        By by = getLocatorType(locatorType, locatorValue);
        return element.findElements(by);
    }

    public List<WebElement> findChildElements(WebElement element, String xpathValue) {
        By by = getLocatorType("xpath", xpathValue);
        return element.findElements(by);
    }

    public WebElement findChildElement(WebElement element, String xpathValue) {
        By by = getLocatorType("xpath", xpathValue);
        return element.findElement(by);
    }

    public void switchToActiveElementAndSendKeys(Keys keys) {
        driver.switchTo().activeElement().sendKeys(keys);
    }

    public void switchToActiveElementAndPressTab(Keys keys) {
        switchToActiveElementAndSendKeys(Keys.TAB);
    }

    public void pressTabToElement(WebElement element) {
        sendKeys(element, Keys.TAB);
    }

    public void clickUsingJavaScript(WebElement element) {
        waitForElement();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String tagName = element.getTagName();
        js.executeScript("arguments[0].click();", element);
        LOGGER.info("Click using javascript on element " + tagName);
    }

    public void clickUsingJavaScript(String locatorType, String locatorValue) {
        waitForElement();
        WebElement element = findElement(locatorType, locatorValue);
        clickUsingJavaScript(element);
    }

    public String getTextUsingJavascript(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return js.executeScript("return arguments[0].innerHTML", element).toString();
    }

    public void executeScript(String script, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(script, element);
    }

    public void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    public void scrollToElement(String locatorType, String locatorValue) {
        WebElement element = findElement(locatorType, locatorValue);
        scrollToElement(element);
    }

    public void scrollToElement(String elementName) {
        WebElement element = findElement(elementName);
        scrollToElement(elementName);
    }

    public void scrollToTop() {
        ((JavascriptExecutor) driver).executeScript("scroll(0,0);");
    }

    public void verifyData(boolean expectedValue, String typeOfElement) {
        Assert.assertTrue(typeOfElement + " not displayed", expectedValue);
        logMessage(typeOfElement + " expected value is Displayed");
//        takeScreenshot(typeOfElement);
    }

    public void verifyData(String actualValue, String expectedValue, String typeOfElement) {
        Assert.assertEquals(typeOfElement + " element expected Value " + expectedValue + " not equals to actual value " + actualValue, expectedValue, actualValue);
        logMessage(typeOfElement + " element expected Value " + expectedValue + " equals to actual Value " + actualValue);
//        takeScreenshot(typeOfElement);
    }

    public void verifyDataContains(String actualValue, String expectedValue, String typeOfElement) {
        Assert.assertTrue(typeOfElement + " element actual Value " + actualValue + " not contains expected value " + expectedValue, actualValue.contains(expectedValue));
        logMessage(typeOfElement + " element actual Value " + actualValue + " contains expected Value " + expectedValue);
//        takeScreenshot(typeOfElement);
    }

    public void verifyDataListIgnoreOrder(List<String> actualList, List<String> expectedList, String typeOfElement) {
        Collections.sort(actualList);
        Collections.sort(expectedList);
        Assert.assertTrue(typeOfElement + " element expected Value " + expectedList + " not equals to actual value " + actualList, actualList.equals(expectedList));
    }

    private void waitForElement() {
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
        }
    }

    private void waitForElement(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
        }
    }

    public void setURL(String url) {
        driver.get(url);
    }

    public void quit() {
        driver.quit();
        logMessage("Browser Closed Successfully.");
    }

    public void takeScreenshot(Scenario scenario) {
        final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        scenario.embed(screenshot, "image/png");
    }

    public void takeScreenshot(String fileNameText) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String folderName = dateFormat.format(date);
        String directory = "C:/Dev/AutomationResults/" + folderName;
        File file = new File(directory);
        if (!file.exists()) {
            if (file.mkdir()) {
                System.out.println("Directory is created!");
            } else {
                System.out.println("Failed to create directory!");
            }
        }
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
            date = new Date();
            String timeStamp = dateFormat.format(date);
            fileNameText = fileNameText.replace(" ", "");
            if (fileNameText.length() >= 30) {
                fileNameText = fileNameText.substring(0, 30);
            }
            fileNameText += timeStamp + ".png";
            FileUtils.copyFile(src, new File(directory + "/" + fileNameText));
            LOGGER.info("Screenshot Verification File Name - " + fileNameText);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void moveToElement(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.perform();
    }

    public String getCurrentDateTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:m:ss");
        Date date = new Date();
        return formatter.format(date);
    }

    private void logMessage(String message) {
        LOGGER.info(message);
    }

    public void logError(String message, Exception exception) {
        LOGGER.error(message, exception);
    }

    public void moveFile(String sourcePath, String targetPath, String fileName) throws IOException {
        Path temp = Files.move
                (Paths.get(sourcePath + File.separator + fileName),
                        Paths.get(targetPath + File.separator + fileName));

        if (temp != null)
            logMessage("File " + fileName + " renamed and moved to " + targetPath + " successfully");
        else {
            logMessage("Error: File " + fileName + " not renamed and moved to " + targetPath + " successfully");
        }
    }

    public String getLatestDownloadedFileName(String folderPath){
        String fileName = "";
        try{
            Path dir = Paths.get(folderPath);

            Optional<File> mostRecentFileOrFolder =
                    Arrays.stream(dir.toFile().listFiles())
                    .max((f1,f2) -> Long.compare(f1.lastModified(),
                            f2.lastModified()));

            if(mostRecentFileOrFolder.isPresent()){
                File mostRecent = mostRecentFileOrFolder.get();
                System.out.println("Most Recent File downloaded is " + mostRecent.getPath());
                fileName = mostRecent.getName();
            } else {
                logMessage("Error: No latest downloaded file found in path " + folderPath);
            }
        } catch (Exception e){
            logMessage("Error: No latest downloaded file found in path " + folderPath);
        }
        return fileName;
    }


}
