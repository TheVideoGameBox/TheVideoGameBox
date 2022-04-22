package Unit.Box;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;

import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mongodb.client.MongoDatabase;

import Data.Connection;
import Logic.Box.Genres;
import Logic.Box.Privacy;
import Logic.Box.SABox;
import Logic.Box.SABoxImp;
import Logic.Box.TBox;

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
