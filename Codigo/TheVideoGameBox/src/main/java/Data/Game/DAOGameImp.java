package Data.Game;

import Data.Connection;
import Logic.Game.Game;
import Logic.Game.TGame;
import com.mongodb.MongoException;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Aggregates;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.regex;


public class DAOGameImp implements DAOGame {

    public List<TGame> searchAllByName(String name) {
        List<TGame> result = new ArrayList<>();
        try {
            MongoDatabase db = Connection.getInstance().getConnection();
            FindIterable<Game> iter = db.getCollection("games", Game.class).find(regex("name", Pattern.compile(name, Pattern.CASE_INSENSITIVE)));
            for (Game game : iter)
                result.add(game.toTransfer());
        } catch (MongoException e) {
            result = null;
        }

        return result;
    }

    public TGame searchOne(ObjectId _id) {
        Game game = null;
        try {
            MongoDatabase db = Connection.getInstance().getConnection();
            MongoCollection<Game> collection = db.getCollection("games", Game.class);

            List<Game> games = new ArrayList<Game>();
            collection.find(eq("_id", _id)).into(games);

            if (games.size() == 1) {
                game = new Game(games.get(0).toTransfer());
            }
        } catch (MongoException e) {
            return null;
        }
        return game.toTransfer();
    }

    @Override
    public List<TGame> random() {
        List<TGame> result = new ArrayList<>();
        try {
            MongoDatabase db = Connection.getInstance().getConnection();
            AggregateIterable<Game> iter = db.getCollection("games", Game.class).aggregate(Collections.singletonList(Aggregates.sample(3)));
            for (Game game : iter)
                result.add(game.toTransfer());
        } catch (MongoException e) {
            result = null;
        }
        return result;
    }

    @Override
    public List<TGame> searchAllByPlatform(String platform) {
        List<TGame> result = new ArrayList<>();
        try {
            MongoDatabase db = Connection.getInstance().getConnection();
            FindIterable<Game> iter = db.getCollection("games", Game.class).find(eq("platforms", platform)).limit(50);
            for (Game game : iter)
                result.add(game.toTransfer());
        } catch (MongoException e) {
            result = null;
        }

        return result;
    }
}
