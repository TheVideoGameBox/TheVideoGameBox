package Integration.Box;

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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class CreateBox {

    private static SABox saBox;

    private static final TBox correct = new TBox("TEST_CREATE", "TEST_CREATE", Privacy.PRIVATE,
            new ArrayList<Genres>(Arrays.asList(Genres.INDIE)));

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

    @After
    public void after() {
        if (idBox != null) saBox.deleteFromDatabase(idBox);
    }
}