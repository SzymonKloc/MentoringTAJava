package Tests;

import Pages.CreateAccountPage;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import utils.PagesUrls;
import utils.RegistrationData;
import utils.RegistrationErrorMsgKeys;

import java.io.IOException;
import java.util.HashMap;


public class RegistrationTests extends BaseTest{

    private CreateAccountPage createAccountPage;

    @BeforeMethod
    public void setupTest() throws IOException {
//        What's the better way? Nunit/C# doesn't mind double setups
        super.setupTest();
        navigateToPage(PagesUrls.CreateAccount);
        createAccountPage = new CreateAccountPage(driver);
    }


    @Test(dataProvider = "validUser", dataProviderClass = RegistrationData.class)
    public void createValidAccount(String firstName, String lastName, String email, String password) {
        createAccountPage.enterFirstName(firstName + uniqueNameForTests);
        createAccountPage.enterLastName(lastName + uniqueNameForTests);
        createAccountPage.enterEmail(String.format(email, uniqueNameForTests));
        createAccountPage.enterPassword(password);
        createAccountPage.enterConfirmPassword(password);
        createAccountPage.clickCreateAccountBtn();

        Assert.assertEquals(driver.getCurrentUrl(), PagesUrls.MyAccount);
    }

    @Test(dataProvider = "invalidEmails", dataProviderClass = RegistrationData.class)
    public void invalidEmails(String firstName, String lastName, String email, String password){
        createAccountPage.enterFirstName(firstName + uniqueNameForTests);
        createAccountPage.enterLastName(lastName + uniqueNameForTests);
        createAccountPage.enterEmail(String.format(email, uniqueNameForTests));
        createAccountPage.enterPassword(password);
        createAccountPage.enterConfirmPassword(password);
        createAccountPage.clickCreateAccountBtn();

        HashMap<String, String> errorMessages = createAccountPage.getExpectedErrorMessages();
        String invalidEmailError = errorMessages.get(String.valueOf(RegistrationErrorMsgKeys.invalidEmail));

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(createAccountPage.getErrorInvalidEmailText(), invalidEmailError);
        softAssert.assertNotEquals(driver.getCurrentUrl(), PagesUrls.MyAccount);
        softAssert.assertAll();
    }

    @Test(dataProvider = "invalidPasswords", dataProviderClass = RegistrationData.class)
    public void invalidPasswords(String firstName, String lastName, String email, String password){
        createAccountPage.enterFirstName(firstName + uniqueNameForTests);
        createAccountPage.enterLastName(lastName + uniqueNameForTests);
        createAccountPage.enterEmail(String.format(email, uniqueNameForTests));
        createAccountPage.enterPassword(password);
        createAccountPage.enterConfirmPassword(password);
        createAccountPage.clickCreateAccountBtn();

        HashMap<String, String> errorMessages = createAccountPage.getExpectedErrorMessages();
        String shortPasswordError = errorMessages.get(String.valueOf(RegistrationErrorMsgKeys.shortPassword));
        String weakPasswordError = errorMessages.get(String.valueOf(RegistrationErrorMsgKeys.weakPassword));
        String actualPasswordError = createAccountPage.getErrorPasswordText();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotEquals(driver.getCurrentUrl(), PagesUrls.MyAccount);

        if (password.length() < 8 || password.contains(" "))
        {
            softAssert.assertEquals(shortPasswordError, actualPasswordError);
        }
        else
        {
            softAssert.assertEquals(weakPasswordError, actualPasswordError);
        }
        softAssert.assertAll();
    }

}
