package framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import framework.utilities.AssertionLibrary;
import framework.utilities.BasePageObject;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class HomePage extends BasePageObject {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    private String home = "//a[contains(text(),'bbcare@gtplkcbpl.com')]";
    private String loginButton = "//input[@id='save']";
    private String usernameField = "//input[@id='txtUserName']";
    private String passwordField = "//input[@id='txtPassword']";

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
        setInputvalue(usernameField, userName);
        setInputvalue(passwordField, passWord);
    }

    public void userSnapsAScreenshot() throws Exception {
        TakeScreenshot();
    }

    public void checkForTickets(String theatre) {
        List<WebElement> a = driver.findElements(By.xpath("//a[@class='__venue-name']"));
        try {
            Optional<String> name = Optional.ofNullable(a.stream().filter(w -> w.getText().contains(theatre)).findFirst().get().getText());
            System.out.println("Tickets Available For "+name.get());
        }
        catch (NoSuchElementException e)
        {
            System.out.println("No Tickets Available For "+theatre);
        }

    }
}
