import org.apache.commons.lang3.ArrayUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import java.util.List;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;


public class test {

	public static void main(String[] args) {
        // Create a new instance of the html unit driver
        // Notice that the remainder of the code relies on the interface, 
        // not the implementation.
		
        WebDriver driver = new HtmlUnitDriver();
        // And now use this to visit Google
        driver.get("http://www.google.com");

        // Find the text input element by its name
        WebElement element = driver.findElement(By.name("q"));
        // and vice versa
        // Enter something to search for
        element.sendKeys(new String[] {"shnarbis"});
        // Now submit the form. WebDriver will find the form for us from the element
        element.submit();

        // Check the title of the page
        System.out.println("Page title is: " + driver.getCurrentUrl());

        driver.quit();
    }
	
	public WebDriver driver;

    int timeOutCounter; // to have count on timeout
    /**
     * Timeout in sec. after which methods in this class should timeout while
     * waiting for a WebElemnet to load. Defaults to 60 sec.
     */
    public int TIMEOUT = 60;

    /**
     * supported drivers are : firefox driver
     * 
     * @param driver
     *            - WebDriver
     * 
     */
    public test(WebDriver driver) {
        this.driver = driver;
        this.driver.manage().window().maximize();
    }

    /**
     * opens the given url in browser,blocks any action until page is fully
     * loaded
     * 
     * @param url
     * 
     */
    public void openUrl(String url) {
        driver.get(url);
    }

    /**
     * perform the log in actions.
     * 
     * @param userName
     * @param password
     * 
     */
    public void login(String userName, String password) {

        WebElement userNameTxt = driver.findElement(By.name("username"));
        WebElement passwordTxt = driver.findElement(By.name("pw"));
        userNameTxt.sendKeys(new String[] {userName});
        passwordTxt.sendKeys(new String[] {password});

        WebElement loginBtn = driver.findElement(By.name("Login"));
        loginBtn.click();
    }

    /**
     * selects the given tab. Timeouts if the tab is not appeared before TIMEOUT
     * class variable
     * 
     * @param tab
     *            - text on tab
     */
    @SuppressWarnings("static-access")
    public void selectTab(String tab) {
        WebElement mTab = findElement(By.cssSelector("#tabBar").linkText(tab));
        mTab.click();
    }

    /**
     * select the given application.
     * 
     * Timeouts if the application list in top right is not appeared before
     * TIMEOUT class variable
     * 
     * @param app
     *            - application name
     */
    public void selectApp(String app) {
        WebElement currentApp = findElement(By.id("tsidLabel"));
        if (currentApp.getText().equalsIgnoreCase(app))
            return;

        WebElement appBtn = findElement(By.id("tsid"));
        appBtn.click();
        @SuppressWarnings("static-access")
        WebElement mApp = findElement(By.cssSelector("#tsid-menuItems")
                .linkText(app));
        mApp.click();
    }

    /**
     * clicks the button with the given label.
     * 
     * It waits for the given button to load and timeouts after the value of
     * TIMEOUT class variable
     * 
     * @param btn
     *            - label on button
     * @throws InterruptedException 
     * @throws TimeoutException 
     */
    public void clickBtn(String btn) throws TimeoutException, InterruptedException {
        getBtn(btn).click();
    }

    /**
     * gets the button with the specified label.
     * 
     * It waits for the given button to load and timeouts after the value of
     * TIMEOUT class variable
     * 
     * @param btn
     *            - label on button
     * @return - WebElement : button with the given label
     * @throws InterruptedException 
     * @throws TimeoutException 
     * 
     */
    public WebElement getBtn(String btn) throws TimeoutException, InterruptedException {

        List<WebElement> buttons = driver.findElements(By.className("btn"));
        for (WebElement button : buttons) {

            if (button.getAttribute("value").trim()
                    .equalsIgnoreCase(btn.trim())) {
                timeOutCounter = 0;
                return button;
            }

        }

        mWait("TimeOut after waiting " + TIMEOUT
                + " sec. for the button with text " + btn + " to appear");

        return getBtn(btn);

    }

