package Data.Box;

import Logic.Box.Box;
import Logic.Box.TBox;
import org.bson.types.ObjectId;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import Data.Connection;

import java.util.List;
import java.util.Objects;

public interface DAOBox {
	public ObjectId create(TBox box);
	public ObjectId addGame(ObjectId idBox, ObjectId idGame);
	public List<ObjectId> listGames(TBox box);
	public void deleteFromDatabase(ObjectId id);
	public List<TBox> searchAllByName(String name);
	public ObjectId modifyBox(TBox tBox);
}
