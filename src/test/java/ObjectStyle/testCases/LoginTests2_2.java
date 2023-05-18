package ObjectStyle.testCases;

import ObjectStyle.bs.Base2;
import ObjectStyle.testScreen.LandingPage2;
import ObjectStyle.testScreen.ProductPage2;
import org.testng.Assert;
import org.testng.annotations.*;
import utils.TestData;

import java.lang.reflect.Method;

public class LoginTests2_2 {

    LandingPage2 lp;
    ProductPage2 pp;
    Base2 bs;
    TestData td;
//    ExtentReports extent;
//    ExtentSparkReporter sparkAll ;


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
//        bs.flushExtent();
        bs.quitDriver();
    }

    @AfterMethod
    public void afterMethod(Method m)
    {

    }

    @Test(priority = 2)
    public void loginSuccess()
    {
//        bs.extent.createTest("loginSuccess")
//                .log(Status.PASS, "This is a logging event for MyFirstTest, and it passed!");


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
