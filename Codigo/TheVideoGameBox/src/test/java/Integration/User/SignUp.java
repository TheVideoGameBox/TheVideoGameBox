package Integration.User;

import Logic.SAAbstractFactory;
import Logic.User.SAUser;
import Logic.User.TUser;
import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class SignUp {

    private static SAUser saUser;
    private static ObjectId idUser;
    private static final TUser correct = new TUser("TEST_SIGN_UP@email.com", "TEST_SIGN_UP", "TEST_SIGN_UP");
    private static final TUser repeatedEmail = new TUser("TEST_SIGN_UP@email.com", "TEST_SIGN_UP2", "TEST_SIGN_UP");
    private static final TUser repeatedUsername = new TUser("TEST_SIGN_UP2@email.com", "TEST_SIGN_UP", "TEST_SIGN_UP");

    @BeforeClass
    public static void init() {
        saUser = SAAbstractFactory.getInstance().createSAUser();
    }

    @Test
    public void testCorrectSignUp() {
        idUser = saUser.createUser(correct);
        try {
            assertNotNull(idUser);
        } catch (Exception ae) {
            fail(ae.getMessage());
        }
    }

    @Test
    public void testRepeatedEmail() {
        idUser = saUser.createUser(correct);
        try {
            assertNull(saUser.createUser(repeatedEmail));
        } catch (Exception ae) {
            fail(ae.getMessage());
        }
    }

    @Test
    public void testRepeatedUsername() {
        idUser = saUser.createUser(correct);
        try {
            assertNull(saUser.createUser(repeatedUsername));
        } catch (Exception ae) {
            fail(ae.getMessage());
        }
    }

    @After
    public void after() {
        if (idUser != null)
            saUser.deleteFromDatabase(idUser);
    }
}
