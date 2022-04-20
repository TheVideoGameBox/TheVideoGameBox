package Unit.Game;

import Data.DAOStubFactory;
import Logic.Game.SAGame;
import Logic.Game.SAGameImp;
import Logic.Game.TGame;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

public class SearchAllByName {

    private static SAGame saGame;

    private static final String limit = new String(new char[26]).replace('\0', 'f');
    private static final String empty = "";

    @BeforeClass
    public static void init() {
        saGame = new SAGameImp(new DAOStubFactory());
    }

    @Test
    public void testIncorrectInputCharLimit() {
        //Comprobar si el usuario introduce mas de 50 caracteres, se produce un fallo
        List<TGame> games = saGame.searchAllByName(limit);

        try {
            assertNull(games);
        } catch (Exception ae) {
            fail(ae.getMessage());
        }
    }

    @Test
    public void testIncorrectInputEmptyName() {
        //Comprobar si el usuario introduce 0 caracteres, se produce un fallo
        List<TGame> games = saGame.searchAllByName(empty);

        try {
            assertNull(games);
        } catch (Exception ae) {
            fail(ae.getMessage());
        }
    }
}
