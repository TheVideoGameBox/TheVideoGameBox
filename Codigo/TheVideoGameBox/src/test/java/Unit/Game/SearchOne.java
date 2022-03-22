package Unit.Game;

import Logic.Game.SAGame;
import Logic.Game.TGame;
import Logic.SAAbstractFactory;
import org.bson.types.ObjectId;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class SearchOne {

    private static SAGame saGame;
    private final ObjectId _id = new ObjectId("623a0636207a93d657bf5f8b");

    @BeforeClass
    public static void init(){
        saGame = SAAbstractFactory.getInstance().createSAGame();
    }

    @Test
    public void testCorrect(){
        TGame tGame = saGame.searchOne(_id);
        assertNotNull(tGame);
    }

}
