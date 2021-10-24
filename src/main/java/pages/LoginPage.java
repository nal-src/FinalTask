package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage extends BasePage {
    private final SelenideElement pageWrapper = $(".login-page");
    private final SelenideElement title = pageWrapper.$("h2");
    private final SelenideElement userNameInput = pageWrapper.$("input[formcontrolname ='username']");
    private final SelenideElement passwordInput = pageWrapper.$("input[formcontrolname='password']");
    private final SelenideElement loginButton = pageWrapper.$("button");

    public LoginPage() {
        pageWrapper.shouldBe(Condition.exist);
    }

    @Step("Fill username")
    public LoginPage setUserName(String username) {
        setValue(userNameInput, username);
        return this;
    }

    @Step("Fill password")
    public LoginPage setPassword(String password) {
        setValue(passwordInput, password);
        return this;
    }

    @Step("Click login button")
    public MainPage clickLoginButton() {
        loginButton.shouldBe(Condition.enabled).click();
        return new MainPage();
    }

    @Step("Get title")
    public String getTitle() {
        return title.shouldBe(Condition.visible).innerText();
    }


}
