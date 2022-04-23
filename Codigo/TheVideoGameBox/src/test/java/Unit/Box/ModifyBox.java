package Unit.Box;

import Data.Connection;
import Logic.Box.*;
import com.mongodb.client.MongoDatabase;
import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class ModifyBox {

	private static SABox saBox;
	private static ObjectId idBox;
	private static final TBox correct = new TBox("TEST_MODIFY_BOX", "TEST_MODIFY_BOX", Privacy.PRIVATE,
			new ArrayList<Logic.Box.Genres>(Arrays.asList(Genres.HORROR, Genres.RACING, Genres.SHOOTER)));
	private static TBox box;
	
	@BeforeClass
	public static void init() {
		MongoDatabase db = Connection.getInstance().getConnection();
		saBox = new SABoxImp();
	}
	
	@Test
	public void testCorrectModifyBox() {
		idBox = saBox.createBox(correct);
		
		try {
			box = correct;
			box.setName("TestCorrectModifyBox");
			assertNotNull(idBox);
		}
		catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@After
	public void after() {
		if (correct != null) {
			saBox.deleteFromDatabase(idBox);
		}
	}
}
