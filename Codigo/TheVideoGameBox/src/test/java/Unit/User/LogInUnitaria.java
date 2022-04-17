package Unit.User;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.bson.types.ObjectId;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import Logic.SAAbstractFactory;
import Logic.User.SAUser;
import Logic.User.TUser;

public class LogInUnitaria {


    private static SAUser saUser;
    private static ObjectId idUser;
    private static final TUser correct = new TUser("TEST_LOG_IN@email.com", "TEST_LOG_IN", "TEST_LOG_IN");
    private static final TUser wrongPassword = new TUser("TEST_LOG_IN@email.com", "TEST_LOG_IN", "TEST_LOG_IN_WRONG");
    private static final TUser[] incorrectEmail = {new TUser("TEST_LOG_IN4@", "TEST_LOG_IN4", "TEST_LOG_IN4"),
            new TUser("TEST_LOG_IN6@.com", "TEST_LOG_IN6", "TEST_LOG_IN6")};
    private static final TUser nullPassword = new TUser("TEST_LOG_IN2@email.com", "TEST_LOG_IN2", null);

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
    public void testIncorrectEmail() {
        try {
            for(TUser user : incorrectEmail)
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

    @AfterClass
    public static void end() {
        if (idUser != null)
            saUser.deleteFromDatabase(idUser);
    }
}
