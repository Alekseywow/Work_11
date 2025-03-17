package qa.guru.allure;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.attachment;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class AttachmentsTests {

    @BeforeAll
    static void setUp() {

        Configuration.pageLoadStrategy = "eager";
    }

    private static final String REPOSITORY = "eroshenkoam/allure-example"; // REPOSITORY: строка, содержащая имя репозитория на GitHub, который будет использоваться в тесте. В данном случае - eroshenkoam/allure-example.
    private static final int ISSSUE = 80; // ISSSUE: целочисленная переменная, содержащая номер issue (задачи/проблемы) в репозитории, которая будет проверяться. Тут это число 80.

    @Test
    public void testLAmbdaAttachments() {
        SelenideLogger.addListener("allure", new AllureSelenide()); // Здесь добавляется слушатель для логирования шагов Selenide в Allure. Это позволит автоматически сохранять шаги теста в отчетах Allure.


        step("Открываем главную страницу", () -> {  // Каждый шаг оформлен с помощью метода step, что делает код более читабельным и структурированным. Каждый из шагов описывает действие, которое выполняется в тесте.
            open("https://github.com/");
            attachment("Source", webdriver().driver().source()); // Эта строка создает вложение (attachment) в отчет о тесте.функция, которая регистрирует вложение. Это может быть часть фрейма для отчетности, например, Allure.это название вложения; оно используется в отчете для обозначения типа прикрепленного объекта.этот вызов получает исходный код текущей веб-страницы, используя драйвер веба. возвращает объект, представляющий текущий веб-драйвер.возвращает сам драйвер.возможно получает HTML-исходный код страницы.
        });

    }

    @Test
    public void testAnnotateAttachments() {
        SelenideLogger.addListener("allure", new AllureSelenide()); // Здесь добавляется слушатель для логирования шагов Selenide в Allure. Это позволит автоматически сохранять шаги теста в отчетах Allure.
        WebSteps webSteps = new WebSteps();

        webSteps.openMainPage();
        webSteps.taleScreenShot();
    }

}
