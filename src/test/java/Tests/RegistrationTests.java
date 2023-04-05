package Tests;

import Pages.CreateAccountPage;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import utils.PagesUrls;
import TestData.RegistrationData;
import utils.RegistrationErrorMsgKeys;

import java.io.IOException;
import java.util.HashMap;


public class RegistrationTests extends BaseTest{

    private CreateAccountPage createAccountPage;

    @BeforeMethod
    public void setupTest() throws IOException {
        super.setupTest();
        navigateToPage(PagesUrls.CreateAccount);
        createAccountPage = new CreateAccountPage(driver);
    }

    @Test(dataProvider = "validUser", dataProviderClass = RegistrationData.class)
    public void createValidAccount(String firstName, String lastName, String email, String password) {
        createAccountPage.fillAccountData(firstName, lastName, email, password, uniqueNameForTests);
        createAccountPage.clickCreateAccountBtn();

        Assert.assertEquals(driver.getCurrentUrl(), PagesUrls.MyAccount);
    }

    @Test
    public void createEmptyAccount() {

        createAccountPage.clickCreateAccountBtn();

        HashMap<String, String> errorMessages = createAccountPage.getExpectedErrorMessages();
        String missingFirstNameError = errorMessages.get(String.valueOf(RegistrationErrorMsgKeys.missingFirstName));
        String missingLastNameError = errorMessages.get(String.valueOf(RegistrationErrorMsgKeys.missingLastName));
        String missingEmailError = errorMessages.get(String.valueOf(RegistrationErrorMsgKeys.missingEmail));
        String missingPasswordError = errorMessages.get(String.valueOf(RegistrationErrorMsgKeys.missingPassword));
        String missingConfirmPasswordError = errorMessages.get(String.valueOf(RegistrationErrorMsgKeys.missingConfirmPassword));

        String actualFirstNameError = createAccountPage.getErrorFirstNameText();
        String actualLastNameError = createAccountPage.getErrorLastNameText();
        String actualEmailError = createAccountPage.getErrorInvalidEmailText();
        String actualPasswordError = createAccountPage.getErrorPasswordText();
        String actualConfirmPasswordError = createAccountPage.getErrorConfirmPasswordText();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotEquals(driver.getCurrentUrl(), PagesUrls.MyAccount);
        softAssert.assertEquals(actualFirstNameError, missingFirstNameError);
        softAssert.assertEquals(actualLastNameError, missingLastNameError);
        softAssert.assertEquals(actualEmailError, missingEmailError);
        softAssert.assertEquals(actualPasswordError, missingPasswordError);
        softAssert.assertEquals(actualConfirmPasswordError, missingConfirmPasswordError);
    }

