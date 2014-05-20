import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import se.sigma.selenium.po.HomePage;
import se.sigma.selenium.po.LocationPage;
import se.sigma.selenium.po.SearchResultPage;

public class testing {
    private WebDriver driver;

    @Before
    public void setUp() {
    	File file = new File("C:/chromedriver.exe");
    	System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
    	WebDriver driver = new ChromeDriver();
        driver = new ChromeDriver();
    }



	@Test
    public void verifyThatStockholmCanBeFound() {
        HomePage home = new HomePage(driver);
        SearchResultPage searchResult = home.searchFor("Stockholm");

        LocationPage stockholm = searchResult.clickOnTopSearchResultLink();

        String expected = "Stockholm";
        String actual = stockholm.getHeadLine();
        assertTrue(actual.contains(expected));
    }
}