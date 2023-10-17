package framework.pageobjectmanager;

import org.openqa.selenium.WebDriver;

import framework.pages.HomePage;

public class PageObjectManager {

	private static WebDriver driver;

	private HomePage homePage;

	public PageObjectManager(WebDriver driver) {
		PageObjectManager.driver = driver;
	}

	public HomePage getHomePage() {
		return (homePage == null) ? homePage = new HomePage(driver) : homePage;
	}

}
