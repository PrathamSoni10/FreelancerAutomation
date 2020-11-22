package com.freelancer.ui.automation.core;

import com.freelancer.ui.automation.utils.DriverUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BasePage extends ActionDriver {

    protected WebDriver driver;
    protected DriverUtils driverUtil;

    private static final Logger LOGGER = LoggerFactory.getLogger(BasePage.class);

    public BasePage(WebDriver driver) {
        super(driver);
        driverUtil = new DriverUtils(driver);
    }
}
