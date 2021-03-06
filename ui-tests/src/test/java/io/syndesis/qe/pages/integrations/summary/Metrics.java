package io.syndesis.qe.pages.integrations.summary;

import com.codeborne.selenide.SelenideElement;
import io.syndesis.qe.pages.SyndesisPageObject;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

@Slf4j
public class Metrics extends SyndesisPageObject {

    private static final class Element {
        public static final By ROOT = By.className("integration-detail__outlet");
        public static final By ROOT_TOTAL_ERROR = By.xpath("//div[@class='card-pf card-pf-accented card-pf-aggregate-status' and contains(.,'Total Errors')]");
        public static final By ROOT_LAST_POCESSED = By.xpath("//div[@class='card-pf card-pf-accented card-pf-aggregate-status' and contains(.,'Last Processed')]");
        public static final By ROOT_TOTAL_MESSAGES = By.xpath("//div[@class='card-pf card-pf-accented card-pf-aggregate-status' and contains(.,'Total Messages')]");
        //div has two h3 elements but contains method wants only one as first argument so the class have to be specified
        public static final By ROOT_UPTIME = By.xpath("//div[@class='card-pf card-pf-accented card-pf-aggregate-status' and contains(.,'Uptime')]");
    }

    @Override
    public SelenideElement getRootElement() {
        return $(Element.ROOT).shouldBe(visible);
    }

    @Override
    public boolean validate() {
        return getRootElement().is(visible);
    }

    public int getTotalErrors() {
        SelenideElement numberOfErrors = $(Element.ROOT_TOTAL_ERROR).shouldBe(visible)
                .find(By.xpath("//span[@class='card-pf-aggregate-status-notification']"));
        return Integer.parseInt(numberOfErrors.getText());
    }

    public String getLastProcessed() {
        SelenideElement lastProcessed = $(Element.ROOT_LAST_POCESSED).shouldBe(visible)
                .find(By.xpath(".//span[@class='card-pf-aggregate-status-notification']"));
        return lastProcessed.getText();
    }

    public int getNumberOfValidMessages() {
        SelenideElement numberOfValidMessages = $(Element.ROOT_TOTAL_MESSAGES).shouldBe(visible)
                .find(By.xpath(".//span[@class='card-pf-aggregate-status-notification' and span[@class='pficon pficon-ok']]"));
        return Integer.parseInt(numberOfValidMessages.getText());
    }

    public int getNumberOfErrorMessages() {
        SelenideElement numberOfMessages = $(Element.ROOT_TOTAL_MESSAGES).shouldBe(visible)
                .find(By.xpath(".//span[@class='card-pf-aggregate-status-notification' and span[@class='pficon pficon-error-circle-o']]"));
        return Integer.parseInt(numberOfMessages.getText());
    }

    public int getNumberOfTotalMessages() {
        SelenideElement numberOfTotalMessages = $(Element.ROOT_TOTAL_MESSAGES).shouldBe(visible)
                .find(By.xpath(".//span[@class='card-pf-aggregate-status-count']"));
        return Integer.parseInt(numberOfTotalMessages.getText());
    }

    public String getUpTime() {
        SelenideElement uptime = $(Element.ROOT_UPTIME).shouldBe(visible)
                .find(By.xpath(".//h4[@class='metrics__uptime-legend']"));
        return uptime.getText();
    }

    public String getStartTime() {
        SelenideElement date = $(Element.ROOT_UPTIME).shouldBe(visible)
                .find(By.xpath(".//h3[@class='metrics__uptime-header metrics__uptime-kickoff']"));
        return date.getText();
    }

}
