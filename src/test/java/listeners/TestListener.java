package listeners;

import ObjectStyle.bs.Base2;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.Assert;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.TestUtils;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class TestListener implements ITestListener {

    public void onTestFailure(ITestResult result) {
        if (result.getThrowable()!=null)
        {
            //Perform the action needed to be done when any method fails
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            result.getThrowable().printStackTrace(pw);
            System.out.println(sw.toString());
        }
        try {
        Base2 bs = new Base2();
        File file = bs.getDriver().getScreenshotAs(OutputType.FILE);

        Map<String,String> params = new HashMap<String,String>();
        params = result.getTestContext().getCurrentXmlTest().getAllParameters();

        String imagePath = "Screenshots" + File.separator + params.get("platformName") + "_" + params.get("platformVersion") + "_" + params.get("deviceName") + File.separator + bs.getDateTime() + File.separator + result.getTestClass().getRealClass().getSimpleName() + File.separator + result.getName() +".png";


            FileUtils.copyFile(file, new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
