package framework.testrunners;

import cucumber.api.CucumberOptions;
import framework.utilities.AbstractCucumberTestNG;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"framework.stepdefinitions"},
        plugin = {"pretty", "json:BDD_REPORTS/cucumber.json","com.cucumber.listener.ExtentCucumberFormatter:BDD_REPORTS/BDD-REPORT.html"},
        tags = {"@UI1"},
        dryRun = false, monochrome = true)

public class TestRunner extends AbstractCucumberTestNG {
    @DataProvider(parallel = true)
    public Object[][] features() {
        return super.features();
    }
}