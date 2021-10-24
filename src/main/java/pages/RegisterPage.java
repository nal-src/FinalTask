package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import entity.User;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class RegisterPage extends BasePage {
    private final SelenideElement pageWrapper = $(".register-page");
    private final SelenideElement userNameInput = pageWrapper.$("input[formcontrolname ='username']");
    private final SelenideElement nameInput = pageWrapper.$("input[formcontrolname='name']");
    private final SelenideElement lastNameInput = pageWrapper.$("input[formcontrolname='lastname']");
    private final SelenideElement passwordInput = pageWrapper.$("input[formcontrolname='password']");
    private final SelenideElement confirmPasswordInput = pageWrapper.$("input[formcontrolname='confirmPassword']");
    private final SelenideElement registerButton = pageWrapper.$("button");

    public RegisterPage() {
        pageWrapper.shouldBe(Condition.exist);
    }

    @Step("Fill username")
    public RegisterPage setUserName(String username) {
        setValue(userNameInput, username);
        return this;
    }

    @Step("Fill name")
    public RegisterPage setName(String name) {
        setValue(nameInput, name);
        return this;
    }

    @Step("Fill lastname")
    public RegisterPage setLastName(String lastName) {
        setValue(lastNameInput, lastName);
        return this;
    }

    @Step("Fill password")
    public RegisterPage setPassword(String password) {
        setValue(passwordInput, password);
        return this;
    }

    @Step("Fill confirm password")
    public RegisterPage confirmPassword(String confirmPassword) {
        setValue(confirmPasswordInput, confirmPassword);
        return this;
    }

    @Step("Click register button")
    public LoginPage clickRegisterButton() {
        registerButton.shouldBe(Condition.enabled).click();
        return new LoginPage();
    }
}
