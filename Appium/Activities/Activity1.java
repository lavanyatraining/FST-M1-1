package activities;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class Activity1 {
	// Declare Android driver
	AndroidDriver<MobileElement> driver;

	@BeforeClass
	public void setUp() throws MalformedURLException {
		// Set the Desired Capabilities
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("deviceName", "RZ8N61709YN");
		caps.setCapability("platformName", "Android");
		caps.setCapability("automationName", "UiAutomator2");
		caps.setCapability("appPackage", "com.sec.android.app.popupcalculator");
		caps.setCapability("appActivity", ".Calculator");
		caps.setCapability("noReset", true);

		// Instantiate Appium Driver
		URL appServer = new URL("http://127.0.0.1:4723/wd/hub");
		driver = new AndroidDriver<MobileElement>(appServer, caps);
	}

	@Test
	public void add() {
		// Using resource-id
		// driver.findElementById("digit_5").click();
		driver.findElementById("calc_keypad_btn_05").click();
		// Using Accessibility ID
		driver.findElementByAccessibilityId("Multiplication").click();
		// Using XPath
		// driver.findElementByXPath("//android.widget.Button[@text='9']").click();
		driver.findElementByXPath("//android.widget.Button[@content-desc='9']").click();
		// Perform Calculation
		driver.findElementById("calc_keypad_btn_equal").click();

		// Display Result
		String result = driver.findElement(MobileBy.id("calc_tv_result")).getText();
		System.out.println(result);

		// Assertion
		Assert.assertEquals(result, "45");
	}

	@AfterClass
	public void tearDown() {
		// Close app
		driver.quit();
	}
}
