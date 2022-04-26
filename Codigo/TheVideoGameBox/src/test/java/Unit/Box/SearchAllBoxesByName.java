package Unit.Box;

import Data.DAOStubFactory;
import Logic.Box.SABox;
import Logic.Box.SABoxImp;
import Logic.Box.TBox;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

public class SearchAllBoxesByName {

    private static SABox saBox;

    private static final String limit = new String(new char[51]).replace('\0', 'f');
    private static final String empty = "";

    @BeforeClass
    public static void init() {
        saBox = new SABoxImp(new DAOStubFactory());
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
}