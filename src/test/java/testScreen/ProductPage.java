package testScreen;

import bs.Base;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AndroidFindBySet;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

import java.util.List;


public class ProductPage extends Base{

    @AndroidFindBy(id="productTV")
    @iOSXCUITFindBy(id="")
    public WebElement title;

    @AndroidFindBy(xpath="//android.widget.ImageView[contains(@content-desc,'Sauce')]")
    @iOSXCUITFindBy(id="")
    public List<WebElement> listOfItems;

    public String getTitle()
    {
      return (title.getText());
    }

    public int getCount()
    {
        return (listOfItems.size());
    }

}
