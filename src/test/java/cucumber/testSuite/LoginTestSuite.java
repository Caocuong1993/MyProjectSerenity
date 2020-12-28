package cucumber.testSuite;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        plugin = {"pretty"},
        features = {"src/test/resources/features"},
        tags = {"@feature=Login"},
        glue = {"cucumber.stepDefinitions"}
        )
public class LoginTestSuite {
}

