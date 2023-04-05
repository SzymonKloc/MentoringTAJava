package TestData;

import org.testng.annotations.DataProvider;

public class SignInData {
    @DataProvider(name = "validUser")
    public static Object[][] validUser() {
        return new Object[][]{
                {"johnnyBravo@test.com", "secretPassword_1", "Johnny", "Bravo"}
        };
    }
}
