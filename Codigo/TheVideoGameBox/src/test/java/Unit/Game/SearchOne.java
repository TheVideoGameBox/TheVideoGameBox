package Unit.Game;

import Data.Connection;
import Logic.Game.Game;
import Logic.Game.SAGame;
import Logic.Game.TGame;
import Logic.SAAbstractFactory;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.types.ObjectId;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Objects;

import static org.junit.Assert.*;

public class SearchOne {

    private static SAGame saGame;
    private static ObjectId _id;

    @BeforeClass
    public static void init() {
        MongoDatabase db = Connection.getInstance().getConnection();
        MongoCollection<Game> games = db.getCollection("games", Game.class);
        _id = Objects.requireNonNull(games.find().first()).getId();
        saGame = SAAbstractFactory.getInstance().createSAGame();
    }

    @Test
    public void testCorrect(){
        TGame tGame = saGame.searchOne(_id);
        assertNotNull(tGame);
    }

}
