package bs;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

public class Base {

    protected static AppiumDriver driver;
    protected static Properties props;

    public Base()
    {
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }

    @BeforeTest
    public void setUp()
    {
        props = new Properties();
        String propFileName = System.getProperty("user.dir") +"/src/test/resources/Global.properties";
        String app = System.getProperty("user.dir") +props.get("androidAppLocation");

        try
        {
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
        dc.setCapability(MobileCapabilityType.PLATFORM_NAME, props.get("platformName"));
        dc.setCapability(MobileCapabilityType.DEVICE_NAME,props.get("androidDevice"));
        dc.setCapability(MobileCapabilityType.APP, app);
        dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, props.get("androidAutomationName"));
        dc.setCapability("appPackage",props.get("androidPackageName"));
        dc.setCapability("appActivity",props.get("androidAppActivity"));
        URL url = new URL(props.getProperty("appiumURL") + "4723/wd/hub");
        driver= new AndroidDriver(url,dc);
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }

    }


    public void waitForVisibility(WebElement e)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(70));
        wait.until(ExpectedConditions.visibilityOf(e));
    }

    public void waitForExplicitVisibility(WebElement e){
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.visibilityOf(e));
    }

    public void clear(WebElement e) {
        waitForVisibility(e);
        e.clear();
    }

    public void click(WebElement e) {
        waitForVisibility(e);
        e.click();
    }

    public void sendKeys(WebElement e, String txt) {
        waitForVisibility(e);
        e.sendKeys(txt);
    }

    public void getAttribute(WebElement e, String attr) {
        waitForVisibility(e);
        e.getAttribute(attr);
    }

    @AfterTest
    public void tearDown()
    {
        driver.quit();
    }


}
