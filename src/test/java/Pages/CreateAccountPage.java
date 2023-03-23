package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.RegistrationErrorMsgKeys;
import java.util.HashMap;


public class CreateAccountPage extends BasePage {

    public CreateAccountPage(WebDriver driver) {
        super(driver);
    }


/*  Java doesn't support direct var -> lambda assigment (lazy locators), thus noSuchElement ex.
What's the proper way to create wrappers for finds with build in fluentWaits? @FindBy is the way to go?
*/

//    private WebElement inputFirstName = FindElement(By.id("firstname"));
//    private WebElement inputLastName = FindElement(By.id("lastname"));
//    private WebElement checkboxNewsletter = FindElement(By.id("is_subscribed"));
//    private WebElement inputEmail = FindElement(By.id("email_address"));
//    private WebElement inputPassword = FindElement(By.id("password"));
//    private WebElement inputConfirmPassword = FindElement(By.id("password-confirmation"));
//    private WebElement btnCreateAccount = FindElement(By.cssSelector(".action.submit.primary"));
//    private WebElement errorInvalidEmail = FindElement(By.id("email_address-error"));
//    private WebElement errorFirstName = FindElement(By.id("firstname-error"));
//    private WebElement errorLastName = FindElement(By.id("lastname-error"));
//    private WebElement errorPassword = FindElement(By.id("password-error"));
//    private WebElement errorConfirmPassword = FindElement(By.id("password-confirmation-error"));
    @FindBy(id = "firstname")
    private WebElement inputFirstName;
    @FindBy(id = "lastname")
    private WebElement inputLastName;
    @FindBy(id = "is_subscribed")
    private WebElement checkboxNewsletter;
    @FindBy(id = "email_address")
    private WebElement inputEmail;
    @FindBy(id = "password")
    private WebElement inputPassword;
    @FindBy(id = "password-confirmation")
    private WebElement inputConfirmPassword;
//    @FindBy(css = ".action.submit.primary")
    @FindBy(xpath = "//*[@class=\"action submit primary\"]//..")
    private WebElement btnCreateAccount;
    @FindBy(id = "email_address-error")
    private WebElement errorInvalidEmail;
    @FindBy(id = "firstname-error")
    private WebElement errorFirstName;
    @FindBy(id = "lastname-error")
    private WebElement errorLastName;
    @FindBy(id = "password-error")
    private WebElement errorPassword;
    @FindBy(id = "password-confirmation-error")
    private WebElement errorConfirmPassword;

    private final HashMap<String, String> expectedErrorMessages = new HashMap<>(){{
        put(String.valueOf(RegistrationErrorMsgKeys.missingFirstName), "This is a required field.");
        put(String.valueOf(RegistrationErrorMsgKeys.missingLastName), "This is a required field.");
        put(String.valueOf(RegistrationErrorMsgKeys.invalidEmail), "Please enter a valid email address (Ex: johndoe@domain.com).");
        put(String.valueOf(RegistrationErrorMsgKeys.missingEmail), "This is a required field.");
        put(String.valueOf(RegistrationErrorMsgKeys.missingPassword), "This is a required field.");
        put(String.valueOf(RegistrationErrorMsgKeys.shortPassword), "Minimum length of this field must be equal or greater than 8 symbols. Leading and trailing spaces will be ignored.");
        put(String.valueOf(RegistrationErrorMsgKeys.weakPassword), "Minimum of different classes of characters in password is 3. Classes of characters: Lower Case, Upper Case, Digits, Special Characters.");
        put(String.valueOf(RegistrationErrorMsgKeys.passwordNotMatching), "Please enter the same value again.");
        put(String.valueOf(RegistrationErrorMsgKeys.missingConfirmPassword), "This is a required field.");
    }};
    
    public void enterFirstName(String firstName) {
        inputFirstName.sendKeys(firstName);
        inputFirstName.click();
    }

    public void enterLastName(String lastName) {
        inputLastName.sendKeys(lastName);
        inputLastName.click();
    }

    public void enterEmail(String email) {
        inputEmail.sendKeys(email);
        inputEmail.click();
    }

    public void enterPassword(String password) {
        inputPassword.sendKeys(password);
        inputPassword.click();
    }

    public void enterConfirmPassword(String confirmPassword) {
        inputConfirmPassword.sendKeys(confirmPassword);
        inputConfirmPassword.click();
    }

    public void clickCreateAccountBtn() {
        btnCreateAccount.click();
    }

    public HashMap<String, String> getExpectedErrorMessages() {
      return expectedErrorMessages;
    }

    public String getErrorInvalidEmailText() {
        return errorInvalidEmail.getText();
    }

    public String getErrorFirstNameText() {
        return errorFirstName.getText();
    }

    public String getErrorLastNameText() {
        return errorLastName.getText();
    }

    public String getErrorPasswordText() {
        return errorPassword.getText();
    }

    public String getErrorConfirmPasswordText() {
        return errorConfirmPassword.getText();
    }

}
