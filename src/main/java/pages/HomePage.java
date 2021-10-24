package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class HomePage extends BasePage {
    private final SelenideElement pageWrapper = $(".home-page");
    private final SelenideElement loginLink = pageWrapper.$(By.linkText("Log in"));
    private final SelenideElement registerLink = pageWrapper.$(By.linkText("Create account"));

    public HomePage() {
        pageWrapper.shouldBe(Condition.exist);
    }

    @Step("Go to Register page")
    public RegisterPage goToRegisterPage() {
        registerLink.click();
        return new RegisterPage();
    }

    @Step("Go to Login page")
    public LoginPage goToLoginPage() {
        loginLink.click();
        return new LoginPage();
    }
}
