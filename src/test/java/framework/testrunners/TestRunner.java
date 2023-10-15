package framework.testrunners;

import cucumber.api.CucumberOptions;
import framework.stepdefinitions.AbstractSteps;
import framework.utilities.AbstractCucumberTestNG;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"framework.stepdefinitions"},
        plugin = {"pretty", "json:BDD_REPORTS/cucumber.json","com.cucumber.listener.ExtentCucumberFormatter:BDD_REPORTS/BDD-REPORT.html"},
        tags = {"@UI1"},
        dryRun = false, monochrome = true)

public class TestRunner extends AbstractCucumberTestNG {
    @BeforeMethod
    public void driverStart() throws Exception {
        AbstractSteps.startDriver();
    }

    @AfterMethod
    public void driverTeardown() {
        AbstractSteps.stopDriver();
    }

    @AfterClass
    void terminate() {
        AbstractSteps.terminateDriver();
    }
}