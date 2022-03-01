package framework.stepdefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import framework.dataProvider.ConfigProvider;

public class LoginStepDefinition extends AbstractSteps {
    @Given("^User is on Homepage$")
    public void User_is_on_Homepage() throws Exception {
        getDriver().get(ConfigProvider.getAsString("ApplicationUrl"));
        pageObjectManager.getHomePage().User_is_on_Homepage();

    }

    @Given("^User is on Movie page$")
    public void User_is_on_MoviePage() throws Exception {
        getDriver().get(ConfigProvider.getAsString("ApplicationUrl"));
    }


    @When("^User Clicks On Login$")
    public void user_Clicks_On_Login() {
        pageObjectManager.getHomePage().user_Clicks_On_Login();
    }

    @Then("^Verify The Login Page$")
    public void verify_The_Login_Page() {
        pageObjectManager.getHomePage().verify_The_Login_Page();
    }

    @When("^User Enters Username as (.*) and Password as (.*)$")
    public void userEntersUsernameAsUsernameAndPasswordAsPassword(String Username, String Password) throws Exception {
        pageObjectManager.getHomePage().userEntersUsernameAsUsernameAndPasswordAsPassword(DefaultStepDefinition.getCellData(Username), DefaultStepDefinition.getCellData(Password));
    }

    @Then("^User Snaps a Screenshot$")
    public void userSnapsAScreenshot() throws Exception {
        pageObjectManager.getHomePage().userSnapsAScreenshot();
    }

    @Then("^Check If Tickets Are Available For (.*)$")
    public void checkForTickets(String theatre) throws Exception {
        pageObjectManager.getHomePage().checkForTickets(theatre);
    }
}
