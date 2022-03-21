package Data.Box;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.MongoException;
import com.mongodb.client.MongoDatabase;

import Data.Connection;
import Logic.Box.TBox;

public interface DAOBox {

	public int create(TBox box);

}
