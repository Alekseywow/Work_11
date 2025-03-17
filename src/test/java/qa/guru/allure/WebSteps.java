package qa.guru.allure;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class WebSteps {

    @Step("Открываем главную страницу")
    public void openMainPage() {
        open("https://github.com/");
    }




    @Step("Ищем репозиторий {repo}")
    public void searchForRepository(String repo) {
        $(".header-search-button").click();
        $("#query-builder-test").setValue(repo).pressEnter();
    }

    @Step("Кликаем по ссылке репозитория {repo}")
    public void clickRepositoryLink(String repo) {
        $(linkText(repo)).click();
    }

    @Step("Открывает там ISSUES")
    public void openIssuesTab() {
        $("#issues-tab").click();
    }

    @Step("Проверяем наличие ISSUES c номеро {issues}")
    public void shouldSeeIssueWithNumber(int issues) {
        $(withText("#" + issues)).should(exist);

    }

    @Attachment(value = "Screenshot", type = "image/png", fileExtension = "png") // Эта аннотация используется для добавления вложений к отчету о выполнении теста. В данном случае она добавляет скриншот.value = "Screenshot": задает название вложения, которое будет видно в отчете.type = "image/png": указывает MIME-тип вложения. В данном случае это PNG-изображение.fileExtension = "png": указывает расширение файла для вложения.
    public byte[] taleScreenShot() {  //Метод помечен как public, что означает, что он может быть вызван из других классов. Возвращаемый тип метода — byte[], что позволяет возвращать данные изображения в виде массива байт.
        return ((TakesScreenshot)WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES); // Этот вызов извлекает текущий экземпляр драйвера веб-браузера, который используется для автоматизации тестирования (например, Selenium WebDriver). Этот участок кода выполняет приведение типа, указывая, что текущий драйвер может делать скриншоты. Вызывает метод getScreenshotAs для захвата скриншота и возвращает его как массив байт (в формате PNG), который затем будет добавлен в отчет благодаря аннотации @Attachment.
    }

}
