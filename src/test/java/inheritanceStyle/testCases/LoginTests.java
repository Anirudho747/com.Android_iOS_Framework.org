package inheritanceStyle.testCases;

import inheritanceStyle.bs.Base;
import org.testng.Assert;
import org.testng.annotations.*;
import inheritanceStyle.testScreen.LandingPage;
import inheritanceStyle.testScreen.ProductPage;

import java.lang.reflect.Method;

public class LoginTests extends Base {

    LandingPage lp;
    ProductPage pp;

    @BeforeClass
    public void beforeClass()
    {
        lp = new LandingPage();
        pp = new ProductPage();
    }

    @BeforeMethod
    public void beforeMethod(Method m)
    {
        System.out.println("+++++++++ "+m.getName()+"  ++++++++++++");
    }

    @AfterClass
    public void afterClass()
    {

    }

    @AfterMethod
    public void afterMethod()
    {

    }

    @Test
    public void loginSuccess()
    {
        lp.clickMenu();
        lp.clickLogin();
        lp.enterName("bod@example.com");
        lp.enterPsswrd("10203040");
        lp.tapLogin();
        Assert.assertEquals(pp.getTitle(),"Products");
    }

}
