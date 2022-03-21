package Data.Box;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import Data.Connection;
import Logic.Box.Box;
import Logic.Box.TBox;

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
	
}
