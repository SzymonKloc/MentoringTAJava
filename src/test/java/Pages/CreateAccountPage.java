package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.RegistrationErrorMsgKeys;
import utils.WaitUntil;

import java.time.Duration;
import java.util.HashMap;


public class CreateAccountPage extends BasePage {

    private final HashMap<String, String> expectedErrorMessages = new HashMap<>() {{
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


    /*  Java doesn't support direct var -> func assigment (lazy locators), thus noSuchElement ex.
    What's the proper way to create wrappers for finds with build in fluentWaits? @FindBy is the only way to go?
    */
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
    @FindBy(css = ".action.submit.primary")
    private WebElement btnCreateAccount;
    @FindBy(id = "password-confirmation")
    private WebElement inputConfirmPassword;
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

    public CreateAccountPage(WebDriver driver) {
        super(driver);
    }

    public void enterFirstName(String firstName) {
        inputFirstName.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        inputLastName.sendKeys(lastName);
    }

    public void enterEmail(String email) {
        inputEmail.sendKeys(email);
    }

    public void enterPassword(String password) {
        inputPassword.sendKeys(password);
    }

    public void enterConfirmPassword(String confirmPassword) {
        inputConfirmPassword.sendKeys(confirmPassword);
    }

    /*It takes a second for a script with required fields to load, how to handle it properly?
    Wait for form elements to gain "required" attributes? DOM state is complete before scripts load.
    This solution works, but seems deeply flawed as it waits for any change
     */

    public void clickCreateAccountBtn() {
        boolean domHasChanged = WaitUntil.domHasChanged(driver, Duration.ofMillis(500), Duration.ofSeconds(5));
        if (domHasChanged) {
            btnCreateAccount.click();
        } else {
            //Move to logs when implemented
            System.out.println("Script identifying required fields not loaded");
        }
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
