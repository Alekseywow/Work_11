package qa.guru.allure;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.By.linkText;

public class SelenideTest {

    @BeforeAll
    static void setUp() {
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    public void testIssueSearch() {
        SelenideLogger.addListener("allure", new AllureSelenide());

     open("https://github.com/");
     $(".header-search-button").click();
     $("#query-builder-test").setValue("eroshenkoam/allure-example").pressEnter();

     $(linkText("eroshenkoam/allure-example")).click();
     $("#issues-tab").click();
     $(withText("80")).should(exist);

    }
}
