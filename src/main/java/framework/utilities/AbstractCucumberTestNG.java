package framework.utilities;


import com.aventstack.extentreports.ExtentReports;
import cucumber.api.Scenario;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AbstractCucumberTestNG {
    private TestNGCucumberRunner testNGCucumberRunner;
    protected Scenario scenario;
    static ExtentReports extentReports;

    @BeforeClass(alwaysRun = true)
    public void setUpClass() {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }

    @Test(groups = "cucumber", description = "Runs Cucumber Feature", dataProvider = "features")
    public void feature(CucumberFeatureWrapper cucumberFeature) {
        testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
    }

    @DataProvider(parallel = true)
    public Object[][] features() {
        return testNGCucumberRunner.provideFeatures();
    }

    @BeforeClass
    public void generateReport() {
        extentReports = new ExtentReports();
        extentReports.flush();
    }

    @AfterClass(alwaysRun = true)
    public static void writeExtentReport() {
        extentReports.setSystemInfo("User Name", System.getProperty("user.name"));
        extentReports.setSystemInfo("Time Zone", System.getProperty("user.timezone"));
        extentReports.setSystemInfo("OS", System.getProperty("os.name"));
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() {

        testNGCucumberRunner.finish();
    }


}
