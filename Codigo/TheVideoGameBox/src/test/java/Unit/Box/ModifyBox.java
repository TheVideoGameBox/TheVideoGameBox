package Unit.Box;

import Data.DAOStubFactory;
import Logic.Box.*;
import org.bson.types.ObjectId;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.assertNull;

public class ModifyBox {
    private static SABox saBox;

    private static final String LIMIT_50 = new String(new char[51]).replace('\0', 'f');
    private static final String LIMIT_250 = new String(new char[251]).replace('\0', 'f');

    private static final TBox[] limit = {new TBox(LIMIT_50, "TEST_CREATE", Privacy.PRIVATE, new ArrayList<Genres>(Collections.singletonList(Genres.INDIE))),
            new TBox("TEST_CREATE", LIMIT_250, Privacy.PRIVATE, new ArrayList<Genres>(Collections.singletonList(Genres.INDIE)))};
    private static final TBox[] empty = {new TBox("", "TEST_CREATE", Privacy.PRIVATE, new ArrayList<Genres>(Collections.singletonList(Genres.INDIE))),
            new TBox("TEST_CREATE", "", Privacy.PRIVATE, new ArrayList<Genres>(Collections.singletonList(Genres.INDIE))),
            new TBox("TEST_CREATE", "TEST_CREATE", Privacy.PRIVATE, Collections.emptyList()),
            new TBox("TEST_CREATE", "TEST_CREATE", Privacy.PRIVATE, null),
            new TBox("TEST_CREATE", "TEST_CREATE", null, new ArrayList<Genres>(Collections.singletonList(Genres.INDIE)))};

    @BeforeClass
    public static void init() {
        saBox = new SABoxImp(new DAOStubFactory());
    }

    @Test
    public void modifyBoxCharLimit() {
        for (TBox tBox : limit) {
            ObjectId result = saBox.modifyBox(tBox);
            assertNull(result);
        }
    }

    @Test
    public void modifyBoxEmptyFields() {
        for (TBox tBox : empty) {
            ObjectId result = saBox.modifyBox(tBox);
            assertNull(result);
        }
    }
}
