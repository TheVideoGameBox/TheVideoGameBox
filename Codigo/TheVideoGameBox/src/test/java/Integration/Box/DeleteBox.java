package Integration.Box;

import Logic.Box.Genres;
import Logic.Box.Privacy;
import Logic.Box.SABox;
import Logic.Box.TBox;
import Logic.SAAbstractFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;

public class DeleteBox {
    private static SABox saBox;
    private static TBox correct = new TBox("TEST_DELETE_BOX", "TEST_DELETE_BOX", Privacy.PRIVATE,
            new ArrayList<Logic.Box.Genres>(Arrays.asList(Genres.RACING, Genres.INDIE)), Collections.emptyList(), null);

    @BeforeClass
    public static void init() {
        saBox = SAAbstractFactory.getInstance().createSABox();
        correct.setId(saBox.createBox(correct));
    }

    @Test
    public void testCorrectDeleteBox() {
        try {
            assertNotNull(saBox.deleteBox(correct.getId()));
            assertFalse(saBox.showBox(correct.getId()).isActive());
        } catch (Exception ae) {
            fail(ae.getMessage());
        }
    }


    @AfterClass
    public static void after() {
        if (correct.getId() != null) saBox.deleteFromDatabase(correct.getId());
    }
}
