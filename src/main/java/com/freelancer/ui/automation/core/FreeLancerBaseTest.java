package com.freelancer.ui.automation.core;

import com.freelancer.ui.automation.utils.CryptoConfig;
import com.freelancer.ui.automation.utils.FileOperations;

public class FreeLancerBaseTest extends BaseTest {

    protected FreeLancerBasePage freeLancerBasePage;
    protected ActionDriver actionDriver;
    private String url;
    private String browser;
    private FileOperations fileOps;
    public String userId;
    public String password;

    private String profile = System.getProperty("spring.profiles.active");
    //    private String filePath = "./config/" + profile + "/application.properties";
    private String filePath = "./config/qa/application.properties";

    public FreeLancerBaseTest() {
        fileOps = new FileOperations(filePath);
        browser = fileOps.getPropertyValue("freelancer.browser.name");
        url = fileOps.getPropertyValue("freelancer.url");
        userId = fileOps.getPropertyValue("freelancer.userid");
//        password = CryptoConfig.decryptFromHex(fileOps.getPropertyValue("freelancer.password"));
        password = fileOps.getPropertyValue("freelancer.password");

    }


    public void launchApplication() {
        setUp();
        launchApplication(url);
    }

    private void setUp() {
        driver = launchBrowser(browser);
        freeLancerBasePage = new FreeLancerBasePage(driver);
        actionDriver = new ActionDriver(driver);
        initBaseTest(freeLancerBasePage);
    }

    public FreeLancerBasePage getFreeLancerBasePage() { return this.freeLancerBasePage; }

    public ActionDriver getActionDriver() { return this.actionDriver; }

}
