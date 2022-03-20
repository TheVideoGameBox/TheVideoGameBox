package Data.Game;

import static com.mongodb.client.model.Filters.regex;

import Data.Connection;
import Logic.Game.Game;
import Logic.Game.TGame;
import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class DAOGameImp implements DAOGame {

    public List<TGame> searchAllByName(String name) {
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

    @Override
    public TGame searchOne(String name) {
        return null;
    }
}
