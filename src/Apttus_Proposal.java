import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
		driver.findElement(By.name("new")).click();
		dropdownModTest(driver, "p3", "option", "Proposal");
		driver.findElement(By.xpath("//input[@title='Continue']")).click();
		driver.findElement(By.id("00Nd0000003gJNX")).sendKeys("RV_200");
		driver.findElement(By.id("CF00Nd0000003gJNO")).sendKeys("Test");
		driver.findElement(By.id("CF00Nd0000003gJNC")).sendKeys("Demo Account");
		driver.findElement(By.id("CF00Nd0000003gLWU")).sendKeys("HG Test PL");
		driver.findElement(By.name("save")).click();
		driver.findElement(By.name("configure")).click();
		driver.findElement(By.className("aptCarrouselImage")).click();
		driver.findElement(By.xpath("//*[@id='carouselContainer']/div/div[1]/div/div[5]")).click();
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='j_id0:idForm:j_id175:0:j_id217']/input")));
		driver.findElement(By.xpath("//*[@id='j_id0:idForm:j_id175:0:j_id217']/input")).click();
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated((By.name("j_id0:idForm:j_id477:0:j_id480"))));
		driver.findElement(By.name("j_id0:idForm:j_id477:0:j_id480")).click();
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='j_id0:idForm:j_id409:2:j_id412']")));
//		System.out.println("HELLO");
//		
//		highlightElement(driver.findElement(By.xpath("//*[@id='j_id0:idForm:j_id409:2:j_id412']")));
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
	
	public static void login(WebDriver driver, String username, String password){
		driver.get("https://login.salesforce.com/");
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("Login")).click();
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
