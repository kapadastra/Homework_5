import data.LanguageLevel;
import factory.WebDriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.FormPage;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FormTest{
    private static final Logger logger = LogManager.getLogger(FormPage.class);

    protected WebDriver driver;

    @BeforeEach
    public void setUp(){
        logger.info("Настройка драйвера и открытие браузера");
        String browser = System.getProperty("browser", "chrome");
        //String baseUrl = System.getProperty("baseUrl", "https://otus.home.kartushin.su/form.html");
        String mode = System.getProperty("mode", "headless");
        driver = WebDriverFactory.getBrowser(browser, mode);
        logger.info("Драйвер настроен.");
        //driver.get(baseUrl);
        //logger.info("Перешли на страницу: " + baseUrl);
    }

    @AfterEach
    public void tearDown() {
        if (driver!= null) {
            logger.info("Закрытие браузера");
            driver.quit();
        }
    }

    @Test
    public void testFormSubmission() {
        String baseUrl = System.getProperty("baseUrl", "https://otus.home.kartushin.su/form.html");
        FormPage formPage = new FormPage(driver);
        formPage.open(baseUrl);

        String username = "TestUser";
        String email = "testuser@example.com";
        String password = System.getProperty("password", "123456789");
        String birthdate = "01012000";
        LanguageLevel languageLevel = LanguageLevel.intermediate;


        formPage.enterUsername(username); // Ввод имя пользователя

        formPage.enterEmail(email); // Ввод майла

        formPage.enterPassword(password); // Ввод пароля

        formPage.selectDateOfBirth(birthdate); // Ввод даты рождения

        formPage.selectLanguageLevel(languageLevel); // Выбор уровня языка

        logger.info("Проверка совпадения паролей перед отправкой формы");
        assertTrue(formPage.isPasswordMatching(), "Пароли не совпадают!");

        formPage.submitForm(); // Отправка формы

        logger.info("Проверка наличия имени пользователя на странице");
        assertTrue(driver.getPageSource().contains(username), "Имя пользователя не отображается на странице");

        logger.info("Проверка наличия электронной почты на странице");
        assertTrue(driver.getPageSource().contains(email), "Электронная почта не отображается на странице");
    }

}
