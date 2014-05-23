import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Facebook {
	static WebDriver driver;
	static WebElement element;
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		login(driver, "https://www.facebook.com/", "eamonb", "Shnarbi1");
		driver.findElement(By.name("mercurymessages")).click();
		driver.findElement(By.xpath("//*[@id='MercuryJewelFooter']/div/a/span")).click();
		driver.findElement(By.partialLinkText("test")).click();
//		driver.findElement(By.xpath("//*[@id='js_28']/div[1]/div[2]")).click();
//		driver.findElement(By.name("message_body")).sendKeys("hello");
//		String paragraph = "Judge Gladys Kessler said ruling that she faced “an anguishing Hobson’s choice” involving the detainee, Jihad Ahmed Mu “unnecessary” suffering “The court simply cannot let Mr. Dhiab die,” she wrote, using an alternate spelling for the detainee’s name.The force-feeding procedure involves strapping a detainee into a restraint chair and inserting a tube through his nose and down his throat. Liquid nutritional supplement is then poure";
//		String[] stringArray = paragraph.split(" ");
//		for (int i=0; i<stringArray.length; i++)
//		{
//			driver.findElement(By.name("message_body")).sendKeys(stringArray[i] + "\n");
//		}
	}
	
	public static void dropdownModTest(WebDriver driver, String select, String options, String selection)
	{
		WebElement s = driver.findElement(By.id(select));
		List<WebElement> allOptions = s.findElements(By.tagName(options));
		boolean found = false;
		for (WebElement option : allOptions) {
		    if (option.getText().equals(selection))
		    {
		    	found = true;
		    	option.click();
		    }
		}
		try {
			if (!found)
				throwException("'" + selection + "'" + " not found. check for misspelling");
		} catch (Exception e) {}
	}
	
	public static void login(WebDriver driver, String url, String username, String password){
		driver.get(url);
		driver.findElement(By.id("email")).sendKeys(username);
		driver.findElement(By.id("pass")).sendKeys(password);
		driver.findElement(By.id("loginbutton")).click();
	}
	
	public static void verifyText(String result, String expected) throws Exception
	{
		if (!result.equals(expected))
			throwException("Expected to see " + expected + " but see " + result);
	}
	
	public static void throwException(String output) throws Exception
	{
		throw new Exception(output);
		
	}
	public static void highlightElement(WebElement element) {
		
		String originalStyle = element.getAttribute("style");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
		wait(8000);
		System.out.println("done");
		js.executeScript("arguments[0].setAttribute('style', '" + originalStyle + "');", element);
	}
	
	public static void wait(int value)
	{
		try {
			Thread.sleep(value);
		} 
		catch (InterruptedException e) {}
	}

}
