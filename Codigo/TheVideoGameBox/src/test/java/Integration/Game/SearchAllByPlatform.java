package Integration.Game;

import Logic.Game.SAGame;
import Logic.Game.TGame;
import Logic.SAAbstractFactory;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class SearchAllByPlatform {

    private static SAGame saGame;
    private static final String correct = "PC (Microsoft Windows)";
    private static final String empty = "No platform";

    @BeforeClass
    public static void init() {
        saGame = SAAbstractFactory.getInstance().createSAGame();
    }

    @Test
    public void testCorrectResult() {
        //Comprobar si todos los juegos encontrados contienen la entrada del usuario
        List<TGame> games = saGame.searchAllByPlatform(correct);

        try {
            assertFalse(games.isEmpty());
            for (TGame game : games)
                assertTrue(game.getPlatforms().contains(correct));
        } catch (Exception ae) {
            fail(ae.getMessage());
        }
    }

    @Test
    public void testIncorrectEmptyResult() {
        //Comprobar que devuelve una lista vacia en caso de no encontrar ningun juego
        List<TGame> games = saGame.searchAllByPlatform(empty);

        try {
            assertTrue(games.isEmpty());
        } catch (Exception ae) {
            fail(ae.getMessage());
        }
    }
}
