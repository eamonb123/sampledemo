//package com.example.tests;
//
//import java.util.regex.Pattern;
//import java.util.concurrent.TimeUnit;
//import org.junit.*;
//import static org.junit.Assert.*;
//import static org.hamcrest.CoreMatchers.*;
//import org.openqa.selenium.*;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.support.ui.Select;
//
//public class Test {
//  private WebDriver driver;
//  private String baseUrl;
//  private boolean acceptNextAlert = true;
//  private StringBuffer verificationErrors = new StringBuffer();
//
//  @Before
//  public void setUp() throws Exception {
//    driver = new FirefoxDriver();
//    baseUrl = "https://www.google.com/";
//    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//  }
//
//  @Test
//  public void test() throws Exception {
//    driver.get(baseUrl + "/");
//    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | name=umps|NA14$00Dd0000000eKuN_005d000000140GA_02_2d4879c8-8781-43b1-a280-bfa7a4fcc5b2|4| | ]]
//    driver.findElement(By.name("configure")).click();
//    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | name=umps|NA14$00Dd0000000eKuN_005d000000140GA_02_2d4879c8-8781-43b1-a280-bfa7a4fcc5b2|4| | ]]
//    driver.findElement(By.id("j_id0:j_id1:btnOnLoad")).click();
//    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | name=umps|NA14$00Dd0000000eKuN_005d000000140GA_02_2d4879c8-8781-43b1-a280-bfa7a4fcc5b2|4| | ]]
//    driver.findElement(By.id("j_id0:idForm:j_id409:1:j_id412")).click();
//    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | name=umps|NA14$00Dd0000000eKuN_005d000000140GA_02_2d4879c8-8781-43b1-a280-bfa7a4fcc5b2|4| | ]]
//    driver.findElement(By.xpath("//div[@id='carouselContainer']/div/div/div/div[5]/div/img")).click();
//    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | name=umps|NA14$00Dd0000000eKuN_005d000000140GA_02_2d4879c8-8781-43b1-a280-bfa7a4fcc5b2|4| | ]]
//    driver.findElement(By.name("j_id0:idForm:j_id175:0:j_id218")).click();
//    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | name=umps|NA14$00Dd0000000eKuN_005d000000140GA_02_2d4879c8-8781-43b1-a280-bfa7a4fcc5b2|4| | ]]
//    driver.findElement(By.id("j_id0:idForm:j_id477:0:j_id480")).click();
//    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | name=umps|NA14$00Dd0000000eKuN_005d000000140GA_02_2d4879c8-8781-43b1-a280-bfa7a4fcc5b2|4| | ]]
//    driver.findElement(By.id("j_id0:idForm:j_id409:2:j_id412")).click();
//    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | name=umps|NA14$00Dd0000000eKuN_005d000000140GA_02_2d4879c8-8781-43b1-a280-bfa7a4fcc5b2|4| | ]]
//    assertEquals("Upsert failed. First exception on row 0 with id a0yd0000006jG98AAE; first error: ENTITY_IS_DELETED, entity is deleted: []", closeAlertAndGetItsText());
//    driver.findElement(By.id("gbqfq")).clear();
//    driver.findElement(By.id("gbqfq")).sendKeys("test");
//    driver.findElement(By.id("gbqfq")).clear();
//    driver.findElement(By.id("gbqfq")).sendKeys("test2");
//  }
//
//  @After
//  public void tearDown() throws Exception {
//    driver.quit();
//    String verificationErrorString = verificationErrors.toString();
//    if (!"".equals(verificationErrorString)) {
//      fail(verificationErrorString);
//    }
//  }
//
//  private boolean isElementPresent(By by) {
//    try {
//      driver.findElement(by);
//      return true;
//    } catch (NoSuchElementException e) {
//      return false;
//    }
//  }
//
//  private boolean isAlertPresent() {
//    try {
//      driver.switchTo().alert();
//      return true;
//    } catch (NoAlertPresentException e) {
//      return false;
//    }
//  }
//
//  private String closeAlertAndGetItsText() {
//    try {
//      Alert alert = driver.switchTo().alert();
//      String alertText = alert.getText();
//      if (acceptNextAlert) {
//        alert.accept();
//      } else {
//        alert.dismiss();
//      }
//      return alertText;
//    } finally {
//      acceptNextAlert = true;
//    }
//  }
//}
