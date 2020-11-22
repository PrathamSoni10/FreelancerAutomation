package cukes;

import com.github.mkolisnyk.cucumber.runner.ExtendedCucumber;
import com.github.mkolisnyk.cucumber.runner.ExtendedCucumberOptions;
import cucumber.api.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(ExtendedCucumber.class)
@ExtendedCucumberOptions(jsonReport = "target/test_results/FREELANCER/cucumber.json",
        retryCount = 0,
        overviewReport = true,
        reportPrefix = "FREELANCER_TestExecution_Result",
        outputFolder = "target/test_results")
@CucumberOptions(plugin = {"html:target/test_results/FREELANCER/cucumber-html-report",
        "json:target/test_results/FREELANCER/cucumber.json", "pretty:target/" +
        "test_results/FREELANCER/cucumber-pretty.txt",
        "usage:target/test_results/FREELANCER/cucumber-usage.json", "junit:target/test_results/FREELANCER/cucumber-results.xml"},
        features = {
//                "src/test/resources/featurefiles/FREELANCER/SignUpPage.feature",
                "src/test/resources/featurefiles/FREELANCER/LoginPage.feature",
        },
        glue = {"steps/freelancer/"}
)

public class FreeLancerCuke {
}
