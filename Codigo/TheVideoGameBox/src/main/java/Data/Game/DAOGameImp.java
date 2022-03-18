package Data.Game;

import Data.Connection;
import Logic.Game.Game;
import Logic.Game.TGame;
import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;

import java.util.ArrayList;
import java.util.List;

public class DAOGameImp {

    public List<TGame> SearchAllByName(String name) {
        List<TGame> result = new ArrayList<>();
        try {
            MongoDatabase db = Connection.getInstance().getConnection();
            FindIterable<Game> iter = db.getCollection("games", Game.class).find();
            for(Game g : iter) {
                if(g.getName().toLowerCase().contains(name.toLowerCase())) {
                    result.add(g.toTransfer());
                }
            }
        } catch (MongoException e) {
            return result;
        }
        return result;
    }


}
