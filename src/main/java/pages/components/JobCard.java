package pages.components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.BasePage;
import pages.JobPage;

import static com.codeborne.selenide.Selenide.switchTo;


public class JobCard extends BasePage {
    private final SelenideElement title;
    private final SelenideElement description;
    private final SelenideElement price;
    private final SelenideElement jobComments;
    private final SelenideElement removeJobButton;
    private final SelenideElement viewDetailsButton;

    public JobCard(SelenideElement parent) {
        title = parent.$("mat-card-title");
        description = parent.$("mat-card-content");
        price = parent.$("mat-card-subtitle.price");
        jobComments = parent.$("mat-card-subtitle[align='end']");
        removeJobButton = parent.$("button.mat-warn");
        viewDetailsButton = parent.$("button");
    }

    @Step("Get job title")
    public String getTitle() {
        return title.text();
    }

    @Step("Get job description")
    public String getDescription() {
        return description.text();
    }

    @Step("Get job price")
    public int getPrice() {
        String text = price.text();
        String[] parts = text.split("Price");
        String price = parts[parts.length -1].trim();
        return Integer.parseInt(price);
    }

    @Step("Get job count comments")
    public int getCountComments() {
        String text = jobComments.text();
        String[] parts = text.split(":");
        String comments = parts[parts.length -1].trim();
        return Integer.parseInt(comments);
    }

    @Step("Click view details button")
    public JobPage clickViewDetailsButton() {
        viewDetailsButton.shouldBe(Condition.exist).shouldBe(Condition.enabled).click();
        return new JobPage();
    }

    @Step("Click remove job button")
    public void clickRemoveJobButton() {
        viewDetailsButton.shouldBe(Condition.exist).shouldBe(Condition.enabled).click();
        switchTo().alert().accept();
    }
}