    /**
     * gets any Input with the given label. e.g. text boxes, dropdowns.
     * 
     * It waits for the given element to load and timeouts after the value of
     * TIMEOUT class variable
     * 
     * @param label
     *            - label for input
     * @return WebElement - input element for given label
     * @throws InterruptedException 
     * @throws TimeoutException 
     */
    public WebElement getInputWithLabel(String label) throws TimeoutException, InterruptedException {

        List<WebElement> labels = driver.findElements(By.tagName("label"));
        for (WebElement mLabel : labels) {
            if (mLabel.getText().trim().endsWith(label.trim())) {
                timeOutCounter = 0;
                String labelFor = mLabel.getAttribute("for");
                return findElement(By.id(labelFor));
            }
        }

        mWait("TimeOut after waiting " + TIMEOUT
                + " sec. for the input with label " + label + " to appear");

        return getInputWithLabel(label);
    }

    /**
     * finds a WebElement based on By selector.
     * 
     * This method will wait for the element to load and will timeout after the
     * value of TIMEOUT class variable.
     * 
     * use this method instead of driver.findElement(By) to wait for the element
     * to load till TIMEOUT class variable
     * 
     * @param by
     *            - selector
     * @return - WEbElement
     */
    public WebElement findElement(final By by) {
        WebElement element = (new WebDriverWait(driver, TIMEOUT))
                .until(new ExpectedCondition<WebElement>() {
                    public WebElement apply(WebDriver d) {
                        return d.findElement(by);
                    }
                });

        return element;
    }

    /**
     * finds all elements based on By selector
     * It willl wait for the @minimumElements to load which matches selection criteria
     * will timeout after TIMEOUT if it does not find elemnets >= @minimumElements that matches selection criteria
     * 
     * @param by - By Selector
     * @param minimumElements - min number of matching elements,for which method should wait to load 
     * @return - list of web elemnts matching criteria
     * @throws TimeoutException
     * @throws InterruptedException
     */
    public List<WebElement> findElements(By by,int minimumElements) throws TimeoutException, InterruptedException{
        List<WebElement> elements  = driver.findElements(by);
        int size = elements.size();
        if(size >= minimumElements){
            timeOutCounter=0;
            return elements;
        }
        
        mWait("Timeout after waiting "+TIMEOUT+" secs. for required elements to load");
        
        return findElements(by, minimumElements);
    
    }
    /**
     * switches to latest opened window.
     * 
     * further methods will execute on the latest opened window.
     * 
     * @return - Window handle for the current window.
     * 
     *         call driver.switchTo.window(@return) to switch to original window
     */
    public String switchToNewlyOpenedWindow() {
        // get the handle of latest window
        String oldWinHandle = driver.getWindowHandle();
        String latestWinHandle = null;
        for (String winHandle : driver.getWindowHandles())
            latestWinHandle = winHandle;
        driver.switchTo().window(latestWinHandle);
        return oldWinHandle;
    }

    /**
     * switches to frame at given index.
     * 
     * It waits for the given frame to load and timeouts after the value of
     * TIMEOUT class variable.
     * 
     * @param index
     *            - Zero based index of frame
     * @throws InterruptedException 
     * @throws TimeoutException 
     */
    public void switchToFrame(int index) throws TimeoutException, InterruptedException {
        try {
            driver.switchTo().frame(index);
            timeOutCounter = 0;
        } catch (NoSuchFrameException e) {
            mWait("TimeOut after waiting " + TIMEOUT
                    + " sec. for the frame at index " + index + " to appear");
            switchToFrame(index);
        }
    }

    /**
     * asserts page title
     * waits till TIMEOUT for the title to load
     * 
     * @param title
     * @throws AssertionError
     * @throws InterruptedException
     */
    public void assertPageTitleContains(String title) throws AssertionError,InterruptedException{
        if(!driver.getTitle().trim().toLowerCase().contains(title.trim().toLowerCase())){
            //wait for page load
            try{
                 mWait("timed out after "+TIMEOUT+" sec");
                 assertPageTitleContains(title);
            }catch (TimeoutException e) {
                throw new AssertionError("expected page title to contain : "
                        + title + " , found : " + driver.getTitle());
            }
        }else
            //reset timeoutCounter
            timeOutCounter=0;
    }
    
