package Integration.Game;

import Data.Connection;
import Logic.Game.Game;
import Logic.Game.SAGame;
import Logic.SAAbstractFactory;
import com.mongodb.client.MongoDatabase;
import org.bson.types.ObjectId;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Objects;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class SearchOne {

    private static SAGame saGame;
    private static ObjectId _id;

    @BeforeClass
    public static void init() {
        MongoDatabase db = Connection.getInstance().getConnection();
        _id = Objects.requireNonNull(db.getCollection("games", Game.class).find().first()).getId();
        saGame = SAAbstractFactory.getInstance().createSAGame();
    }

    @Test
    public void testCorrect() {
        try {
            assertNotNull(saGame.searchOne(_id));
        } catch (Exception ae) {
            fail(ae.getMessage());
        }

    }

}
