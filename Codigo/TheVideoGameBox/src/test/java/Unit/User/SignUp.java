package Unit.User;

import Data.Connection;
import Logic.User.SAUser;
import Logic.User.TUser;
import Logic.SAAbstractFactory;
import com.mongodb.client.MongoDatabase;
import org.bson.types.ObjectId;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

public class SignUp {

    private static SAUser saUser;
    private static ObjectId idUser;
    private static final TUser correct = new TUser("TEST_SIGN_UP", "TEST_SIGN_UP", "TEST_SIGN_UP");

    @BeforeClass
    public static void init() {
        MongoDatabase db = Connection.getInstance().getConnection();
        saUser = SAAbstractFactory.getInstance().createSAUser();
    }

    @Test
    public void testCorrectSignUp() {
    	ObjectId idUser = saUser.createUser(correct);
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
        TUser incorrect = new TUser("TEST_SIGN_UP", "TEST_SIGN_UP_2", "TEST_SIGN_UP_2");
        try {	
            assertNull(saUser.createUser(incorrect));
        } catch (Exception ae) {
            fail(ae.getMessage());
        }
    }
    
    @Test
    public void testRepeatedUsername() {
        TUser incorrect = new TUser("TEST_SIGN_UP_2", "TEST_SIGN_UP", "TEST_SIGN_UP_2");
        try {	
            assertNull(saUser.createUser(incorrect));
        } catch (Exception ae) {
            fail(ae.getMessage());
        }
    }
    
    @Test
    public void testTooLongUsername() {
        TUser incorrect = new TUser("TEST_SIGN_UP_2", "TEST_SIGN_UPaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "TEST_SIGN_UP_2");
        try {	
            assertNull(saUser.createUser(incorrect));
        } catch (Exception ae) {
            fail(ae.getMessage());
        }
    }
    
    @AfterClass
    public static void eliminarUsuarioPrueba() {
    	if (idUser != null) saUser.deleteFromDatabase(idUser);
    }


}
