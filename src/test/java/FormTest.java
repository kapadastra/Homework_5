import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.FormPage;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FormTest extends BaseTest {
    private static final Logger logger = LogManager.getLogger(FormPage.class);

    @Test
    public void testFormSubmission() {
        driver.get("https://otus.home.kartushin.su/form.html");
        FormPage formPage = new FormPage(driver);

        String username = "TestUser";
        String email = "testuser@example.com";
        String password = "securePassword";
        String birthdate = "01012000";
        String languageLevel = "intermediate";

        formPage.enterUsername(username); // Ввод имя пользователя

        formPage.enterEmail(email); // Ввод майла

        formPage.enterPassword(password); // Ввод пароля

        formPage.selectDateOfBirth(birthdate); // Ввод даты рождения

        formPage.selectLanguageLevel(languageLevel); // Выбор укроня языка

        logger.info("Проверка совпадения паролей перед отправкой формы");
        assertTrue(formPage.isPasswordMatching(), "Пароли не совпадают!");

        formPage.submitForm(); // Отправка формы

        logger.info("Проверка наличия имени пользователя на странице");
        assertTrue(driver.getPageSource().contains(username), "Имя пользователя не отображается на странице");

        logger.info("Проверка наличия электронной почты на странице");
        assertTrue(driver.getPageSource().contains(email), "Электронная почта не отображается на странице");
    }

}
