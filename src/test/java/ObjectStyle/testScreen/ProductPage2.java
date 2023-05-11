package ObjectStyle.testScreen;

import ObjectStyle.bs.Base2;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;


public class ProductPage2 {

    Base2 bs;

    @AndroidFindBy(id="productTV")
    @iOSXCUITFindBy(id="")
    public WebElement title;

    @AndroidFindBy(xpath="//android.widget.ImageView[contains(@content-desc,'Sauce')]")
    @iOSXCUITFindBy(id="")
    public List<WebElement> listOfItems;

    public ProductPage2()
    {
        bs = new Base2();
        PageFactory.initElements(new AppiumFieldDecorator(bs.getDriver()),this);
    }

    public String getTitle()
    {
      return (bs.getAttribute(title,"title"));
    }

    public int getCount()
    {
        return (listOfItems.size());
    }

}
