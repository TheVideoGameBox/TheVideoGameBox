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

public class AddGameToBox {

    private static SABox saBox;
    private static ObjectId idBox;
    private static ObjectId idGame;
    private static final TBox correct = new TBox("TEST_ADD_GAME", "TEST_ADD_GAME", Privacy.PRIVATE,
            new ArrayList<Genres>(Arrays.asList(Genres.RACING, Genres.INDIE)), new ArrayList<ObjectId>(), null);

    @BeforeClass
    public static void init() {
        MongoDatabase db = Connection.getInstance().getConnection();
        idGame = Objects.requireNonNull(db.getCollection("games", Game.class).find().first()).getId();
        saBox = SAAbstractFactory.getInstance().createSABox();
        idBox = saBox.createBox(correct);
    }

    @Test
    public void testCorrectAddGame() {
        try {
            assertNotNull(saBox.addGame(idBox, idGame));
        } catch (Exception ae) {
            fail(ae.getMessage());
        }
    }

    @Test
    public void testCorrectAddGameX2() {        //Por ahora se puede añadir el mismo juego infinitas veces a la box
        try {
            assertNotNull(saBox.addGame(idBox, idGame));
        } catch (Exception ae) {
            fail(ae.getMessage());
        }
    }

    @AfterClass
    public static void after() {
        if (idBox != null) saBox.deleteFromDatabase(idBox);
    }

}
