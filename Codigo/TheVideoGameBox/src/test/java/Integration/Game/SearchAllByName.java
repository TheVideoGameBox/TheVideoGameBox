package Integration.Game;

import Logic.Game.SAGame;
import Logic.Game.TGame;
import Logic.SAAbstractFactory;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class SearchAllByName {

    private static SAGame saGame;
    private static final String correct = "drive";
    private static final String correctEmptyResult = "@!214_=-";

    @BeforeClass
    public static void init() {
        saGame = SAAbstractFactory.getInstance().createSAGame();
    }

    @Test
    public void testCorrectResult() {
        //Comprobar si todos los juegos encontrados contienen la entrada del usuario
        List<TGame> games = saGame.searchAllByName(correct);

        try {
            assertFalse(games.isEmpty());
            for (TGame game : games)
                assertTrue(game.getName().toLowerCase().contains(correct));
        } catch (Exception ae) {
            fail(ae.getMessage());
        }
    }

    @Test
    public void testCorrectEmptyResult() {
        //Comprobar que devuelve una lista vacia en caso de no encontrar ningun juego
        List<TGame> games = saGame.searchAllByName(correctEmptyResult);

        try {
            assertTrue(games.isEmpty());
        } catch (Exception ae) {
            fail(ae.getMessage());
        }
    }
}
