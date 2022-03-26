package Unit.Box;

import Logic.Box.Genres;
import Logic.Box.Privacy;
import Logic.Box.SABox;
import Logic.Box.TBox;
import Logic.SAAbstractFactory;
import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class CreateBox {

    private static SABox saBox;

    private static final String LIMIT_50 = new String(new char[51]).replace('\0', 'f');
    private static final String LIMIT_250 = new String(new char[251]).replace('\0', 'f');
    private static final ArrayList<Genres> CATEGORIES = new ArrayList<Genres>(Arrays.asList(Genres.INDIE));

    private static final TBox correct = new TBox("TEST_CREATE", "TEST_CREATE", Privacy.PRIVATE, new ArrayList<Genres>(Arrays.asList(Genres.INDIE)));
    private static final TBox[] limit = {new TBox(LIMIT_50, "TEST_CREATE", Privacy.PRIVATE, new ArrayList<Genres>(Arrays.asList(Genres.INDIE))),
            new TBox("TEST_CREATE", LIMIT_250, Privacy.PRIVATE, new ArrayList<Genres>(Arrays.asList(Genres.INDIE)))};
    private static final TBox[] empty = {new TBox("", "TEST_CREATE", Privacy.PRIVATE, new ArrayList<Genres>(Arrays.asList(Genres.INDIE))),
            new TBox("TEST_CREATE", "", Privacy.PRIVATE, new ArrayList<Genres>(Arrays.asList(Genres.INDIE))),
            new TBox("TEST_CREATE", "TEST_CREATE", Privacy.PRIVATE, new ArrayList<Genres>()),
            new TBox("TEST_CREATE", "TEST_CREATE", Privacy.PRIVATE, null),
            new TBox("TEST_CREATE", "TEST_CREATE", null, new ArrayList<Genres>(Arrays.asList(Genres.INDIE)))};

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