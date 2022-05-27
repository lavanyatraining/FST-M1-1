package appiumProject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class GoogleKeepReminder {
	AppiumDriver<MobileElement> driver = null;

	@BeforeMethod
	public void beforeMethod() throws MalformedURLException {
		// Set the Desired Capabilities
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("deviceName", "RZ8N61709YN");
		caps.setCapability("platformName", "Android");
		caps.setCapability("automationName", "UiAutomator2");
		caps.setCapability("appPackage", "com.google.android.keep");
		caps.setCapability("appActivity", ".activities.BrowseActivity");
		caps.setCapability("noReset", true);

		// Instantiate Appium Driver
		URL appServer = new URL("http://0.0.0.0:4723/wd/hub");
		driver = new AndroidDriver<MobileElement>(appServer, caps);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void createNoteWithReminder() {
		// create new note with reminder
		driver.findElementByAccessibilityId("New text note").click();
		driver.findElementById("editable_title").click();
		driver.findElementById("editable_title").sendKeys("New note with reminder for testing");
		driver.findElementById("edit_note_text").click();
		driver.findElementById("edit_note_text").sendKeys("New Description");
		driver.findElementByAccessibilityId("Single-column view").click();
		driver.findElementById("save").click();
		driver.findElementByAccessibilityId("Open navigation drawer").click();
		boolean isReminderAdded = driver.findElementById("reminder_chip_text").isDisplayed();
		if (isReminderAdded) {
			System.out.println("note added with a reminder");
		} else {
			System.out.println("note not added");
		}
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}
}
