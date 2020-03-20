package framework.stepdefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import framework.dataProvider.ConfigProvider;

public class LoginStepDefinition extends AbstractSteps {
	@Given("^User is on Homepage$")
	public void User_is_on_Homepage() {
		startDriver();
		getDriver().get(ConfigProvider.getAsString("ApplicationUrl"));
		pageObjectManager.getHomePage().User_is_on_Homepage();
		
	}
	@When("^User Clicks On Login$")
	public void user_Clicks_On_Login() throws Throwable {
		pageObjectManager.getHomePage().user_Clicks_On_Login();
	}
	@Then("^Verify The Login Page$")
	public void verify_The_Login_Page() throws Throwable {
		pageObjectManager.getHomePage().verify_The_Login_Page();
		stopDriver();
	}
}
