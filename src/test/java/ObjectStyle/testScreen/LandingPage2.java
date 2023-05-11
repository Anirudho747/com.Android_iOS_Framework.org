package ObjectStyle.testScreen;

import ObjectStyle.bs.Base2;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class LandingPage2 {

    Base2 bs;

    @AndroidFindBy(accessibility = "View menu")
    @iOSXCUITFindBy(id="l")
    public WebElement menu;

    @AndroidFindBy(xpath="//android.widget.TextView[@text='Log In']")
    @iOSXCUITFindBy(id="l")
    public WebElement logIn;

    @AndroidFindBy(id="nameET")
    @iOSXCUITFindBy(id="l")
    public WebElement uName;

    @AndroidFindBy(id="passwordET")
    @iOSXCUITFindBy(id="l")
    public WebElement psswrd;

    @AndroidFindBy(id="loginBtn")
    @iOSXCUITFindBy(id="l")
    public WebElement logInButton;

    public LandingPage2()
    {
        bs = new Base2();
        PageFactory.initElements(new AppiumFieldDecorator(bs.getDriver()),this);
    }

    public void clickMenu()
    {
        bs.click(menu);
    }

    public void clickLogin()
    {
        bs.click(logIn);
    }

    public void enterName(String name)
    {
        bs.sendKeys(uName,name);
    }

    public void enterPsswrd(String passwrd)
    {
        bs.sendKeys(psswrd,passwrd);
    }

    public void tapLogin()
    {
        bs.click(logInButton);
    }
}
