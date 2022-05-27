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
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class Activity5 {
	AndroidDriver<MobileElement> driver;
	WebDriverWait wait;

	@BeforeClass
	public void beforeClass() throws MalformedURLException {
		// Set the Desired Capabilities
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("deviceName", "RZ8N61709YN");
		caps.setCapability("platformName", "Android");
		caps.setCapability("automationName", "UiAutomator2");
		caps.setCapability("appPackage", "com.samsung.android.messaging");
		caps.setCapability("appActivity", "com.android.Conversation");
		caps.setCapability("noReset", true);

		// Instantiate Appium Driver
		URL appServer = new URL("http://0.0.0.0:4723/wd/hub");
		driver = new AndroidDriver<MobileElement>(appServer, caps);
		wait = new WebDriverWait(driver, 10);
	}

	@Test
	public void smsTest() {
		// Wait for elements to load
		wait.until(ExpectedConditions
				.elementToBeClickable(MobileBy.AndroidUIAutomator("description(\"Start new conversation\")")));
		// Locate the button to write a new message and click it
		driver.findElement(MobileBy.AndroidUIAutomator("description(\"Start new conversation\")")).click();

		// Enter the number to send message to
		String contactBoxLocator = "resourceId(\"com.samsung.android.messaging:id/recipients_editor_to\")";

		// Enter your own phone number
		wait.until(ExpectedConditions.elementToBeClickable(MobileBy.AndroidUIAutomator(contactBoxLocator)));
		driver.findElement(MobileBy.AndroidUIAutomator(contactBoxLocator)).sendKeys("9014842907");
		((AndroidDriver<MobileElement>) driver).pressKey(new KeyEvent(AndroidKey.ENTER));

		// Wait for message box to load
		wait.until(ExpectedConditions.elementToBeClickable(
				MobileBy.AndroidUIAutomator("resourceId(\"com.samsung.android.messaging:id/message_edit_text\")")));

		// Type in a message
		driver.findElement(
				MobileBy.AndroidUIAutomator("resourceId(\"com.samsung.android.messaging:id/message_edit_text\")"))
				.sendKeys("Hello from Appium");

		// Send the message
		driver.findElement(MobileBy.AndroidUIAutomator("description(\"Send\")")).click();

		// Wait for message to show
		String messageLocator = "resourceId(\"com.samsung.android.messaging:id/message_text\")";
		wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AndroidUIAutomator(messageLocator)));

		// Assertion
		String sentMessageText = driver.findElement(MobileBy.AndroidUIAutomator(messageLocator)).getText();
		Assert.assertEquals(sentMessageText, "Hello from Appium");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
