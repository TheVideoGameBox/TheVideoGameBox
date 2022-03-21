package Game;

import Logic.Game.SAGame;
import Logic.Game.TGame;
import Logic.SAAbstractFactory;
import org.bson.types.ObjectId;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class SearchOne {

    private static SAGame saGame;
    private final ObjectId _id = new ObjectId("62370c135c37793c661d73bf");

    @BeforeClass
    public static void init(){
        saGame = SAAbstractFactory.getInstance().createSAGame();
    }

    @Test
    public void testCorrect(){
        TGame tGame = saGame.SearchOne(_id);
        assertNotNull(tGame);
    }

}
