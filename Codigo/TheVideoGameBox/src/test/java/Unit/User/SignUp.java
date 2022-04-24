package Unit.User;

import Data.DAOStubFactory;
import Logic.User.SAUser;
import Logic.User.SAUserImp;
import Logic.User.TUser;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

public class SignUp {

    private static SAUser saUser;

    private static final TUser limit = new TUser("TEST_SIGN_UP4@email.com", new String(new char[51]).replace('\0', 'f'), "TEST_SIGN_UP4");
    private static final TUser[] incorrectEmail = {new TUser("TEST_SIGN_UP4@", "TEST_SIGN_UP4", "TEST_SIGN_UP4"),
            new TUser("TEST_SIGN_UP6@.com", "TEST_SIGN_UP6", "TEST_SIGN_UP6")};

    @BeforeClass
    public static void init() {
        saUser = new SAUserImp(new DAOStubFactory());
    }

    @Test
    public void testUsernameCharLimit() {
        try {
            assertNull(saUser.createUser(limit));
        } catch (Exception ae) {
            fail(ae.getMessage());
        }
    }

    @Test
    public void testIncorrectEmail() {
        try {
            for (TUser user : incorrectEmail)
                assertNull(saUser.createUser(user));
        } catch (Exception ae) {
            fail(ae.getMessage());
        }
    }
}
