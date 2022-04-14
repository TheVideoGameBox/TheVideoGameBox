package Data.Box;

import Data.Connection;
import Logic.Box.Box;
import Logic.Box.TBox;
import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Updates;

import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.regex;

public class DAOBoxImp implements DAOBox {

	public ObjectId create(TBox box) {
		ObjectId result;

		try {
			MongoDatabase db = Connection.getInstance().getConnection();
			MongoCollection<Box> boxes = db.getCollection("boxes", Box.class);

			Box insert = new Box(box);

			result = Objects.requireNonNull(boxes.insertOne(insert).getInsertedId()).asObjectId().getValue();
		} catch (MongoException | NullPointerException e) {
			result = null;
		}

		return result;
	}

	@Override
	public ObjectId addGame(ObjectId idBox, ObjectId idGame) {
		List<ObjectId> gameList = new ArrayList<>();
		try {
			MongoDatabase db = Connection.getInstance().getConnection();
			Box box = db.getCollection("boxes", Box.class).find(eq("_id", idBox)).first();
			if(box.getGameList() != null) gameList = box.getGameList();
			gameList.add(idGame);
			db.getCollection("boxes", Box.class).updateOne(eq("_id", idBox), Updates.set("gameList", gameList));
		} catch (MongoException e) {
			return null;
		}
		return idGame;
	}
	
	

	@Override
	public ObjectId deleteGame(ObjectId idBox, ObjectId idGame) {
		List<ObjectId> gameList = new ArrayList<>();
		try {
			MongoDatabase db = Connection.getInstance().getConnection();
			Box box = db.getCollection("boxes", Box.class).find(eq("_id",idBox)).first();
			if(box.getGameList() !=null) 
				gameList = box.getGameList();
			gameList.remove(idGame);
			db.getCollection("boxes", Box.class).updateOne(eq("_id", idBox), Updates.set("gameList", gameList));
		}
		catch(MongoException e) {
			return null;
		}
		return idGame;
	}

	@Override
	public List<ObjectId> listGames(TBox tBox) {
		List<ObjectId> gameList;
		try {
			MongoDatabase db = Connection.getInstance().getConnection();
			Box box = db.getCollection("boxes", Box.class).find(eq("_id", tBox.getId())).first();
			gameList = box.getGameList();
			if(box.getGameList() == null) return null;
		} catch (MongoException e) {
			return null;
		}
		return gameList;
	}

	@Override
    public void deleteFromDatabase(ObjectId id) {
        try {
            MongoDatabase db = Connection.getInstance().getConnection();
            MongoCollection<Box> boxes = db.getCollection("boxes", Box.class);

            boxes.deleteOne(eq("_id", id));
        } catch (MongoException e) {

        }
    }

	@Override
	public List<TBox> searchAllByName(String name) {
		List<TBox> result = new ArrayList<>();
			try {
				MongoDatabase db = Connection.getInstance().getConnection();
				FindIterable<Box> iter = db.getCollection("boxes", Box.class).find(regex("name", Pattern.compile(name, Pattern.CASE_INSENSITIVE)));
		        for (Box box : iter)
		        	result.add(box.toTransfer());
		    } catch (MongoException e) {
		    	result = null;
		    }
			
		return result;
	}
}
