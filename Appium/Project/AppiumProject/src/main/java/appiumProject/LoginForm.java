package appiumProject;

import static org.testng.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class LoginForm {
	AppiumDriver<MobileElement> driver = null;

	@BeforeMethod

	public void beforeMethod() throws MalformedURLException {
		// Set the Desired Capabilities
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("deviceName", "RZ8N61709YN");
		caps.setCapability("platformName", "Android");
		caps.setCapability("automationName", "UiAutomator2");
		caps.setCapability("appPackage", "com.android.chrome");
		caps.setCapability("appActivity", "com.google.android.apps.chrome.Main");
		caps.setCapability("noReset", true);

		// Instantiate Appium Driver
		URL appServer = new URL("http://0.0.0.0:4723/wd/hub");
		driver = new AndroidDriver<MobileElement>(appServer, caps);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void LoginForm() throws InterruptedException {
		// navigate to webpage
		driver.get("https://www.training-support.net/selenium");
		// scroll to the particular text
		driver.findElement(MobileBy.AndroidUIAutomator(
				"UiScrollable(UiSelector().scrollable(true).instance(0)).scrollIntoView(textStartsWith(\"Login Form\"))"));
		driver.findElement(MobileBy.xpath("//android.widget.TextView[@text='Login Form']")).click();

		// login with valid credentials
		driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"username\")")).sendKeys("admin");
		driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"password\")")).sendKeys("password");
		driver.findElement(MobileBy.AndroidUIAutomator("text(\"Log in\")")).click();
		String confirmationMessageValid = driver
				.findElement(MobileBy.AndroidUIAutomator("resourceId(\"action-confirmation\")")).getText();
		System.out.println(confirmationMessageValid);
		assertEquals(confirmationMessageValid, "Welcome Back, admin");

		// login with invalid credentials
		driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"username\")")).clear();
		driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"username\")")).sendKeys("admin");
		driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"password\")")).clear();
		driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"password\")")).sendKeys("nopassword");
		driver.findElement(MobileBy.AndroidUIAutomator("text(\"Log in\")")).click();
		String confirmationMessageInvalid = driver
				.findElement(MobileBy.AndroidUIAutomator("resourceId(\"action-confirmation\")")).getText();
		System.out.println(confirmationMessageInvalid);
		assertEquals(confirmationMessageInvalid, "Invalid Credentials");
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}
}
