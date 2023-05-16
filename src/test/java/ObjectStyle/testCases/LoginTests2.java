package ObjectStyle.testCases;

import ObjectStyle.bs.Base2;
import ObjectStyle.testScreen.LandingPage2;
import ObjectStyle.testScreen.ProductPage2;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.*;
import utils.TestData;

import java.lang.reflect.Method;

public class LoginTests2 {

    LandingPage2 lp;
    ProductPage2 pp;
    Base2 bs;
    TestData td;

    @BeforeSuite
    public void fireUp()
    {
        bs = new Base2();
  //      bs.startAppiumServer();
    }


    @Parameters({"emulator","platformName","deviceName"})
    @BeforeClass
    public void beforeClass(String emulator,String platformName,String deviceName)
    {
        bs.initialiseDriver(emulator,platformName,deviceName);
        lp = new LandingPage2();
        pp = new ProductPage2();
        td = new TestData();
    }

    @BeforeMethod
    public void beforeMethod(Method m)
    {
        System.out.println("+++++++++ "+m.getName()+"  ++++++++++++");
    }

    @AfterClass
    public void afterClass()
    {
        bs.quitDriver();
    }

    @AfterMethod
    public void afterMethod()
    {

    }

    @Test(priority = 2)
    public void loginSuccess()
    {
        lp.clickMenu();
        lp.clickLogin();
        lp.enterName(td.userName[0]);
        lp.enterPsswrd(td.psswrd[0]);
        lp.tapLogin();
        Assert.assertEquals(pp.getTitle(), "Products");
    }

    @Test(priority = 0)
    public void incorrectUserName()
    {
        lp.clickMenu();
        lp.clickLogin();
        lp.enterName(td.userName[1]);
        lp.enterPsswrd(td.psswrd[0]);
        lp.tapLogin();
        Assert.assertEquals(pp.getTitle(), "Products");
    }

    @Test(priority = 1)
    public void incorrectPassword()
    {
        lp.clickMenu();
        lp.clickLogin();
        lp.enterName(td.userName[0]);
        lp.enterPsswrd(td.psswrd[1]);
        lp.tapLogin();
        Assert.assertEquals(pp.getTitle(), "Products");
    }



}
