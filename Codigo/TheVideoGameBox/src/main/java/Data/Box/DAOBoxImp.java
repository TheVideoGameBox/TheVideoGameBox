package Data.Box;

import static com.mongodb.client.model.Filters.eq;
import org.bson.types.ObjectId;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Updates;

import Data.Connection;
import Logic.Box.Box;
import Logic.Box.TBox;

import java.util.ArrayList;
import java.util.List;

public class DAOBoxImp implements DAOBox {

	public int create(TBox box) {
		int result=-1;
	        try {
	            MongoDatabase db = Connection.getInstance().getConnection();
	            MongoCollection<Box> boxes = db.getCollection("boxes",Box.class);
	            Box insert = new Box(box.getName(), box.getDescription(), box.getPrivacy(), box.getCategory());
				insert.setActive(true);
	            boxes.insertOne(insert);
	            
	            result=1;
	            
	        } catch (MongoException e) {
	            return result;
	        }
		return result;
	}

	@Override
	public ObjectId addGame(TBox tBox, ObjectId idGame) {
		List<ObjectId> gameList = new ArrayList<>();
		try {
			MongoDatabase db = Connection.getInstance().getConnection();
			Box box = db.getCollection("boxes", Box.class).find(eq("_id", tBox.getId())).first();
			gameList = box.getGameList();
			gameList.add(idGame);
			db.getCollection("boxes", Box.class).updateOne(eq("_id", tBox.getId()), Updates.set("gameList", gameList));
		} catch (MongoException e) {
			return null;
		}
		return idGame;
	}
}
