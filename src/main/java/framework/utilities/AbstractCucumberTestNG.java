package framework.utilities;

import com.aventstack.extentreports.ExtentReports;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.cucumber.listener.Reporter;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;

public class AbstractCucumberTestNG {
	private TestNGCucumberRunner testNGCucumberRunner;
	protected Scenario scenario;

	@BeforeClass(alwaysRun = true)
	public void setUpClass() throws Exception {
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
	public void generateReport()
	{
		ExtentReports extentReports = new ExtentReports();
		extentReports.flush();
	}
	@AfterClass
	public static void writeExtentReport() {
		Reporter.loadXMLConfig("src/test/resources/properties/extent-config.xml");
	}

	@AfterClass(alwaysRun = true)
	public void tearDownClass() throws Exception {

		testNGCucumberRunner.finish();
	}


}
