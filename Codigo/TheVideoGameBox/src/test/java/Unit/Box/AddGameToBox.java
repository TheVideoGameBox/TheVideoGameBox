package Unit.Box;

import Logic.Box.Category;
import Logic.Box.Privacy;
import Logic.Box.SABox;
import Logic.Box.TBox;
import Logic.SAAbstractFactory;
import org.bson.types.ObjectId;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class AddGameToBox {

    private static SABox saBox;
    private static ObjectId idBox = null;

    private static final TBox correct = new TBox("TEST_ADD_GAME", "TEST_ADD_GAME", Privacy.PRIVATE, new ArrayList<Logic.Box.Category>(List.of(Category.RACING, Category.INDIE)));
    private static final ObjectId idGame = new ObjectId("62370c135c37793c661d73bf");

    @BeforeClass
    public static void init() {
        saBox = SAAbstractFactory.getInstance().createSABox();
        idBox = saBox.createBox(correct);
        correct.setId(idBox);
    }

    @Test
    public void testCorrectResult() {
        try {
            assertNotNull(saBox.addGame(correct, idGame));
        } catch (Exception ae) {
            fail(ae.getMessage());
        }
    }

    @AfterClass
    public static void after() {
        if (idBox != null) saBox.deleteFromDatabase(idBox);
    }

}
