package Unit.Game;

import Logic.Game.SAGame;
import Logic.Game.TGame;
import Logic.SAAbstractFactory;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.fail;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import java.util.List;

public class SearchAllByName {

    private static SAGame saGame;

    private static final String correct = "lego";
    private static final String correctEmptyResult = "@!214_=-";
    private static final String limit = new String(new char[51]).replace('\0', 'f');
    private static final String empty = "";

    @BeforeClass
    public static void init(){
        saGame = SAAbstractFactory.getInstance().createSAGame();
    }

    @Test
    public void testCorrectResult(){
        //Comprobar si todos los juegos encontrados contienen la entrada del usuario
        List<TGame> games = saGame.searchAllByName(correct);

        try{
            assertFalse(games.isEmpty());
            for(TGame game : games)
                assertTrue(game.getName().toLowerCase().contains(correct));
        }catch (Exception ae){
            fail(ae.getMessage());
        }
    }

    @Test
    public void testCorrectEmptyResult(){
        //Comprobar que devuelve una lista vacia en caso de no encontrar ningun juego
        List<TGame> games = saGame.searchAllByName(correctEmptyResult);

        try{
            assertTrue(games.isEmpty());
        }catch (Exception ae){
            fail(ae.getMessage());
        }
    }

    @Test
    public void testIncorrectInputCharLimit(){
        //Comprobar si el usuario introduce mas de 50 caracteres, se produce un fallo
        List<TGame> games = saGame.searchAllByName(limit);

        try{
            assertNull(games);
        }catch (Exception ae){
            fail(ae.getMessage());
        }
    }

    @Test
    public void testIncorrectInputEmptyName(){
        //Comprobar si el usuario introduce 0 caracteres, se produce un fallo
        List<TGame> games = saGame.searchAllByName(empty);

        try{
            assertNull(games);
        }catch (Exception ae){
            fail(ae.getMessage());
        }
    }
}
