package ui;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.screenshot;

public class BaseTest {
    @BeforeClass
    public void beforeClass() {
        WebDriverManager.chromedriver().setup();
    }

    protected void openHomePage() {
        Configuration.startMaximized = true;
        open("https://freelance.lsrv.in.ua");
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult result) {
        if (result.getStatus() != ITestResult.SUCCESS) {
            saveScreenshot(screenshot(OutputType.BYTES));
        }
        Selenide.closeWebDriver();
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshot(byte[] screenShot) {
        return screenShot;
    }
}
