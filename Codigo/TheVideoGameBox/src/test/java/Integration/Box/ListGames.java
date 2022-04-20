package Integration.Box;

import Data.Connection;
import Logic.Box.Genres;
import Logic.Box.Privacy;
import Logic.Box.SABox;
import Logic.Box.TBox;
import Logic.Game.Game;
import Logic.SAAbstractFactory;
import com.mongodb.client.MongoDatabase;
import org.bson.types.ObjectId;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class ListGames {

    private static SABox saBox;
    private static ObjectId idBox;
    private static final TBox correct = new TBox("TEST_LIST_GAMES", "TEST_LIST_GAMES", Privacy.PRIVATE, new ArrayList<Genres>(Arrays.asList(Genres.RACING, Genres.INDIE)));

    @BeforeClass
    public static void init() {
        MongoDatabase db = Connection.getInstance().getConnection();
        ObjectId idGame = Objects.requireNonNull(db.getCollection("games", Game.class).find().first()).getId();
        saBox = SAAbstractFactory.getInstance().createSABox();
        idBox = saBox.createBox(correct);
        correct.setId((idBox));
        saBox.addGame(idBox, idGame);
    }

    @Test
    public void testCorrectListGames() {
        try {
            assertNotNull(saBox.listGames(correct));
        } catch (Exception ae) {
            fail(ae.getMessage());
        }
    }

    @AfterClass
    public static void after() {
        if (idBox != null) saBox.deleteFromDatabase(idBox);
    }

}
