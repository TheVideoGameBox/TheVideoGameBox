package Unit.Box;

import Data.Connection;
import Data.DAOAbstractFactory;
import Logic.Box.*;
import Logic.Game.TGame;
import Logic.SAAbstractFactory;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.fail;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class CreateBox {

    private static SABox saBox;

    private static final String LIMIT_50 = new String(new char[51]).replace('\0', 'f');
    private static final String LIMIT_250 = new String(new char[251]).replace('\0', 'f');
    private static final ArrayList<Category> CATEGORIES = new ArrayList<Category>(Arrays.asList(Category.INDIE));

    private static final TBox correct = new TBox("TEST_CREATE", "TEST_CREATE", Privacy.PRIVATE, new ArrayList<Category>(Arrays.asList(Category.INDIE)));
    private static final TBox[] limit = {new TBox(LIMIT_50, "TEST_CREATE", Privacy.PRIVATE, new ArrayList<Category>(Arrays.asList(Category.INDIE))),
            new TBox("TEST_CREATE", LIMIT_250, Privacy.PRIVATE, new ArrayList<Category>(Arrays.asList(Category.INDIE)))};
    private static final TBox[] empty = {new TBox("", "TEST_CREATE", Privacy.PRIVATE, new ArrayList<Category>(Arrays.asList(Category.INDIE))),
            new TBox("TEST_CREATE", "", Privacy.PRIVATE, new ArrayList<Category>(Arrays.asList(Category.INDIE))),
            new TBox("TEST_CREATE", "TEST_CREATE", Privacy.PRIVATE, new ArrayList<Category>()),
            new TBox("TEST_CREATE", "TEST_CREATE", Privacy.PRIVATE, null),
            new TBox("TEST_CREATE", "TEST_CREATE", null, new ArrayList<Category>(Arrays.asList(Category.INDIE)))};

    private static ObjectId idBox = null;

    @BeforeClass
    public static void init() {
        saBox = SAAbstractFactory.getInstance().createSABox();
    }

    @Test
    public void testCorrectResult() {
        //Comprobar que la box se ha creado correctamente
        idBox = saBox.createBox(correct);

        try {
            assertNotNull(idBox);
        } catch (Exception ae) {
            fail(ae.getMessage());
        }
    }

    @Test
    public void testIncorrectInputCharLimit() {
        //Comprobar que la box no se ha creado correctamente debido a que hay un campo que sobrepasa el limite de caracteres
        for (TBox tBox : limit) {
            ObjectId result = saBox.createBox(tBox);

            try {
                assertNull(result);
            } catch (Exception ae) {
                fail(ae.getMessage());
            }
        }
    }

    @Test
    public void testIncorrectInputEmptyField() {
        //Comprobar que la box no se ha creado correctamente debido a que hay un campo obligatorio vacio
        for (TBox tBox : empty) {
            ObjectId result = saBox.createBox(tBox);

            try {
                assertNull(result);
            } catch (Exception ae) {
                fail(ae.getMessage());
            }
        }
    }

    @After
    public void after() {
        if (idBox != null)
            saBox.deleteFromDatabase(idBox);
    }
}