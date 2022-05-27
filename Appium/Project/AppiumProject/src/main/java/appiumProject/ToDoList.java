package appiumProject;

import static org.testng.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class ToDoList {
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
	public void alchemyToDoList() throws InterruptedException {

		// navigate to webpage
		driver.get("https://www.training-support.net/selenium");
		driver.findElement(MobileBy.AndroidUIAutomator(
				"UiScrollable(UiSelector().scrollable(true).instance(0)).scrollIntoView(textStartsWith(\"To-Do List\"))"));
		driver.findElement(MobileBy.xpath("//android.widget.TextView[@text='To-Do List']")).click();
		driver.findElement(
				By.xpath("//android.webkit.WebView/android.view.View/android.view.View[3]/android.view.View[3]"))
				.click();
		driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"taskInput\")")).sendKeys("Add tasks to list");
		driver.findElement(MobileBy.AndroidUIAutomator("text(\"Add Task\")")).click();
		driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"taskInput\")")).sendKeys("Get number of tasks");
		driver.findElement(MobileBy.AndroidUIAutomator("text(\"Add Task\")")).click();
		driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"taskInput\")")).sendKeys("Clear the list");
		driver.findElement(MobileBy.AndroidUIAutomator("text(\"Add Task\")")).click();
		driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"tasksList\")")).click();
		driver.findElement(
				By.xpath("//android.webkit.WebView/android.view.View/android.view.View[3]/android.view.View[3]"))
				.click();
		// checking the remained tasks count
		List<MobileElement> tasksLeft = driver.findElements(MobileBy.AndroidUIAutomator("resourceId(\"tasksList\")"));
		int countofTasksList = tasksLeft.size();
		assertEquals(countofTasksList, 0);
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}

}
