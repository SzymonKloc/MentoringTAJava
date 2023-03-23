package utils;

import org.testng.annotations.DataProvider;

public class RegistrationData {
    @DataProvider(name = "validUser")
    public static Object[][] validUser(){
        return new Object[][] {
                {"Johny", "Bravo", "automation%s@gmail.com", "Qwerty123@"}
        };
    }

    @DataProvider(name = "invalidEmails")
    public static Object[][] invalidEmails(){
        return new Object[][] {
                {"Johny", "Bravo", "automation{0}", "Qwerty123@"},
                {"Johny", "Bravo", "automation{0}@gmail", "Qwerty123@"},
                {"Johny", "Bravo", "automation{0}.@gmail", "Qwerty123@"},
                {"Johny", "Bravo", "automation{0}@.gmail", "Qwerty123@"},
                {"Johny", "Bravo", "automation{0}@gmail.c", "Qwerty123@"},
                {"Johny", "Bravo", "@gmail.com", "Qwerty123@"}
        };
    }

    @DataProvider(name = "invalidPasswords")
    public static Object[][] invalidPasswords(){
        return new Object[][] {
                {"Johny", "Bravo", "automation%s@gmail.com", " Qwer_12"},
                {"Johny", "Bravo", "automation%s@gmail.com", "Qwer_12 "},
                {"Johny", "Bravo", "automation%s@gmail.com", "qwer_12"},
                {"Johny", "Bravo", "automation%s@gmail.com", "QWER_12"},
                {"Johny", "Bravo", "automation%s@gmail.com", "a"},
                {"Johny", "Bravo", "automation%s@gmail.com", "A"},
                {"Johny", "Bravo", "automation%s@gmail.com", "@"},
                {"Johny", "Bravo", "automation%s@gmail.com", "12345678"},
                {"Johny", "Bravo", "automation%s@gmail.com", "QWERTYUI"},
                {"Johny", "Bravo", "automation%s@gmail.com", "qwertyui"},
                {"Johny", "Bravo", "automation%s@gmail.com", "@@@@@@@@"},
                {"Johny", "Bravo", "automation%s@gmail.com", "qwer1234"},
                {"Johny", "Bravo", "automation%s@gmail.com", "QWER1234"},
                {"Johny", "Bravo", "automation%s@gmail.com", "@@@@1324"},
                {"Johny", "Bravo", "automation%s@gmail.com", "qwer@@@@"},
                {"Johny", "Bravo", "automation%s@gmail.com", "qwerTYUI"},
        };
    }
}