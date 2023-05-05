package bs;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;

public class Base {

    protected AppiumDriver driver;

    @BeforeTest
    public void setUp()
    {
        String appName = System.getProperty("user.dir") +"/src/test/resources/sauceLabs.apk";

        try
        {
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
        dc.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        dc.setCapability(MobileCapabilityType.DEVICE_NAME,"Pixel 4 XL 30");
        dc.setCapability(MobileCapabilityType.APP, appName);
        dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        dc.setCapability("appPackage","com.saucelabs.mydemoapp.android");
        dc.setCapability("appActivity",".view.activities.SplashActivity");
        URL url = new URL("http://127.0.0.1:4723/wd/hub");
        driver= new AndroidDriver(url,dc);
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }

    }

}
