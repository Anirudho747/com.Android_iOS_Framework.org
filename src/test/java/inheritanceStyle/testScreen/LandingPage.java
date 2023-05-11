package inheritanceStyle.testScreen;

import inheritanceStyle.bs.Base;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class LandingPage extends Base {

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

    public void clickMenu()
    {
        click(menu);
    }

    public void clickLogin()
    {
        logIn.click();
    }

    public void enterName(String name)
    {
        sendKeys(uName,name);
    }

    public void enterPsswrd(String passwrd)
    {
        sendKeys(psswrd,passwrd);
    }

    public void tapLogin()
    {
        click(logInButton);
    }
}
