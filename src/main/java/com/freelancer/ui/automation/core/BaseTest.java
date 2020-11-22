package com.freelancer.ui.automation.core;

import com.freelancer.ui.automation.pages.Constants;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.HashMap;

/*
 * Created by Pratham on 21/11/2020
 * <p>
 * This class will open browser and navigate to URL
 */

public class BaseTest {

    protected BasePage basePage;
    public WebDriver driver;
    private String browser;

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseTest.class);

    protected void initBaseTest(BasePage basePage) {
        this.basePage = basePage;
    }

    /*
     * Open browser as per parameter
     *
     *  @return webDriver
     */

    public WebDriver getDriver() {
        if (driver == null) {
            if ("IE".equalsIgnoreCase(browser)) {
                driver = getIEDriver();
            } else if ("Chrome".equalsIgnoreCase(browser)) {
                driver = getChromeDriver();
            } else if ("Mozilla".equalsIgnoreCase(browser)) {
                driver = getMozillaDriver();
            } else if ("RemoteChromeHeadless".equalsIgnoreCase(browser)) {
                driver = getRemoteChromeHeadlessDriver();
            } else if ("RemoteChrome".equalsIgnoreCase(browser)) {
                driver = getRemoteChromeDriver();
            } else {
                driver = getHTMLUnitDriver();
            }
        }
        return driver;
    }

    /*
     * @return HtmlUnitDriver
     */

    private WebDriver getHTMLUnitDriver() {
        driver = new HtmlUnitDriver(BrowserVersion.INTERNET_EXPLORER_8);
        if (driver != null) {
            LOGGER.info("HtmlUnitDriver Firefox browser launched");
        }
        return driver;

    }

    private WebDriver getRemoteChromeDriver() {
        return driver;

    }

    private WebDriver getRemoteChromeHeadlessDriver() {
        return driver;

    }

    private WebDriver getMozillaDriver() {
        driver = new FirefoxDriver();
        if (driver != null) {
            LOGGER.info("Mozilla Firefox browser launched");
        }
        return driver;
    }

    /*
     * @return ChromeDriver
     */

    private WebDriver getChromeDriver() {
//        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "selenium" + File.separator + "automation" + File.separator + "drivers" + File.separator + "chromedriver.exe" );
//        System.setProperty("webdriver.chrome.driver", "src" + File.separator + "main" + File.separator + "resources" + File.separator + "selenium" + File.separator + "automation" + File.separator + "drivers" + File.separator + "chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/main/resources/selenium/automation/drivers/chromedriver.exe");
//        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
//        HashMap<String, Object> chromePrefs = new HashMap();
//        chromePrefs.put("profile.default_content_settings.popups", 0);
//        chromePrefs.put("download.default_directory", System.getProperty("user.dir") + Constants.FREELANCER_LOCAL_PATH);
//        ChromeOptions options = new ChromeOptions();
//        options.setExperimentalOption("prefs", chromePrefs);
//        options.addArguments("--start-maximized");
//        options.setExperimentalOption("useAutomationExtension", false);
//        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
//        driver = new ChromeDriver(capabilities);
        driver = new ChromeDriver();

        if (driver != null) {
            LOGGER.info("Chrome browser launched");
        }
        return driver;

//tobe removed
//        driver = new ChromeDriver(options);
//        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
//        driver.manage().window().maximize();

    }

    private WebDriver getIEDriver() {
        return driver;

    }

    /*
     * @param browser name of the browser
     * @return
     */
    protected WebDriver launchBrowser(String browser) {
        this.browser = browser;
        if(driver == null){
            getDriver();
        }
        return driver;
    }

    /*
     *@return title of the homepage
     */
    protected String getTitle() { return driver.getTitle(); }

    protected void launchApplication(String url){
        driver.get(url);
        LOGGER.info("Application URL launched Successfully :: {}" , url);
    }


}