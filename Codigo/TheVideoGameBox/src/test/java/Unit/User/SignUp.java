package Unit.User;

import Logic.Box.Genres;
import Logic.Box.Privacy;
import Logic.Box.TBox;
import Logic.SAAbstractFactory;
import Logic.User.SAUser;
import Logic.User.TUser;
import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class SignUp {

    private static SAUser saUser;
    private static ObjectId idUser;
    private static final TUser correct = new TUser("TEST_SIGN_UP@email.com", "TEST_SIGN_UP", "TEST_SIGN_UP");
    private static final TUser repeatedEmail = new TUser("TEST_SIGN_UP2@email.com", "TEST_SIGN_UP2", "TEST_SIGN_UP2");
    private static final TUser repeatedUsername = new TUser("TEST_SIGN_UP3@email.com", "TEST_SIGN_UP3", "TEST_SIGN_UP3");
    private static final TUser limit = new TUser("TEST_SIGN_UP4@email.com", new String(new char[51]).replace('\0', 'f'), "TEST_SIGN_UP4");
    private static final TUser[] incorrectEmail = {new TUser("TEST_SIGN_UP4@", "TEST_SIGN_UP4", "TEST_SIGN_UP4"),
            new TUser("TEST_SIGN_UP6@.com", "TEST_SIGN_UP6", "TEST_SIGN_UP6")};

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
        } finally {
            if (idUser != null) saUser.deleteFromDatabase(idUser);
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
            for(TUser user : incorrectEmail)
                assertNull(saUser.createUser(user));
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
