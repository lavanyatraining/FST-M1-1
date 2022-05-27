package activities;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class Activity4 {
	AndroidDriver<MobileElement> driver;
	WebDriverWait wait;

	@BeforeClass
	public void beforeClass() throws MalformedURLException {
		// Set the Desired Capabilities
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("deviceName", "RZ8N61709YN");
		caps.setCapability("platformName", "Android");
		caps.setCapability("automationName", "UiAutomator2");
		caps.setCapability("appPackage", "com.samsung.android.dialer");
		caps.setCapability("appActivity", ".DialtactsActivity");
		caps.setCapability("noReset", true);

		// Instantiate Appium Driver
		URL appServer = new URL("http://0.0.0.0:4723/wd/hub");
		driver = new AndroidDriver<MobileElement>(appServer, caps);
		wait = new WebDriverWait(driver, 5);
	}

	@Test
	public void addContact() {
		// Wait for app to load
		wait.until(ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId("Create new contact")));

		// Click on add new contact floating button
		driver.findElementByAccessibilityId("Create new contact").click();

		// Wait for fields to load
		wait.until(ExpectedConditions.elementToBeClickable(MobileBy.id("nameEdit")));

		// Find the first name, last name, and phone number fields
		MobileElement firstName = driver
				.findElementByXPath("//android.widget.Button[@content-desc='Show detailed name fields']");
		MobileElement phoneNumber = driver.findElementByXPath("//android.widget.Button[@content-desc='Phone']");

		// Enter the text in the fields
		firstName.sendKeys("Lavanya");
		phoneNumber.sendKeys("9991284789");

		// Save the contact
		driver.findElementById("smallLabel").click();

		// Wait for contact card to appear
		wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.id("Edit contact")));

		// Assertion
		MobileElement mobileCard = driver.findElementById("communication_card");
		Assert.assertTrue(mobileCard.isDisplayed());

		String contactName = driver.findElementById("communication_card").getText();
		Assert.assertEquals(contactName, "Lavanya");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
