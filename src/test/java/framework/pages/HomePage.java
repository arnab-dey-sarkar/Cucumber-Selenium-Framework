package framework.pages;

import framework.utilities.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class HomePage extends BasePageObject {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    private final String home = "//h5[contains(text(),'Login your Account')]";
    private final String loginButton = "//button[@type='LOGIN']";
    private final String usernameField = "//input[@placeholder='Username']";
    private final String passwordField = "//input[@placeholder='Password']";

    public void User_is_on_Homepage() throws InterruptedException {

        ele_Visibility_Wait(loginButton);
        Assert.assertTrue(isDisplayed(loginButton), "Homepage Verification");
    }

    public void user_Clicks_On_Login() {
        ele_Presence_Wait(loginButton);
        clickElement(loginButton);

    }

    public void verify_The_Login_Page() {
        ele_Visibility_Wait(loginButton);
        Assert.assertTrue(isDisplayed(loginButton), "Login Page Verification");
    }

    public void userEntersUsernameAsUsernameAndPasswordAsPassword(String userName, String passWord) {
        setInputvalue(usernameField, userName);
        setInputvalue(passwordField, passWord);
    }

    public void userSnapsAScreenshot() throws Exception {
        takeScreenshot();
    }

    public void checkForTickets(String theatre) {
        List<WebElement> a = driver.findElements(By.xpath("//a[@class='__venue-name']"));
        try {
            Optional<String> name = Optional.ofNullable(a.stream().filter(w -> w.getText().contains(theatre)).findFirst().get().getText());
            name.ifPresent(s -> System.out.println("Tickets Available For " + s));
        }
        catch (NoSuchElementException e)
        {
            System.out.println("No Tickets Available For "+theatre);
        }

    }
}
