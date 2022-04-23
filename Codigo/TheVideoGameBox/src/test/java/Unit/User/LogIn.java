package Unit.User;

import Data.DAOStubFactory;
import Logic.User.SAUser;
import Logic.User.SAUserImp;
import Logic.User.TUser;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

public class LogIn {

    private static SAUser saUser;

    private static final TUser wrongPassword = new TUser("TEST_LOG_IN@email.com", "TEST_LOG_IN", "TEST_LOG_IN_WRONG");
    private static final TUser[] incorrectEmail = {new TUser("TEST_LOG_IN4@", "TEST_LOG_IN4", "TEST_LOG_IN4"),
            new TUser("TEST_LOG_IN6@.com", "TEST_LOG_IN6", "TEST_LOG_IN6")};
    private static final TUser nullPassword = new TUser("TEST_LOG_IN2@email.com", "TEST_LOG_IN2", null);

    @BeforeClass
    public static void init() {
        saUser = new SAUserImp(new DAOStubFactory());
    }

    @Test
    public void testIncorrectEmail() {
        try {
            for (TUser user : incorrectEmail)
                assertNull(saUser.logIn(user));
        } catch (Exception ae) {
            fail(ae.getMessage());
        }
    }

    @Test
    public void testNullPassword() {
        try {
            assertNull(saUser.logIn(nullPassword));
        } catch (Exception ae) {
            fail(ae.getMessage());
        }
    }

    @Test
    public void testWrongPassword() {
        try {
            assertNull(saUser.logIn(wrongPassword));
        } catch (Exception ae) {
            fail(ae.getMessage());
        }
    }
}