    /**
     * asserts page title not to contain a string
     * waits till TIMEOUT for title to load
     *
     * @param notInTitle
     * @throws AssertionError
     * @throws InterruptedException
     */
    public void assertPageTitleDoesNotContain(String notInTitle) throws AssertionError,InterruptedException{
        if(driver.getTitle().trim().toLowerCase().contains(notInTitle.trim().toLowerCase())){
            //wait for page load
            try{
                 mWait("timed out after "+TIMEOUT+" sec");
                 assertPageTitleDoesNotContain(notInTitle);
            }catch (TimeoutException e) {
                throw new AssertionError("expected page title to not contain : "
                        + notInTitle + " , found : " + driver.getTitle());
            }
        }else
            //reset timeoutCounter
            timeOutCounter=0;
    }

    /**
     * asserts if a button with given label is visible on the page
     * waits till TIMEOUT for the btn to load
     * @param btnLabel
     * @throws AssertionError
     * @throws InterruptedException
     */
    public void assertBtnIsVisible(String btnLabel) throws AssertionError, InterruptedException{
        try{
            getBtn(btnLabel);
        }catch (TimeoutException e) {
            assertCondition(false,btnLabel+" button was not visible");
        }
    }
    
    /**
     * waits for 1 second.Timeouts if it has already waited
     * TIMEOUT sec. in the calling function, and fails with @timeoutMessage
     * 
     * @param timeoutMessage
     * @throws TimeoutException
     * @throws InterruptedException
     */
    public void mWait(String timeoutMessage) throws TimeoutException,InterruptedException{
        
            if (timeOutCounter > TIMEOUT) {
                timeOutCounter = 0;
                throw new TimeoutException(timeoutMessage);
            }
            Thread.sleep(1000);
            timeOutCounter++;
        
    }
    
    /**
     * returns the label of selected tab
     * 
     * @return
     */
    public String getSelectedTabLabel(){
        //only selected tab contains span element in tab bar
        WebElement span = findElement(By.cssSelector("#tabBar")).findElement(By.tagName("span"));
        //get parent of span
        WebElement parent = span.findElement(By.xpath(".."));
        //get link for the tab
        WebElement tabLink = parent.findElement(By.tagName("a")); 
        return tabLink.getText().trim();
    }

    /**
     * checks if all empty mandatory fields gives error
     * after form submit
     * works for input tag only,ignores picklists as they always
     * have a value
     * 
     * @throws InterruptedException 
     * @throws TimeoutException 
     * 
     */
    public void assertMandatoryFieldsValidated(int minimumFieldsToValidate) throws TimeoutException, InterruptedException,AssertionError{
        
        List<WebElement> rqdInputs = findElements(By.className("requiredInput"), minimumFieldsToValidate);
        
        for(WebElement element : rqdInputs){
            List<WebElement> inputField = element.findElements(By.tagName("input"));
            //I used findElements and not findElement,as later could throw
            //NoSuchElementException if there is no input tag but a picklist
            if(inputField.size()==0)
                continue;
            else{
                //there is at most one input field per '.requiredInput' div
                if(inputField.get(0).getText().equalsIgnoreCase("")){
                    //there must be error message
                    try{
                        if(!element.findElement(By.className("errorMsg")).getText().equalsIgnoreCase("Error: You must enter a value"))
                            throw new Exception();
                    }catch (Exception e) {
                        //either the error msg was not there or
                        //it was not "Error: You must enter a value"
                        throw new AssertionError("Empty mandatory field did not show - 'Error: You must enter a value'");
                    }
                }
            }
        }
        
    }
    /**
     * quits the test and closes all browser windows
     */
    public void cleanUp() {
        driver.quit();
    }
    
    public void assertCondition(boolean condition,String failureMsg) throws AssertionError{
        if(!condition)
            throw new AssertionError(failureMsg);
    }


}
