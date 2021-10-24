package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.components.JobCard;

import static com.codeborne.selenide.Selenide.$;

public class JobPage extends BasePage {
    private final SelenideElement pageWrapper = $(".job-page");
    private final SelenideElement jobCardElement = pageWrapper.$("mat-card.job-card");
    private final SelenideElement commentInput = pageWrapper.$("textarea");
    private final SelenideElement leaveCommentButton = pageWrapper.$("mat-card button");
    private final ElementsCollection comments = pageWrapper.$$(".comments mat-card");

    public JobCard jobCard;

    public JobPage() {
        pageWrapper.shouldBe(Condition.exist);
        jobCard = new JobCard(jobCardElement);
    }

    @Step("Add comment")
    public JobPage addComment(String text) {
        setValue(commentInput, text);
        leaveCommentButton.shouldBe(Condition.enabled).click();
        leaveCommentButton.shouldBe(Condition.disabled);
        return this;
    }

    @Step("Get count comments")
    public int getCountComments() {
        return comments.size();
    }
}
