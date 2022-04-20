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
    private static final TUser correct = new TUser("TEST_SIGN_UP1@email.com", "TEST_SIGN_UP1", "TEST_SIGN_UP1");
    private static final TUser repeatedEmail = new TUser("TEST_SIGN_UP2@email.com", "TEST_SIGN_UP2", "TEST_SIGN_UP2");
    private static final TUser repeatedUsername = new TUser("TEST_SIGN_UP3@email.com", "TEST_SIGN_UP3", "TEST_SIGN_UP3");

    private ObjectId idUser;

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
        idUser = saUser.createUser(repeatedEmail);
        try {
            assertNull(saUser.createUser(repeatedEmail));
        } catch (Exception ae) {
            fail(ae.getMessage());
        }
    }

    @Test
    public void testRepeatedUsername() {
        idUser = saUser.createUser(repeatedUsername);
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
