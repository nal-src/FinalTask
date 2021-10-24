package pages.components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.BasePage;
import pages.HomePage;
import pages.ProfilePage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class Header extends BasePage {
    private final SelenideElement componentWrapper = $("mat-toolbar");
    private final SelenideElement userDetailsMenu = componentWrapper.$("button[mattooltip='Profile']");
    private final SelenideElement profileMenuItem =
            $x("//*[contains(@class, 'mat-menu-content')]/button[contains(text(), 'Profile')]");
    private final SelenideElement logoutMenuItem =
            $x("//*[contains(@class, 'mat-menu-content')]/button[contains(text(), 'Logout')]");

    public Header() {
        componentWrapper.should(Condition.exist);
    }

    @Step("Click profile button in header")
    public ProfilePage goToProfile() {
        userDetailsMenu.shouldBe(Condition.visible).shouldBe(Condition.enabled).click();
        profileMenuItem.shouldBe(Condition.visible).shouldBe(Condition.enabled).click();
        return new ProfilePage();
    }

    @Step("Click logout button in header")
    public HomePage logout() {
        userDetailsMenu.shouldBe(Condition.visible).shouldBe(Condition.enabled).click();
        logoutMenuItem.shouldBe(Condition.visible).shouldBe(Condition.enabled).click();
        return new HomePage();
    }
}
