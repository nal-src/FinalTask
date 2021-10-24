package pages.components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.BasePage;
import pages.ProfilePage;

import static com.codeborne.selenide.Selenide.$;

public class EditUserProfileModal extends BasePage {
    private final SelenideElement componentWrapper = $("mat-dialog-container");
    private final SelenideElement nameInput = componentWrapper.$("input[formcontrolname='name']");
    private final SelenideElement lastNameInput = componentWrapper.$("input[formcontrolname='lastname']");
    private final ElementsCollection allButtons = componentWrapper.$$("button");

    public EditUserProfileModal() {
        componentWrapper.shouldBe(Condition.exist);
    }

    @Step("Fill name in user profile modal")
    public EditUserProfileModal setName(String name) {
        setValue(nameInput, name);
        return this;
    }

    @Step("Fill lastname in user profile modal")
    public EditUserProfileModal setLastName(String lastName) {
        setValue(lastNameInput, lastName);
        return this;
    }

    @Step("Click update profile button")
    public ProfilePage update() {
        findButtonByText("Update").shouldBe(Condition.enabled).click();
        return new ProfilePage();
    }

    private SelenideElement findButtonByText(String text) {
        return allButtons.filter(Condition.text(text)).first();
    }
}
