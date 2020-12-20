package framework.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import framework.utilities.AssertionLibrary;
import framework.utilities.BasePageObject;
import io.github.bonigarcia.wdm.WebDriverManager;

public class HomePage extends BasePageObject {
	public HomePage(WebDriver driver) {
		super(driver);
	}
	private String products="//a[contains(text(),'Products')]";
	private String login = "//button[contains(text(),'Login')]";
	private String loginbutton="//input[@id='Login']";
	private String usernamefield="//input[@id='username']";
	private String passwordfield="//input[@id='password']";

	public void User_is_on_Homepage() {
		AssertionLibrary.assertTrue(isDisplayed(products), "Homepage Verification");
	}

	public void user_Clicks_On_Login() {
		Ele_presence_Wait(login);
		clickElement(login);
		
	}

	public void verify_The_Login_Page() {
		Ele_visibility_Wait(loginbutton);
		AssertionLibrary.assertTrue(isDisplayed(loginbutton), "Login Page Verification");
	}

    public void userEntersUsernameAsUsernameAndPasswordAsPassword(String userName, String passWord) {
		setInputvalue(usernamefield,userName);
		setInputvalue(passwordfield,passWord);
    }

	public void userSnapsAScreenshot() throws Exception {
		TakeScreenshot();
	}
}
