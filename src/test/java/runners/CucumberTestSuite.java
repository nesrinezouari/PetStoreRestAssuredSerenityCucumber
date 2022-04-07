package runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;

import org.junit.runner.RunWith;


@RunWith(CucumberWithSerenity.class)

@CucumberOptions(glue = "features.steps", features = "src/test/resources/features",tags="@PostPetwithfiledata")
public class CucumberTestSuite {

}
