import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class LoginTest {



    @Test
    void testRegisterUser_PasswordMeetsComplexity() {
        Login login = new Login();
        String result = login.registerUser("Kyle", "Smith", "kyl_1",
                "Ch&&sec@ke99!", "+27838968976");
        assertTrue(result.contains("Password successfully captured."));
    }

    @Test
    void testRegisterUser_PasswordDoesNotMeetComplexity() {
        Login login = new Login();
        String result = login.registerUser("Kyle", "Smith", "kyl_1",
                "password", "+27838968976");
        assertTrue(result.contains(
                "Password is not correctly formatted; please ensure that the password "
                + "contains at least eight characters, a capital letter, a number, and a "
                + "special character."));
    }

    @Test
    void testRegisterUser_CellPhoneCorrectlyFormatted() {
        Login login = new Login();
        String result = login.registerUser("Kyle", "Smith", "kyl_1",
                "Ch&&sec@ke99!", "+27838968976");
        assertTrue(result.contains("Cell phone number successfully captured."));
    }

    @Test
    void testRegisterUser_CellPhoneIncorrectlyFormatted() {
        Login login = new Login();
        String result = login.registerUser("Kyle", "Smith", "kyl_1",
                "Ch&&sec@ke99!", "08966553");
        assertTrue(result.contains(
                "Cell phone number is incorrectly formatted or does not contain an "
                + "international code; please correct the number and try again."));
    }

    // ---------- assertTrue / assertFalse tests: boolean check methods ----------

    @Test
    void testUsernameCorrectlyFormatted() {
        Login login = new Login();
        assertTrue(login.checkUserName("kyl_1"));
    }

    @Test
    void testUsernameIncorrectlyFormatted() {
        Login login = new Login();
        assertFalse(login.checkUserName("kyle!!!!!!"));
    }

    @Test
    void testPasswordMeetsComplexityRequirements() {
        Login login = new Login();
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"));
    }

    @Test
    void testPasswordDoesNotMeetComplexityRequirements() {
        Login login = new Login();
        assertFalse(login.checkPasswordComplexity("password"));
    }

    @Test
    void testCellPhoneNumberCorrectlyFormatted() {
        Login login = new Login();
        assertTrue(login.checkCellPhoneNumber("+27838968976"));
    }

    @Test
    void testCellPhoneNumberIncorrectlyFormatted() {
        Login login = new Login();
        assertFalse(login.checkCellPhoneNumber("08966553"));
    }

    // ---------- Login tests ----------

    @Test
    void testLoginSuccessful() {
        Login login = new Login();
        login.registerUser("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(login.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }

    @Test
    void testLoginFailed() {
        Login login = new Login();
        login.registerUser("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertFalse(login.loginUser("kyl_1", "wrongPassword"));
    }

    @Test
    void testReturnLoginStatus_Success() {
        Login login = new Login();
        login.registerUser("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        boolean success = login.loginUser("kyl_1", "Ch&&sec@ke99!");
        assertEquals("Welcome Kyle, Smith, it is great to see you again.",
                login.returnLoginStatus(success));
    }

    @Test
    void testReturnLoginStatus_Failure() {
        Login login = new Login();
        login.registerUser("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        boolean success = login.loginUser("kyl_1", "wrongPassword");
        assertEquals("Username or password incorrect, please try again.",
                login.returnLoginStatus(success));
    }
}