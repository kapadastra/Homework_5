package pages;

import data.LanguageLevel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.Normalizer;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class FormPage extends BasePage{
    private static final Logger logger = LogManager.getLogger(FormPage.class);

    private By usernameField = By.id("username");
    private By emailField = By.id("email");
    private By passwordField = By.id("password");
    private By confirmPasswordField = By.id("confirm_password");
    private By dateOfBirthField = By.id("birthdate");
    private By languageLevelSelect = By.id("language_level");
    private By languageLevel = By.id("language_level");
    private By submitButton = By.cssSelector("input[type='submit']");

    public FormPage(WebDriver driver) {
        super(driver);
    }

    public void enterUsername(String username) {
        try {
            logger.info("Ввод имени пользователя: " + username);
            driver.findElement(usernameField).sendKeys(username);
        } catch (Exception e) {
            logger.error("Ошибка при вводе имени пользователя", e);
        }
    }

    public void enterEmail(String email) {
        try {
            logger.info("Ввод электронной почты: " + email);
            driver.findElement(emailField).sendKeys(email);
        } catch (Exception e) {
            logger.error("Ошибка при вводе электронной почты", e);
        }
    }

    public void enterPassword(String password) {
        try {
            logger.info("Ввод пароля и его подтверждение");
            driver.findElement(passwordField).sendKeys(password);
            driver.findElement(confirmPasswordField).sendKeys(password);
        } catch (Exception e) {
            logger.error("Ошибка при вводе пароля или его подтверждения", e);
        }
    }

    public void enterConfirmPassword(String confirmPassword) {
        driver.findElement(confirmPasswordField).sendKeys(confirmPassword);
    }

    public void selectDateOfBirth(String dob) {
        driver.findElement(dateOfBirthField).sendKeys(dob);
    }

    public void selectLanguageLevel(LanguageLevel level) {
        WebElement dropdown = driver.findElement(languageLevelSelect);
        Select select = new Select(dropdown);
        select.selectByValue(level.getValue());
    }

    public void submitForm() {
        try {
            logger.info("Отправка формы");
            driver.findElement(submitButton).click();
        } catch (Exception e) {
            logger.error("Ошибка при отправке формы", e);
        }
    }


    public boolean isPasswordMatching() {
        String password = driver.findElement(passwordField).getAttribute("value");
        String confirmPassword = driver.findElement(confirmPasswordField).getAttribute("value");
        return password.equals(confirmPassword);
    }

    public void submitFormCheck() {
        if (isPasswordMatching()) {
            driver.findElement(submitButton).click();
        } else {
            System.out.println("Пароли не совпадают.");
        }

    }
}
