package framework.pages;

import org.openqa.selenium.WebDriver;

import framework.utilities.AssertionLibrary;
import framework.utilities.BasePageObject;

public class HomePage extends BasePageObject {
	public HomePage(WebDriver driver) {
		super(driver);
	}
	private String home="//a[contains(text(),'bbcare@gtplkcbpl.com')]";
	private String loginButton ="//input[@id='save']";
	private String usernameField ="//input[@id='txtUserName']";
	private String passwordField="//input[@id='txtPassword']";

	public void User_is_on_Homepage() {
		AssertionLibrary.assertTrue(isDisplayed(home), "Homepage Verification");
	}

	public void user_Clicks_On_Login() {
		Ele_presence_Wait(loginButton);
		clickElement(loginButton);
		
	}

	public void verify_The_Login_Page() {
		Ele_visibility_Wait(loginButton);
		AssertionLibrary.assertTrue(isDisplayed(loginButton), "Login Page Verification");
	}

    public void userEntersUsernameAsUsernameAndPasswordAsPassword(String userName, String passWord) {
		setInputvalue(usernameField,userName);
		setInputvalue(passwordField,passWord);
    }

	public void userSnapsAScreenshot() throws Exception {
		TakeScreenshot();
	}
}
