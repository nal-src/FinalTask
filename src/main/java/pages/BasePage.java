package pages;

import com.codeborne.selenide.SelenideElement;

public class BasePage {
    protected void setValue(SelenideElement element, String value) {
        element.clear();
        element.sendKeys(value);
    }
}
