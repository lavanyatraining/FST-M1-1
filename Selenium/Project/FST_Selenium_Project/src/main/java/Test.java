import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Test {
	public static void main(String[] args) {
        //Create a new instance of the Firefox driver
        //WebDriver driver = new FirefoxDriver();
        WebDriver driver = new ChromeDriver();
        //Open the browser
        driver.get("https://www.example.com");
        System.out.println("Get Title:"+driver.getTitle());
        //Perform testing and assertions
        //...

        //Close the browser
       // driver.close();
    }
}
