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

import static org.junit.Assert.*;

//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
// Mejor que los tests no dependan de otros
//Podriamos querer probar un test sin tener que ejecutar otro
public class DeleteGameFromBox {
    private static SABox saBox;
    private static ObjectId idBox;
    private static ObjectId idGame;
    private static final TBox correct = new TBox("TEST_DELETE_GAME", "TEST_DELETE_GAME", Privacy.PRIVATE,
            new ArrayList<Logic.Box.Genres>(Arrays.asList(Genres.RACING, Genres.INDIE)), new ArrayList<ObjectId>(), null);

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
            assertTrue(saBox.listGames(correct).get(0).getId().equals(idGame));
            assertNotNull(saBox.deleteGame(idBox, idGame));
            assertTrue(saBox.listGames(correct).isEmpty());
            saBox.addGame(idBox, idGame);
        } catch (Exception ae) {
            fail(ae.getMessage());
        }
    }

//  No tiene sentido este test teniendo en cuenta los criterios de aceptacion
//    @Test
//    public void testCorrectDeleteGame_2() { // Elemento ya eliminado
//        try {
//            saBox.deleteGame(idBox, idGame);
//
//            assertTrue(saBox.listGames(correct).isEmpty());
//            assertNull(saBox.deleteGame(idBox, idGame));
//            assertTrue(saBox.listGames(correct).isEmpty());
//        } catch (Exception ae) {
//            fail(ae.getMessage());
//        }
//    }

    @AfterClass
    public static void after() {
        if (idBox != null) saBox.deleteFromDatabase(idBox);
    }
}
