package test.java;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Set;


public class lamdatestSelenium {

    private RemoteWebDriver driver;
    private String Status = "failed";
    @Parameters(value={"browserName","browserVersion","platformName"})
    @BeforeMethod
    public void setUp(String browserName, String browserVersion, String platformName, Method m, ITestContext ctx){
//        String username="sainadhpvnl";
//        String accessKey="LT_zUtXB44POAC2Kow2bsl8g2B5f84qdvnBJzQp4Q4o27GMFsa";
        String gridURL="@hub.lambdatest.com/wd/hub";
        String username = System.getenv("LT_USERNAME") == null ? "Your LT Username" : System.getenv("LT_USERNAME");
        String accessKey = System.getenv("LT_ACCESS_KEY") == null ? "Your LT AccessKey" : System.getenv("LT_ACCESS_KEY");
        System.out.println("Username is : "+username+" Access key is : "+accessKey);
//        System.out.println(browserName+browserVersion+platformName);
        if(browserName.equals("chrome") && platformName.equals("Windows 11")){
            // ✅ Use LambdaTest W3C-compliant structure (LT:Options)
            MutableCapabilities ltOptions = new MutableCapabilities();
            ltOptions.setCapability("build", "TestNG With Java");
            ltOptions.setCapability("name", m.getName() + " - " + this.getClass().getName());
            ltOptions.setCapability("platformName", "Windows 11"); // modern macOS
            ltOptions.setCapability("plugin", "git-testng");
            ltOptions.setCapability("tags", new String[] { "Feature", "Magicleap", "Severe" });
            // ✅ Standard browser options
            ChromeOptions browserOptions = new ChromeOptions();
            browserOptions.setCapability("browserVersion", "latest");
            browserOptions.setCapability("LT:Options", ltOptions);
            try{
                System.out.println("https://" + username+ ":" + accessKey + gridURL);
                driver=new RemoteWebDriver(new URL("https://" + username+ ":" + accessKey + gridURL),browserOptions);
            } catch (MalformedURLException e) {
                System.out.println("Invalid grid URL or an error occurred");
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
            driver.get("https://www.testmuai.com/");


//            ChromeOptions browserOptions = new ChromeOptions();
//            browserOptions.setPlatformName("Windows 11");
//            browserOptions.setBrowserVersion("dev");
//            HashMap<String, Object> ltOptions =new HashMap<String, Object>();
//            ltOptions.put("username",username);
//            ltOptions.put("accesskey",accessKey);
//            ltOptions.put("visual",true);
//            ltOptions.put("video",true);
//            ltOptions.put("network",true);
//            ltOptions.put("tunnel", true);
//
//            browserOptions.setCapability("LT:Options", ltOptions);
        } else if (browserName.equals("firefox") && platformName.equals("Windows 11")){
            FirefoxOptions  browserOptions = new FirefoxOptions();
            browserOptions.setPlatformName("Windows 11");
            browserOptions.setBrowserVersion("dev");
            HashMap<String, Object> ltOptions =new HashMap<String, Object>();
            ltOptions.put("username",username);
            ltOptions.put("accesskey",accessKey);
            ltOptions.put("visual",true);
            ltOptions.put("video",true);
            ltOptions.put("network",true);
            ltOptions.put("tunnel", true);
            ltOptions.put("w3c",true);
            ltOptions.put("plugin", "java-testNG");
            ltOptions.put("name","Test :" +browserName + " ");
            browserOptions.setCapability("LT:Options", ltOptions);
            try{
                driver=new RemoteWebDriver(new URL("https://" + username+ ":" + accessKey + gridURL),browserOptions);
            } catch (MalformedURLException e) {
                System.out.println("Invalid grid URL or an error occurred");
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        }else if (browserName.equals("safari") && platformName.equals("MacOS Tahoe")){
            SafariOptions browserOptions = new SafariOptions();
            browserOptions.setPlatformName("MacOS Tahoe");
            browserOptions.setBrowserVersion("26");
            HashMap<String, Object> ltOptions =new HashMap<String, Object>();
            ltOptions.put("username",username);
            ltOptions.put("accesskey",accessKey);
            ltOptions.put("visual",true);
            ltOptions.put("video",true);
            ltOptions.put("network",true);
            ltOptions.put("tunnel", true);
            ltOptions.put("w3c",true);
            ltOptions.put("plugin", "java-testNG");
            ltOptions.put("name","Test :" +browserName + " ");
            browserOptions.setCapability("LT:Options", ltOptions);
            try{
                driver=new RemoteWebDriver(new URL("https://" + username+ ":" + accessKey + gridURL),browserOptions);
            } catch (MalformedURLException e) {
                System.out.println("Invalid grid URL or an error occurred");
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        }


//        DesiredCapabilities cap=new DesiredCapabilities();
//        cap.setCapability("browserName",browserName);
//        cap.setCapability("browserVersion",browserVersion);
//        cap.setCapability("platformName",platformName);
//        cap.setCapability("build", "Selenium Certification Build 1");
//        cap.setCapability("name", "TestScenario 1");

//        cap.setCapability("LT:Options", ltOptions);



    }

    @Test(timeOut = 20000)
    public void testLamdaTestNavigation() throws  InterruptedException{
        System.out.println("Loading Url");
//        1. Navigate to https://www.testmuai.com/.
        WebDriver driver=new ChromeDriver();
        driver.get("https://www.testmuai.com/");
        driver.manage().window().maximize();
        waitForPageLoad(driver);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        SoftAssert softAssert=new SoftAssert();


//        2. Perform an explicit wait till the time all the elements in the DOM are available.
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.ignoring(StaleElementReferenceException.class);
//        waitForPageLoad(driver);
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("")));

//        3. Scroll to the WebElement ‘Explore Agentic Clouds’ using the scrollIntoView() method.
//        You are free to use any of the available web locators (e.g., XPath, CSSSelector, etc.)
        WebElement exploreAgenticClouds_Link=driver.findElement(By.xpath("//a[text()='Explore Agentic Clouds']"));
        JavascriptExecutor js=(JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", exploreAgenticClouds_Link);
        System.out.println("Explore Agentic Clouds Link- Scrolled into View");
        wait.until(ExpectedConditions.elementToBeClickable(exploreAgenticClouds_Link));
        String parentWindow=driver.getWindowHandle();
        waitForPageLoad(driver);

//        4. Click on the link and ensure that it opens in a new tab.
        js.executeScript("arguments[0].setAttribute('target','blank');arguments[0].click();",exploreAgenticClouds_Link);
        System.out.println("Clicked on Explore Agentic Clouds Link");


//        5. Save the window handles in a List (or array). Print the window handles of the opened windows (now there are two windows open).
        Set<String> winHandles=driver.getWindowHandles();
        System.out.println("The window handles are :"+ winHandles.toString());
        System.out.println("New tab is opened after clicking the Link");
        for(String winhandle:winHandles){
            if(!(winhandle.equals(parentWindow))){
                driver.switchTo().window(winhandle);
                break;
            }
        }

//        6. Verify whether the URL is the same as the expected URL (if not, throw an Assert).
        String expectedURL="https://www.testmuai.com/agentic-cloud/";
        String actualURL=driver.getCurrentUrl().trim();
        softAssert.assertTrue(expectedURL.equals(actualURL),"Opened URL is mismatched," +
                " Expected URL :"+ expectedURL+"Actual URL is :"+actualURL);
        System.out.println("New tab is opened after clicking the Link and the URL is matched succesfully");
        waitForPageLoad(driver);

//        7. On that page, scroll to the page where the WebElement
//        (Seamlessly Scale with Agentic Cloud for Cross-Browser Testing) is present.
        WebElement seamlessAutomationLink=driver.findElement(By.xpath(
                "//h2[text()='Seamlessly Scale with Agentic Cloud for Cross-Browser Testing']"));
        js.executeScript("arguments[0].scrollIntoView(true);", seamlessAutomationLink);
        System.out.println("Seamlessly Scale with Agentic Cloud for Cross-Browser Testing- Scrolled into View");

//        8. Click the ‘Try Now For Free’ link. The page should open in the same window.
        WebElement tryNowForFree_Link=driver.findElement(By.xpath("//h2[text()='Seamlessly Scale with " +
                "Agentic Cloud for Cross-Browser Testing']/following-sibling::a[text()='Try Now For Free']"));
        js.executeScript("arguments[0].setAttribute('target','_self');arguments[0].click();",tryNowForFree_Link);
        System.out.println("Try Now For Free- Scrolled into View");
        System.out.println("Clicked on Try Now For Free Link");

//        9. Check if the title of the page is ‘Sign up for free | Cross Browser Testing Tool’. If not, raise an Assert.
        waitForPageLoad(driver);
        String expectedPageTilte="Sign up for free | Cross Browser Testing Tool | LambdaTest";
        String actualPageTitle=driver.getTitle();
        System.out.println("Validating the title of the page is ‘Sign up for free | Cross Browser Testing Tool | LambdaTest");
        softAssert.assertTrue(expectedPageTilte.equals(actualPageTitle),"The page title is mimatched. " +
                "Expected :"+expectedPageTilte+" Actual :"+actualPageTitle);
        System.out.println("Title Matched as expected");

//        10. Close the current window using the window handle [which we obtained in step (5)].
        System.out.println("Closing the current Window");
        driver.close();

//        11. Print the current window count.
        Set<String> winhandles1=driver.getWindowHandles();
        System.out.println("The window handles count is :"+winhandles1.size());

//        12. On the current window, set the URL to https://www.testmuai.com/blog.
        driver.switchTo().window(parentWindow);
        System.out.println("Navigate to the URL-https://www.testmuai.com/blog");
        driver.navigate().to("https://www.testmuai.com/blog/");
        waitForPageLoad(driver);

//        13. Click on the ‘Community’ link and verify whether the URL is https://community.testmuai.com/.
        WebElement communityLink=driver.findElement(By.linkText("Community"));
        js.executeScript("arguments[0].setAttribute('target','_self');arguments[0].click();",communityLink);
        System.out.println("Clicked on community Link");
        waitForPageLoad(driver);
        String expectedURL1="https://community.testmuai.com/";
        String actualURL1=driver.getCurrentUrl();
        System.out.println("Validating the URL of the navigated page");
        softAssert.assertTrue(expectedURL1.equals(actualURL1),"Opened URL is mismatched , Expected URL :"+expectedURL1+"Actual URL :"+actualURL1);
        System.out.println("URL Matched as expected");
        waitForPageLoad(driver);
//        Status = "passed";
        System.out.println("Test Finished");

        softAssert.assertAll();


    }

    @AfterMethod
    public void tearDown() {
        try {
            driver.executeScript("lambdatest_executor: {\"action\": \"stepcontext\", \"arguments\": {\"data\": \"Adding Test Result and Closing Browser\", \"level\": \"info\"}}");
            driver.executeScript("lambda-status=" + Status);
        } finally {
            if (driver != null) {
                //        14. Close the current browser window.
                driver.quit();
            }
        }
    }

    public static void waitForPageLoad(WebDriver driver){
        Wait<WebDriver> wait =new WebDriverWait(driver,Duration.ofSeconds(60));
        wait.until(driver1-> ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete"));
    }
}
