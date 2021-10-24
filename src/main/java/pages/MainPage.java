package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import entity.Job;
import exeptions.JobNotFoundException;
import io.qameta.allure.Step;
import pages.components.Header;
import pages.components.JobCard;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.$;

public class MainPage extends BasePage {
    public final String relativeUrlPath = "main";
    private final SelenideElement pageWrapper = $(".index-page");
    private final ElementsCollection jobs = pageWrapper.$$("mat-card");

    public Header header;

    public MainPage() {
        pageWrapper.shouldBe(Condition.exist);
        header = new Header();
    }

    @Step("Go to Job details page")
    public JobPage viewJobDetails(Job job) {
        List<SelenideElement> result = jobs.stream().filter(element -> {
            JobCard jobCard = new JobCard(element);
            return jobCard.getTitle().equals(job.getTitle())
                    && jobCard.getDescription().equals(job.getDescription())
                    && jobCard.getPrice() == job.getPrice();
        }).collect(Collectors.toList());

        if(result.size() == 0) {
            throw new JobNotFoundException("Not find job: " + job);
        }
        return new JobCard(result.get(0)).clickViewDetailsButton();
    }

}
