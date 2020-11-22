package steps.freelancer;

import com.freelancer.ui.automation.core.ActionDriver;
import com.freelancer.ui.automation.core.FreeLancerBasePage;
import com.freelancer.ui.automation.core.FreeLancerBaseTest;
import com.freelancer.ui.automation.pages.FreeLancerException;
import com.freelancer.ui.automation.pages.SignUpPage;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class FreeLancerStepDefs {

    protected FreeLancerBasePage freeLancerBasePage;
    protected FreeLancerBaseTest freeLancerBaseTest;
    protected static ActionDriver actionDriver;

    public static String parameterString;
    public static List<String> parameterList = new ArrayList<>();
    public static HashMap<String, String> parameters = new HashMap<>();
    public static HashMap<String, HashMap<String, String>> parameters1 = new HashMap<>();
    public static HashMap<String, HashMap<String, HashMap<String, String>>> parameters2 = new HashMap<>();

    private static final Logger LOGGER = LoggerFactory.getLogger(FreeLancerStepDefs.class);

    public FreeLancerStepDefs() {
        freeLancerBaseTest = new FreeLancerBaseTest();
    }

    @Before
    public void beforeScenario(Scenario scenario) {
        String scenarioName = scenario.getName();
        String featureName = scenario.getId();
        LOGGER.info("\n");
        LOGGER.info("----------------------------------------------------BEGIN SCENARIO----------------------------------------------------");
        LOGGER.info("Starting Time :: {}", getDate());
        LOGGER.info("FEATURE :: {}", featureName.split(":")[0]);
        LOGGER.info("SCENARIO NAME :: {}", scenarioName);
    }

    @After
    public void afterScenario(Scenario scenario) {
        if (actionDriver != null) {
            if (scenario.isFailed()) {
                if (freeLancerBaseTest.driver != null)
                    actionDriver.takeScreenshot(scenario);
            }
        }
        LOGGER.info("SCENARIO STATUS :: {}", scenario.getStatus());
        LOGGER.info("End Time :: {}", getDate());
        LOGGER.info("----------------------------------------------------SCENARIO ENDS----------------------------------------------------");
    }

    public String getDate() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(calendar.getTime());
    }


    @Given("^SignUp Into FREELANCER Application$")
    public void signUpIntoFreelancerApplication() throws Throwable {
        freeLancerBasePage.signUpPage().clickOnSignUpLink();
        freeLancerBasePage.signUpPage().performSignUp();

    }

    @Then("^Verify Join Freelancer Logo Is Displayed$")
    public void verifyJoinFreelancerLogoIsDisplayed() throws Throwable {
//        freeLancerBasePage.signUpPage().ge

    }

    @Then("^Verify Select Account Type Is Displayed$")
    public void verifySelectAccountTypeTextIsDisplayed() throws Throwable {
//        freeLancerBasePage.signUpPage().ge

    }

    @Then("^Verify Sign Up Text Is Displayed In Sign Up Page$")
    public void verifySignUpTextIsDisplayedInSignUpPage() throws Throwable {


    }


    @Given("^Login Into FREELANCER Application$")
    public void loginIntoFreelancerApplication() throws Throwable {
        boolean status = false;
        int retries = 0;
        while (retries < 3) {
            try {
                freeLancerBaseTest.launchApplication();
                setObjects();
                resetVariables();
                FreeLancerBasePage.loginPage.login(freeLancerBaseTest.userId, freeLancerBaseTest.password);
                FreeLancerBasePage.homePage.verifyDashBoardLinkIsDisplayed();
                status = true;
                break;
            } catch (Exception e) {
                LOGGER.info("Login Failed. Retrying - " + retries + " times");
                LOGGER.info("Login Failed. ERROR" + e);
                if (actionDriver != null) {
                    actionDriver.quit();
                    freeLancerBaseTest.driver = null;
                }
                retries++;
            }
        }
        if (!status) {
            LOGGER.info("ERROR: Not able to login in FREELANCER Application");
            throw new FreeLancerException("ERROR: Not able to login in FREELANCER Application");
        }
    }

    @Then("^Verify Dashboard Button Is Displayed In Home Page$")
    public void verifyDashboardButtonIsDisplayedInHomePage() throws Throwable{
//    FreeLancerBasePage.homePage.verifyDashBoardLinkIsDisplayed();
        freeLancerBasePage.homePage.verifyDashBoardLinkIsDisplayed();

    }


    @Given("^Close The FREELANCER Application$")
    public void closeTheFreelancerApplication() throws Throwable{
        resetVariables();
        actionDriver.quit();
    }


    public void setObjects() {
        freeLancerBasePage = freeLancerBaseTest.getFreeLancerBasePage();
        actionDriver = freeLancerBaseTest.getActionDriver();
        freeLancerBasePage.setObjects();
    }

    public void resetVariables() {

    }
}
