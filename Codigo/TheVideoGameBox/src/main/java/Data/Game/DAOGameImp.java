package Data.Game;

import static com.mongodb.client.model.Filters.regex;

import Data.Connection;
import Logic.Game.Game;
import Logic.Game.TGame;
import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.bson.types.ObjectId;
import static com.mongodb.client.model.Filters.eq;


public class DAOGameImp implements DAOGame {

    public List<TGame> SearchAllByName(String name) {
        List<TGame> result = new ArrayList<>();
        try {
            MongoDatabase db = Connection.getInstance().getConnection();
            FindIterable<Game> iter = db.getCollection("games", Game.class).find(regex("name", Pattern.compile(name, Pattern.CASE_INSENSITIVE)));
            for(Game game : iter)
                result.add(game.toTransfer());
        } catch (MongoException e) {
            result = null;
        }

        return result;
    }

    public TGame SearchOne(ObjectId _id) {
		Game game = null;
		try {
			MongoDatabase db = Connection.getInstance().getConnection();
			MongoCollection<Game> collection = db.getCollection("games", Game.class);

			List<Game> games = new ArrayList<Game>();
			collection.find(eq("_id", _id)).into(games);

			if (games.size() == 1) {
				game = new Game(games.get(0).toTransfer());
			}
		}
		catch(MongoException e) {
			return null;
		}
    	return game.toTransfer();
    }
}
