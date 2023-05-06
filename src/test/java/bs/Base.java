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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

public class Base {

    protected static AppiumDriver driver;
    protected static Properties props;
    protected static FileInputStream fis;

    public Base()
    {
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }

    @BeforeTest
    public void setUp()
    {
        try
        {
        props = new Properties();
        String propFileName = System.getProperty("user.dir")+"/src/test/resources/Global.properties";
        fis = new FileInputStream(propFileName);
        props.load(fis);


        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
        dc.setCapability(MobileCapabilityType.PLATFORM_NAME, props.getProperty("platformName"));
        dc.setCapability(MobileCapabilityType.DEVICE_NAME,props.getProperty("androidDevice"));
            String app = System.getProperty("user.dir")+props.getProperty("androidAppLocation");
        dc.setCapability(MobileCapabilityType.APP, app);
        dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, props.getProperty("androidAutomationName"));
        dc.setCapability("appPackage",props.getProperty("androidPackageName"));
        dc.setCapability("appActivity",props.getProperty("androidAppActivity"));
        URL url = new URL("http://127.0.0.1:4723/wd/hub");
        driver= new AndroidDriver(url,dc);
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
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

    public String getAttribute(WebElement e, String attr) {
        waitForVisibility(e);
        return(e.getAttribute(attr));
    }

    @AfterTest
    public void tearDown()
    {
        driver.quit();
    }


}
