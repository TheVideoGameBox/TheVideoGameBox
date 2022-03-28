package Unit.Box;

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
import java.util.List;

import static org.junit.Assert.*;

public class SearchAllBoxesByName {

    private static SABox saBox;

    private static final TBox tBox = new TBox("TEST_SEARCH_ALL", "TEST_SEARCH_ALL", Privacy.PRIVATE, new ArrayList<Genres>(Collections.singletonList(Genres.INDIE)));

    private static final String correct = "TEST_SEARCH_ALL";
    private static final String correctEmptyResult = "@!214_=-";
    private static final String limit = new String(new char[51]).replace('\0', 'f');
    private static final String empty = "";

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

    @Test
    public void testIncorrectInputCharLimit() {
        //Comprobar si el usuario introduce mas de 50 caracteres, se produce un fallo
        List<TBox> boxes = saBox.searchAllBoxesByName(limit);

        try {
            assertNull(boxes);
        } catch (Exception ae) {
            fail(ae.getMessage());
        }
    }

    @Test
    public void testIncorrectInputEmptyName() {
        //Comprobar si el usuario introduce 0 caracteres, se produce un fallo
        List<TBox> boxes = saBox.searchAllBoxesByName(empty);

        try {
            assertNull(boxes);
        } catch (Exception ae) {
            fail(ae.getMessage());
        }
    }

    @AfterClass
    public static void afterClass() {
        if (tBox.getId() != null)
            saBox.deleteFromDatabase(tBox.getId());
    }
}