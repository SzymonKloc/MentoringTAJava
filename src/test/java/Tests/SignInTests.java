package Tests;

import Pages.HomePage;
import Pages.SignInPage;
import TestData.SignInData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.PagesUrls;

import java.io.IOException;

public class SignInTests extends BaseTest{

    private SignInPage signInPage;

    @BeforeMethod
    public void setupTest() throws IOException {
        super.setupTest();
        navigateToPage(PagesUrls.SignIn);
        signInPage = new SignInPage(driver);
    }

    @Test(dataProvider = "validUser", dataProviderClass = SignInData.class)
    public void signInWithValidCredentials(String email, String password, String name, String lastName){
        signInPage.enterEmail(email);
        signInPage.enterPassword(password);
        signInPage.clickSignInBtn();

        HomePage homePage = new HomePage(driver);
        String headerWelcomeMsg = homePage.getHeaderWelcomeMsg();
        String expectedWelcomeMsg = "Welcome, " + name + " " + lastName + "!";

        Assert.assertEquals(headerWelcomeMsg, expectedWelcomeMsg);
    }

}
