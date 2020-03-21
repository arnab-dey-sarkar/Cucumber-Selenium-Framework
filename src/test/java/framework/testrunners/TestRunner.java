package framework.testrunners;

import cucumber.api.CucumberOptions;
import framework.utilities.AbstractCucumberTestNG;
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"framework.stepdefinitions"},
        plugin ={"com.cucumber.listener.ExtentCucumberFormatter:BDD_REPORTS/BDD-REPORT.html"},
        tags = {"@API"},
        dryRun = false,monochrome = true)

public class TestRunner extends AbstractCucumberTestNG{
   
}