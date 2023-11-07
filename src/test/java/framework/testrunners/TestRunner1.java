package framework.testrunners;

import cucumber.api.CucumberOptions;
import framework.stepdefinitions.AbstractSteps;
import framework.utilities.AbstractCucumberTestNG;
import org.testng.annotations.*;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"framework.stepdefinitions"},
        plugin ={"com.cucumber.listener.ExtentCucumberFormatter:BDD_REPORTS/BDD-REPORT.html"},
        tags = {"@UI"},
        dryRun = false,monochrome = true)

public class TestRunner1 extends AbstractCucumberTestNG{

}