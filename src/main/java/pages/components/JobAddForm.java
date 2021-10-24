package pages.components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.BasePage;
import pages.ProfilePage;

import static com.codeborne.selenide.Selenide.$;

public class JobAddForm extends BasePage {
    private final SelenideElement componentWrapper = $("app-add-job");
    private final SelenideElement titleInput = componentWrapper.$("input[formcontrolname='title']");
    private final SelenideElement descriptionInput = componentWrapper.$("textarea[formcontrolname='description']");
    private final SelenideElement priceInput = componentWrapper.$("input[formcontrolname='price']");
    private final SelenideElement createJobButton = componentWrapper.$("form button");

    public JobAddForm() {
        componentWrapper.shouldBe(Condition.exist);
    }

    @Step("Fill job title")
    public JobAddForm setTitle(String text) {
        setValue(titleInput, text);
        return this;
    }

    @Step("Fill job description")
    public JobAddForm setDescription(String text) {
        setValue(descriptionInput, text);
        return this;
    }

    @Step("Fill job price")
    public JobAddForm setPrice(int price) {
        setValue(priceInput, String.valueOf(price));
        return this;
    }

    @Step("Click create job button")
    public ProfilePage create() {
        createJobButton.shouldBe(Condition.enabled).click();
        return new ProfilePage();
    }

}
