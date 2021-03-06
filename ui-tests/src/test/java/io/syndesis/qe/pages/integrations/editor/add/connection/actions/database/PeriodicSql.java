package io.syndesis.qe.pages.integrations.editor.add.connection.actions.database;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;

import com.codeborne.selenide.SelenideElement;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by sveres on 12/6/17.
 */
@Slf4j
public class PeriodicSql extends Sql {

    private static final class Element {
        public static final By INPUT_QUERY = By.cssSelector("input[name='query']");
        public static final By INPUT_PERIOD = By.cssSelector("input[id='schedulerExpression']");
        public static final By SELECT_PERIOD = By.cssSelector("select[id='select-schedulerExpression']");
        public static final By TITLE = By.cssSelector("h3[innertext='Periodic SQL Invocation']");
    }

    @Override
    public void fillSqlInput(String query) {
        log.debug("filling sql query: {}", query);
        SelenideElement element = $(Element.INPUT_QUERY);
        this.fillInput(element, query);
    }

    @Override
    public boolean validate() {
        return this.getRootElement().find(Element.TITLE).is(visible);
    }

    public void fillSQLperiod(String period) {
        log.debug("filling sql period: {}", period);
        SelenideElement element = $(Element.INPUT_PERIOD).shouldBe(visible);
        this.fillInput(element, period);
    }

    public void selectSQLperiodUnits(String timeUnits) {
        log.debug("selecting sql period units: {}", timeUnits);
        SelenideElement selectElement = $(Element.SELECT_PERIOD).shouldBe(visible);
        selectElement.selectOption(timeUnits);
    }

}
