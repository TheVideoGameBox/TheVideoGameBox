package Integration.Box;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import org.bson.types.ObjectId;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.mongodb.client.MongoDatabase;

import Data.Connection;
import Logic.SAAbstractFactory;
import Logic.Box.Genres;
import Logic.Box.Privacy;
import Logic.Box.SABox;
import Logic.Box.TBox;
import Logic.Game.Game;

@FixMethodOrder( MethodSorters.NAME_ASCENDING )
public class DeleteGameFromBox {
    private static SABox saBox;
    private static ObjectId idBox;
    private static ObjectId idGame;
    private static final TBox correct = new TBox("TEST_DELETE_GAME", "TEST_DELETE_GAME", Privacy.PRIVATE,
            new ArrayList<Logic.Box.Genres>(Arrays.asList(Genres.RACING, Genres.INDIE)), new ArrayList<ObjectId>());

    @BeforeClass
    public static void init() {
        MongoDatabase db = Connection.getInstance().getConnection();
        idGame = Objects.requireNonNull(db.getCollection("games", Game.class).find().first()).getId();
        saBox = SAAbstractFactory.getInstance().createSABox();
        idBox = saBox.createBox(correct);
        correct.setId(idBox);
        
        saBox.addGame(idBox, idGame);
        
    }

    @Test
    public void testCorrectDeleteGame_1() {
        try {
        	assertTrue(saBox.listGames(correct).contains(idGame));
            assertNotNull(saBox.deleteGame(idBox, idGame));
            assertFalse(saBox.listGames(correct).contains(idGame));
        } catch (Exception ae) {
            fail(ae.getMessage());
        }
    }
    
    @Test
    public void testCorrectDeleteGame_2() { // Elemento ya eliminado
        try {
        	assertFalse(saBox.listGames(correct).contains(idGame));
            assertNotNull(saBox.deleteGame(idBox, idGame));
            assertFalse(saBox.listGames(correct).contains(idGame));
        } catch (Exception ae) {
            fail(ae.getMessage());
        }
    }

    @AfterClass
    public static void after() {
        if (idBox != null) saBox.deleteFromDatabase(idBox);
    }
}
