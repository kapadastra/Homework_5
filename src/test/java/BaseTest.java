import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.FormPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BaseTest {
    private static final Logger logger = LogManager.getLogger(FormPage.class);

    protected WebDriver driver;

    @BeforeEach
    public void setUp(){
        logger.info("Настройка драйвера и открытие браузера");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterEach
    public void tearDown() {
        if (driver!= null) {
            logger.info("Закрытие браузера");
            driver.quit();
        }
    }

    @Test
    public void testPasswordMatch(){
        driver.get("https://otus.home.kartushin.su/form.html");
        FormPage formPage = new FormPage(driver);

        formPage.enterPassword("Password123");
        formPage.enterConfirmPassword("Password123");

        assertTrue(formPage.isPasswordMatching(), "Пароли не совпадают!");


    }
}
