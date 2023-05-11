package ObjectStyle.bs;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.screenrecording.CanRecordScreen;
import io.netty.handler.codec.base64.Base64Encoder;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.TestUtils;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Base2 {

    protected static AppiumDriver driver;
    protected static Properties props;
    protected static FileInputStream fis;
    protected static String dateTime;
    protected String pn2;
    protected String em2;
    protected String dn2;
    TestUtils utils;

    public void setDriver(AppiumDriver driver)
    {
        this.driver = driver;
    }

    public AppiumDriver getDriver()
    {
        return driver;
    }

    public void initialiseDriver(String em2, String pn2,String dn2)
    {
        utils = new TestUtils();
        dateTime = utils.getDateTime();
        try
        {
        props = new Properties();
        String propFileName = System.getProperty("user.dir")+"/src/test/resources/Global.properties";
        URL url;
        fis = new FileInputStream(propFileName);
        props.load(fis);
        String platformName = (String) props.get("platformName");
        System.out.println(em2);
        String androidEmulatorAlowed = em2;
        String iOSEmulatorAlowed = (String) props.get("iOSEmulator");

        DesiredCapabilities dc = new DesiredCapabilities();
        switch (platformName)
        {
            case "Android":
                dc.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
                dc.setCapability(MobileCapabilityType.PLATFORM_NAME, pn2);
                dc.setCapability(MobileCapabilityType.DEVICE_NAME,dn2);

                if(androidEmulatorAlowed.equalsIgnoreCase("true"))
                {
                   dc.setCapability("platformVersion",props.get("androidPlatformVersion"));
                    //           dc.setCapability("avd", props.get("androidDevice"));
                }
                else
                {
                    dc.setCapability("udid", props.get("androidUDID"));
                }

                String androidApp = System.getProperty("user.dir") + props.getProperty("androidAppLocation");
                dc.setCapability(MobileCapabilityType.APP, androidApp);
                dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, props.getProperty("androidAutomationName"));
                dc.setCapability("appPackage", props.getProperty("androidPackageName"));
                dc.setCapability("appActivity", props.getProperty("androidAppActivity"));
                    url = new URL("http://127.0.0.1:4723/wd/hub");
                driver = new AndroidDriver(url, dc);
                break;

            case "iOS":
                dc.setCapability(MobileCapabilityType.PLATFORM_NAME, props.get("platformName"));
                if(iOSEmulatorAlowed.equalsIgnoreCase("true"))
                {
                    dc.setCapability("platformVersion",props.get("iOSPlatformVersion"));
                    dc.setCapability("avd", props.get("iOSDevice"));
                }
                else
                {
                    dc.setCapability("udid", props.get("iosUDID"));
                }

                String iOSApp = System.getProperty("user.dir") + props.getProperty("iOSAppLocation");
                dc.setCapability(MobileCapabilityType.APP, iOSApp);
                dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, props.get("iOSAutomationName"));

                url = new URL(props.getProperty("appiumURL") + "4724/wd/hub");
                driver = new IOSDriver(url, dc);
                break;

            default :
                throw new Exception("Invalid platform! =");
        }
            setDriver(driver);
            String sessionId = driver.getSessionId().toString();
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
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

    public String getDateTime()
    {
        return dateTime;
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

    public void quitDriver()
    {
        driver.quit();
    }


}
