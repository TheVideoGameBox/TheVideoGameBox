package Unit.Box;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.Objects;

import org.bson.types.ObjectId;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mongodb.client.MongoDatabase;

import Data.Connection;
import Logic.SAAbstractFactory;
import Logic.Box.Box;
import Logic.Box.SABox;


public class ShowBox {
	
	private static SABox saBox;
	private static ObjectId _id;
	
	@BeforeClass
    public static void init() {
        MongoDatabase db = Connection.getInstance().getConnection();
        _id = Objects.requireNonNull(db.getCollection("boxes", Box.class).find().first()).getId();
        saBox = SAAbstractFactory.getInstance().createSABox();
    }

    @Test
    public void testCorrect(){
        try {
            assertNotNull(saBox.showBox(_id));
        } catch (Exception ae) {
            fail(ae.getMessage());
        }

    }
}
