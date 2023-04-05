package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignInPage extends BasePage {

    public SignInPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "input#email")
    private WebElement inputEmail;

    @FindBy(xpath = "fieldset input#pass")
    private WebElement inputPassword;

    @FindBy(css = ".action.login.primary")
    private WebElement btnSignIn;

    @FindBy(css = ".action.remind")
    private WebElement linkResetPassword;

    @FindBy(css = ".action.create.primary")
    private WebElement btnCreateAccount;

    public void enterEmail(String email){
        inputEmail.sendKeys(email);
    }

    public void enterPassword(String password){
        inputPassword.sendKeys(password);
    }

    public void clickSignInBtn(){
        btnSignIn.click();
    }


}
