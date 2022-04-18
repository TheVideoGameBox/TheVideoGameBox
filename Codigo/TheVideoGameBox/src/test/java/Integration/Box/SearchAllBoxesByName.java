package Integration.Box;

import Logic.Box.Genres;
import Logic.Box.Privacy;
import Logic.Box.SABox;
import Logic.Box.TBox;
import Logic.SAAbstractFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class SearchAllBoxesByName {

    private static SABox saBox;

    private static final TBox tBox = new TBox("TEST_SEARCH_ALL", "TEST_SEARCH_ALL", Privacy.PRIVATE,
            Collections.singletonList(Genres.INDIE), Collections.emptyList());

    private static final String correct = "TEST_SEARCH_ALL";
    private static final String correctEmptyResult = "@!214_=-";

    @BeforeClass
    public static void init() {
        saBox = SAAbstractFactory.getInstance().createSABox();
        tBox.setId(saBox.createBox(tBox));
    }

    @Test
    public void testCorrectResult() {
        //Comprobar se encuentran boxes (se espera encontrar al menos una box) y que el nombre de todas las boxes encontradas contienen la cadena especificada
        List<TBox> boxes = saBox.searchAllBoxesByName(correct);

        try {
            assertFalse(boxes.isEmpty());
            for (TBox box : boxes)
                assertTrue(box.getName().contains(correct));
        } catch (Exception ae) {
            fail(ae.getMessage());
        }
    }

    @Test
    public void testCorrectEmptyResult() {
        //Comprobar que devuelve una lista vacia en caso de no encontrar ninguna box
        List<TBox> boxes = saBox.searchAllBoxesByName(correctEmptyResult);

        try {
            assertTrue(boxes.isEmpty());
        } catch (Exception ae) {
            fail(ae.getMessage());
        }
    }

    @AfterClass
    public static void afterClass() {
        if (tBox.getId() != null) saBox.deleteFromDatabase(tBox.getId());
    }
}