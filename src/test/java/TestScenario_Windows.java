
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.JsonFormatter;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Set;

public class TestScenario_Windows {
    public static String username = System.getenv("LT_USERNAME");
    public static String access_key = System.getenv("LT_ACCESS_KEY");
    public static RemoteWebDriver driver = null;
    public String gridURL = "@hub.lambdatest.com/wd/hub";
    boolean status = false;

    @BeforeClass
    public void setUp() throws Exception {
        HashMap<String, Object> ltOptions = new HashMap<>();
        ltOptions.put("build", "Test Scenario Execution 1");
        ltOptions.put("name", "Windows OS and Chrome Browser Execution 1");
        ltOptions.put("tunnel", false);
        ltOptions.put("network", true);
        ltOptions.put("console", true);
        ltOptions.put("visual", true);
        ltOptions.put("selenium_version", "4.24.0");
        ltOptions.put("w3c", true);

        // Accessibility options
        ltOptions.put("accessibility", true);
        ltOptions.put("accessibility.wcagVersion", "wcag21a");
        ltOptions.put("accessibility.bestPractice", false);
        ltOptions.put("accessibility.needsReview", true);
        // Use browser-specific Options class for W3C compliance
        MutableCapabilities browserOptions;
        String browser = "Chrome", version = "128", platformName = "Windows 10";

        switch (browser.toLowerCase()) {
            case "chrome":
                browserOptions = new ChromeOptions();
                break;
            case "microsoftedge":
            case "edge":
                browserOptions = new EdgeOptions();
                break;
            case "firefox":
                browserOptions = new FirefoxOptions();
                break;
            default:
                browserOptions = new ChromeOptions();
        }
        browserOptions.setCapability("browserVersion", version);
        browserOptions.setCapability("platformName", platformName);
        browserOptions.setCapability("LT:Options", ltOptions);
        try {
            driver = new RemoteWebDriver(new URL("https://" + username + ":" + access_key + gridURL), browserOptions);
        } catch (MalformedURLException e) {
            System.out.println("Invalid grid URL");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    ExtentSparkReporter spark = new ExtentSparkReporter("target/surefire-reports/html/extentReport.html");
    JsonFormatter json = new JsonFormatter("target/surefire-reports/json/Extent_Report.json");
    ExtentReports extent = new ExtentReports();


    @Test
    public void runtest() {
        System.out.println("Test Execution Started");
        extent.attachReporter(json, spark);
        ExtentTest test1 = extent.createTest("Run the Testscenario", "To Do App test 1");
        System.out.println("Loading Url");

//        1. Navigate to https://www.testmuai.com/.
        driver.get("https://www.testmuai.com/");
        driver.manage().window().maximize();
        test1.log(Status.PASS, "URL is opened");
        waitForPageLoad(driver);
        test1.log(Status.PASS, "Wait created");
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        SoftAssert softAssert = new SoftAssert();


//        2. Perform an explicit wait till the time all the elements in the DOM are available.
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.ignoring(StaleElementReferenceException.class);


//        3. Scroll to the WebElement ‘Explore Agentic Clouds’ using the scrollIntoView() method.
//        You are free to use any of the available web locators (e.g., XPath, CSSSelector, etc.)
        WebElement exploreAgenticClouds_Link = driver.findElement(By.xpath("//a[text()='Explore Agentic Clouds']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", exploreAgenticClouds_Link);
        System.out.println("Explore Agentic Clouds Link- Scrolled into View");
        test1.log(Status.PASS, "Explore Agentic Clouds Link- Scrolled into View");
        wait.until(ExpectedConditions.elementToBeClickable(exploreAgenticClouds_Link));
        String parentWindow = driver.getWindowHandle();
        waitForPageLoad(driver);

//        4. Click on the link and ensure that it opens in a new tab.
        js.executeScript("arguments[0].setAttribute('target','blank');arguments[0].click();", exploreAgenticClouds_Link);
        System.out.println("Clicked on Explore Agentic Clouds Link");
        test1.log(Status.PASS, "Clicked on Explore Agentic Clouds Link");


//        5. Save the window handles in a List (or array). Print the window handles of the opened windows (now there are two windows open).
        Set<String> winHandles = driver.getWindowHandles();
        System.out.println("The window handles are :" + winHandles.toString());
        System.out.println("New tab is opened after clicking the Link");
        for (String winhandle : winHandles) {
            if (!(winhandle.equals(parentWindow))) {
                driver.switchTo().window(winhandle);
                break;
            }
        }

//        6. Verify whether the URL is the same as the expected URL (if not, throw an Assert).
        String expectedURL = "https://www.testmuai.com/agentic-cloud/";
        String actualURL = driver.getCurrentUrl().trim();
        softAssert.assertTrue(expectedURL.equals(actualURL), "Opened URL is mismatched," +
                " Expected URL :" + expectedURL + "Actual URL is :" + actualURL);
        System.out.println("New tab is opened after clicking the Link and the URL is matched succesfully");
        waitForPageLoad(driver);
        test1.log(Status.PASS, "ew tab is opened after clicking the Link and the URL is matched succesfully");

//        7. On that page, scroll to the page where the WebElement
//        (Seamlessly Scale with Agentic Cloud for Cross-Browser Testing) is present.
        WebElement seamlessAutomationLink = driver.findElement(By.xpath(
                "//h2[text()='Seamlessly Scale with Agentic Cloud for Cross-Browser Testing']"));
        js.executeScript("arguments[0].scrollIntoView(true);", seamlessAutomationLink);
        System.out.println("Seamlessly Scale with Agentic Cloud for Cross-Browser Testing- Scrolled into View");
        test1.log(Status.PASS, "Seamlessly Scale with Agentic Cloud for Cross-Browser Testing- Scrolled into View");

//        8. Click the ‘Try Now For Free’ link. The page should open in the same window.
        WebElement tryNowForFree_Link = driver.findElement(By.xpath("//h2[text()='Seamlessly Scale with " +
                "Agentic Cloud for Cross-Browser Testing']/following-sibling::a[text()='Try Now For Free']"));
        js.executeScript("arguments[0].setAttribute('target','_self');arguments[0].click();", tryNowForFree_Link);
        System.out.println("Try Now For Free- Scrolled into View");
        System.out.println("Clicked on Try Now For Free Link");
        test1.log(Status.PASS, "Clicked on Try Now For Free Link");


//        9. Check if the title of the page is ‘Sign up for free | Cross Browser Testing Tool’. If not, raise an Assert.
        waitForPageLoad(driver);
        String expectedPageTilte = "Sign up for free | Cross Browser Testing Tool | LambdaTest";
        String actualPageTitle = driver.getTitle();
        System.out.println("Validating the title of the page is ‘Sign up for free | Cross Browser Testing Tool | LambdaTest");
        softAssert.assertTrue(expectedPageTilte.equals(actualPageTitle), "The page title is mimatched. " +
                "Expected :" + expectedPageTilte + " Actual :" + actualPageTitle);
        System.out.println("Title Matched as expected");
        test1.log(Status.PASS, "Title Matched as expected");


//        10. Close the current window using the window handle [which we obtained in step (5)].
        System.out.println("Closing the current Window");
        driver.close();
        test1.log(Status.PASS, "Closing the current Window");

//        11. Print the current window count.
        Set<String> winhandles1 = driver.getWindowHandles();
        System.out.println("The window handles count is :" + winhandles1.size());

//        12. On the current window, set the URL to https://www.testmuai.com/blog.
        driver.switchTo().window(parentWindow);
        System.out.println("Navigate to the URL-https://www.testmuai.com/blog");
        driver.navigate().to("https://www.testmuai.com/blog/");

        test1.log(Status.PASS, "Navigate to the URL-https://www.testmuai.com/blog");
        waitForPageLoad(driver);

//        13. Click on the ‘Community’ link and verify whether the URL is https://community.testmuai.com/.
        WebElement communityLink = driver.findElement(By.linkText("Community"));
        js.executeScript("arguments[0].setAttribute('target','_self');arguments[0].click();", communityLink);
        System.out.println("Clicked on community Link");
        test1.log(Status.PASS, "Clicked on community Link");
        waitForPageLoad(driver);
        String expectedURL1 = "https://community.testmuai.com/";
        String actualURL1 = driver.getCurrentUrl();
        System.out.println("Validating the URL of the navigated page");
        softAssert.assertTrue(expectedURL1.equals(actualURL1), "Opened URL is mismatched , Expected URL :" + expectedURL1 + "Actual URL :" + actualURL1);
        System.out.println("URL Matched as expected");
        test1.log(Status.PASS, "URL Matched as expected");
        waitForPageLoad(driver);
        System.out.println("Test Finished");
        softAssert.assertAll();
        status = true;
        test1.log(Status.PASS, "Test Scenario Passed succesfully");
        extent.flush();

    }

    public static void waitForPageLoad(WebDriver driver) {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(driver1 -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            ((JavascriptExecutor) driver).executeScript("lambda-status=" + status);
            driver.quit();
        }
    }

}