    @Test(dataProvider = "invalidEmails", dataProviderClass = RegistrationData.class)
    public void invalidEmails(String firstName, String lastName, String email, String password){
        createAccountPage.fillAccountData(firstName, lastName, email, password, uniqueNameForTests);
        createAccountPage.clickCreateAccountBtn();

        HashMap<String, String> errorMessages = createAccountPage.getExpectedErrorMessages();
        String invalidEmailError = errorMessages.get(String.valueOf(RegistrationErrorMsgKeys.invalidEmail));
        String actualInvalidEmailError = createAccountPage.getErrorInvalidEmailText();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualInvalidEmailError, invalidEmailError);
        softAssert.assertNotEquals(driver.getCurrentUrl(), PagesUrls.MyAccount);
        softAssert.assertAll();
    }

    @Test(dataProvider = "invalidPasswords", dataProviderClass = RegistrationData.class)
    public void invalidPasswords(String firstName, String lastName, String email, String password){
        createAccountPage.fillAccountData(firstName, lastName, email, password, uniqueNameForTests);
        createAccountPage.clickCreateAccountBtn();

        HashMap<String, String> errorMessages = createAccountPage.getExpectedErrorMessages();
        String shortPasswordError = errorMessages.get(String.valueOf(RegistrationErrorMsgKeys.shortPassword));
        String weakPasswordError = errorMessages.get(String.valueOf(RegistrationErrorMsgKeys.weakPassword));
        String actualPasswordError = createAccountPage.getErrorPasswordText();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotEquals(driver.getCurrentUrl(), PagesUrls.MyAccount);

        if (password.length() < 8 || password.contains(" "))
        {
            softAssert.assertEquals(actualPasswordError, shortPasswordError);
        }
        else
        {
            softAssert.assertEquals(actualPasswordError, weakPasswordError);
        }
        softAssert.assertAll();
    }

    @Test(dataProvider = "validUser", dataProviderClass = RegistrationData.class)
    public void emptyFirstName(String firstName, String lastName, String email, String password) {
        createAccountPage.fillAccountData("", lastName, email, password, uniqueNameForTests);
        createAccountPage.clickCreateAccountBtn();

        HashMap<String, String> errorMessages = createAccountPage.getExpectedErrorMessages();
        String missingFirstNameError = errorMessages.get(String.valueOf(RegistrationErrorMsgKeys.missingFirstName));
        String actualFirstNameError = createAccountPage.getErrorFirstNameText();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotEquals(driver.getCurrentUrl(), PagesUrls.MyAccount);
        softAssert.assertEquals(actualFirstNameError, missingFirstNameError);
    }

    @Test(dataProvider = "validUser", dataProviderClass = RegistrationData.class)
    public void emptyLastName(String firstName, String lastName, String email, String password) {
        createAccountPage.fillAccountData(firstName, "", email, password, uniqueNameForTests);
        createAccountPage.clickCreateAccountBtn();

        HashMap<String, String> errorMessages = createAccountPage.getExpectedErrorMessages();
        String missingLastNameError = errorMessages.get(String.valueOf(RegistrationErrorMsgKeys.missingLastName));
        String actualLastNameError = createAccountPage.getErrorLastNameText();


        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotEquals(driver.getCurrentUrl(), PagesUrls.MyAccount);
        softAssert.assertEquals(actualLastNameError, missingLastNameError);
    }

    @Test(dataProvider = "validUser", dataProviderClass = RegistrationData.class)
    public void emptyEmail(String firstName, String lastName, String email, String password) {
        createAccountPage.fillAccountData(firstName, lastName, "", password, uniqueNameForTests);
        createAccountPage.clickCreateAccountBtn();

        HashMap<String, String> errorMessages = createAccountPage.getExpectedErrorMessages();
        String missingEmailError = errorMessages.get(String.valueOf(RegistrationErrorMsgKeys.missingEmail));
        String actualMissingEmailError = createAccountPage.getErrorInvalidEmailText();


        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotEquals(driver.getCurrentUrl(), PagesUrls.MyAccount);
        softAssert.assertEquals(actualMissingEmailError, missingEmailError);
    }

    @Test(dataProvider = "validUser", dataProviderClass = RegistrationData.class)
    public void emptyPassword(String firstName, String lastName, String email, String password) {
        createAccountPage.fillAccountData(firstName, lastName, email, "", uniqueNameForTests);
        createAccountPage.clickCreateAccountBtn();

        HashMap<String, String> errorMessages = createAccountPage.getExpectedErrorMessages();
        String missingPasswordError = errorMessages.get(String.valueOf(RegistrationErrorMsgKeys.missingPassword));
        String actualPasswordError = createAccountPage.getErrorPasswordText();


        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotEquals(driver.getCurrentUrl(), PagesUrls.MyAccount);
        softAssert.assertEquals(actualPasswordError, missingPasswordError);
    }

    @Test(dataProvider = "validUser", dataProviderClass = RegistrationData.class)
    public void emptyConfirmPassword(String firstName, String lastName, String email, String password) {
        createAccountPage.enterFirstName(firstName + uniqueNameForTests);
        createAccountPage.enterLastName(lastName + uniqueNameForTests);
        createAccountPage.enterEmail(String.format(email, uniqueNameForTests));
        createAccountPage.enterPassword(password);
        createAccountPage.clickCreateAccountBtn();

        HashMap<String, String> errorMessages = createAccountPage.getExpectedErrorMessages();
        String missingConfirmPasswordError = errorMessages.get(String.valueOf(RegistrationErrorMsgKeys.missingConfirmPassword));
        String actualPasswordError = createAccountPage.getErrorConfirmPasswordText();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotEquals(driver.getCurrentUrl(), PagesUrls.MyAccount);
        softAssert.assertEquals(actualPasswordError, missingConfirmPasswordError);
    }


}
