package qa.guru.allure;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Allure;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class StepsTests {

    @BeforeAll
    static void setUp() {

        Configuration.pageLoadStrategy = "eager";
    }

    private static final String REPOSITORY = "eroshenkoam/allure-example"; // REPOSITORY: строка, содержащая имя репозитория на GitHub, который будет использоваться в тесте. В данном случае - eroshenkoam/allure-example.
    private static final int ISSSUE = 80; // ISSSUE: целочисленная переменная, содержащая номер issue (задачи/проблемы) в репозитории, которая будет проверяться. Тут это число 80.

    @Test
    public void testLAmbdaStep() {
        SelenideLogger.addListener("allure", new AllureSelenide()); // Здесь добавляется слушатель для логирования шагов Selenide в Allure. Это позволит автоматически сохранять шаги теста в отчетах Allure.


//        step("Строка", new Allure.ThrowableRunnableVoid() { // Метод, который запускает шаг теста. Обычно, эта функция принимает два параметра: описание шага (в данном случае "Строка") и функциональный интерфейс, который будет выполнен, когда тест доходит до этого шага. Здесь создается анонимный класс, реализующий интерфейс ThrowableRunnableVoid. Этот интерфейс обычно предполагает выполнение какого-то кода, который может бросать исключения (Throwable).
//            @Override
//            public void run() throws Throwable { // В аннотации @Override реализуется метод run(), который должен содержать код, выполняемый во время выполнения шага теста. В вашем примере метод run() пуст, что означает, что в рамках этого шага ничего не будет выполняться. Вы можете добавить логику тестирования сюда, например, взаимодействие с веб-элементами, проверки, логирование и др.
//
//            }
//        });

        step("Открываем главную страницу", () -> {  // Каждый шаг оформлен с помощью метода step, что делает код более читабельным и структурированным. Каждый из шагов описывает действие, которое выполняется в тесте.
            open("https://github.com/");
        });

        step("Ищем репозиторий" + REPOSITORY , () -> {
            $(".header-search-button").click();
            $("#query-builder-test").setValue(REPOSITORY).pressEnter();
        });


        step("Кликаем по ссылке репозитория " + REPOSITORY, () -> {
            $(linkText(REPOSITORY)).click();
        });

        step("Открывает там ISSUES", () -> {
            $("#issues-tab").click();
        });

        step("Проверяем наличие ISSUES c номеро" + ISSSUE, () ->{
            $(withText("#" + ISSSUE)).should(exist);
        });
    }

    @Test
    public void testAnnotateStep() {
        SelenideLogger.addListener("allure", new AllureSelenide()); // Здесь добавляется слушатель для логирования шагов Selenide в Allure. Это позволит автоматически сохранять шаги теста в отчетах Allure.
        WebSteps webSteps = new WebSteps();

        webSteps.openMainPage();
        webSteps.searchForRepository(REPOSITORY);
        webSteps.clickRepositoryLink(REPOSITORY);
        webSteps.openIssuesTab();
        webSteps.shouldSeeIssueWithNumber(ISSSUE);
    }

}
