import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Apttus_Proposal {
	static WebDriver driver;
	static WebElement element;
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		login(driver, "hghia@apttus.com.cpqqa1", "test123#");
		try {
			verifyText(driver.findElement(By.id("tsidLabel")).getText(), "Apttus Proposal Management");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		driver.findElement(By.xpath("//*[@id='01rd0000000AiHE_Tab']/a")).click();
	}

	
	
	public static void login(WebDriver driver, String username, String password){
		driver.get("https://login.salesforce.com/");
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("Login")).click();
	}
	
	public static void verifyText(String result, String expected) throws Exception
	{
		if (!result.equals(expected))
			throw new Exception("Expected to see " + expected + " but see " + result);
	}
	
	public static void highlightElement(WebElement element) {
		
		String originalStyle = element.getAttribute("style");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
		wait(3000);
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
