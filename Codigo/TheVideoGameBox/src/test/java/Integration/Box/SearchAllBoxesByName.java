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

    private static final TBox tBox = new TBox("TEST_SEARCH_ALL", "TEST_SEARCH_ALL", Privacy.PUBLIC,
            Collections.singletonList(Genres.INDIE), Collections.emptyList(), null);

    private static final TBox privateTBox = new TBox("TEST_SEARCH_ALL2", "TEST_SEARCH_ALL2", Privacy.PRIVATE,
            Collections.singletonList(Genres.INDIE), Collections.emptyList(), null);

    private static final String correct = "TEST_SEARCH_ALL";
    private static final String[] correctEmptyResult = {"@!214_=-", "TEST_SEARCH_ALL2"};

    @BeforeClass
    public static void init() {
        saBox = SAAbstractFactory.getInstance().createSABox();
        tBox.setId(saBox.createBox(tBox));
        privateTBox.setId(saBox.createBox(privateTBox));
    }

    @Test
    public void testCorrectResult() {
        //Comprobar se encuentran boxes (se espera encontrar al menos una box) y que el nombre de todas las boxes encontradas contienen la cadena especificada
        List<TBox> boxes = saBox.searchAllBoxesByName(correct);

        try {
            assertFalse(boxes.isEmpty());
            for (TBox box : boxes)
                assertTrue(box.getName().contains(correct) && box.isActive() && box.getPrivacy() == Privacy.PUBLIC);
        } catch (Exception ae) {
            fail(ae.getMessage());
        }
    }

    @Test
    public void testCorrectEmptyResult() {
        //Comprobar que devuelve una lista vacia en caso de no encontrar ninguna box o de que esta sea privada
        for (String name : correctEmptyResult) {
            List<TBox> boxes = saBox.searchAllBoxesByName(name);

            try {
                assertTrue(boxes.isEmpty());
            } catch (Exception ae) {
                fail(ae.getMessage());
            }
        }
    }

    @AfterClass
    public static void afterClass() {
        if (tBox.getId() != null) {
            saBox.deleteFromDatabase(tBox.getId());
            saBox.deleteFromDatabase(privateTBox.getId());
        }
    }
}