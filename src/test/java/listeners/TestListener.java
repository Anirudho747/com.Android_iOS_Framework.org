package listeners;

import ObjectStyle.bs.Base2;
import com.aventstack.extentreports.Status;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class TestListener implements ITestListener {

    Base2 bs;

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
        bs = new Base2();
        File file = bs.getDriver().getScreenshotAs(OutputType.FILE);

        Map<String,String> params = new HashMap<String,String>();
        params = result.getTestContext().getCurrentXmlTest().getAllParameters();

        String imagePath = "Screenshots" + File.separator + params.get("platformName") + "_" + params.get("platformVersion") + "_" + params.get("deviceName") + File.separator + bs.getDateTime() + File.separator + result.getTestClass().getRealClass().getSimpleName() + File.separator + result.getName() +".png";


            FileUtils.copyFile(file, new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Base2.getTest().log(Status.FAIL, "Test Failed");
    }

    @Override
    public void onTestStart(ITestResult result) {
        Base2.startTest(result.getName(), result.getMethod().getDescription())
                .assignAuthor("Ani 747");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Base2.getTest().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        Base2.getTest().log(Status.SKIP, "Test Skipped");

    }

    @Override
    public void onFinish(ITestContext context) {
        Base2.getReporter().flush();
    }

}
