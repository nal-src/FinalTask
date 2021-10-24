package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import entity.Job;
import exeptions.JobNotFoundException;
import io.qameta.allure.Step;
import pages.components.EditUserProfileModal;
import pages.components.JobAddForm;
import pages.components.JobCard;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.$;

public class ProfilePage extends BasePage {
    private final SelenideElement pageWrapper = $(".user-profile");
    private final ElementsCollection allButtons = pageWrapper.$$("button");
    private final SelenideElement nameInfo = pageWrapper.$("h3");
    private final SelenideElement allMyJobsWrapper = pageWrapper.$("app-my-jobs");
    private final ElementsCollection allMyJobs = allMyJobsWrapper.$$("mat-card");

    public ProfilePage() {
        pageWrapper.shouldBe(Condition.exist);
    }

    @Step("Open edit profile modal")
    public EditUserProfileModal editInfo() {
        findButtonByText("Edit Info").shouldBe(Condition.enabled).click();
        return new EditUserProfileModal();
    }

    @Step("Open add job form")
    public JobAddForm addJob() {
        findButtonByText("Add job").shouldBe(Condition.enabled).click();
        return new JobAddForm();
    }

    @Step("Get user name and lastname")
    public String getNameInfo() {
        return nameInfo.text();
    }

    @Step("Check is Present job")
    public boolean isPresentJob(Job job) {
        allMyJobsWrapper.shouldBe(Condition.exist);
        if (allMyJobs.size() == 0) {
            return false;
        }
        Stream<SelenideElement> result = allMyJobs.stream().filter(element -> {
            JobCard jobCard = new JobCard(element);
            return jobCard.getTitle().equals(job.getTitle())
                    && jobCard.getDescription().equals(job.getDescription())
                    && jobCard.getPrice() == job.getPrice();
        });
        return result.count() > 0;
    }

    @Step("Get job card")
    public JobCard getJob(Job job) {
        List<SelenideElement> result = allMyJobs.stream().filter(element -> {
            JobCard jobCard = new JobCard(element);
            return jobCard.getTitle().equals(job.getTitle())
                    && jobCard.getDescription().equals(job.getDescription())
                    && jobCard.getPrice() == job.getPrice();
        }).collect(Collectors.toList());

        if(result.size() == 0) {
            throw new JobNotFoundException("Not find job: " + job);
        }
        return new JobCard(result.get(0));
    }

    private SelenideElement findButtonByText(String text) {
        return allButtons.filter(Condition.text(text)).first();
    }
}
