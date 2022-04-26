package Integration.Box;

import Logic.Box.Genres;
import Logic.Box.Privacy;
import Logic.Box.SABox;
import Logic.Box.TBox;
import Logic.SAAbstractFactory;
import org.bson.types.ObjectId;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;


public class ShowBox {
    private static final TBox tBox = new TBox("TEST_SHOW_BOX", "TEST_SHOW_BOX", Privacy.PRIVATE, Collections.singletonList(Genres.INDIE));

    private static SABox saBox;
    private static ObjectId _id;

    @BeforeClass
    public static void init() {
        saBox = SAAbstractFactory.getInstance().createSABox();
        _id = saBox.createBox(tBox);
    }

    @Test
    public void testCorrect() {
        try {
            assertNotNull(saBox.showBox(_id));
        } catch (Exception ae) {
            fail(ae.getMessage());
        }
    }

    @AfterClass
    public static void end() {
        if (_id != null)
            saBox.deleteFromDatabase(_id);
    }
}
