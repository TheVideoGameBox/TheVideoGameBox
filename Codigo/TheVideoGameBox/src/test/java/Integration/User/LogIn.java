package Integration.User;

import Logic.SAAbstractFactory;
import Logic.User.SAUser;
import Logic.User.TUser;
import org.bson.types.ObjectId;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class LogIn {
    private static SAUser saUser;
    private static ObjectId idUser;
    private static final TUser correct = new TUser("TEST_LOG_IN@email.com", "TEST_LOG_IN", "TEST_LOG_IN");
    private static final TUser wrongPassword = new TUser("TEST_LOG_IN@email.com", "TEST_LOG_IN", "TEST_LOG_IN_WRONG");

    @BeforeClass
    public static void init() {
        saUser = SAAbstractFactory.getInstance().createSAUser();
        idUser = saUser.createUser(correct);
    }

    @Test
    public void testCorrectLogIn() {
        try {
            assertNotNull(saUser.logIn(correct));
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

    @AfterClass
    public static void end() {
        if (idUser != null)
            saUser.deleteFromDatabase(idUser);
    }
}
