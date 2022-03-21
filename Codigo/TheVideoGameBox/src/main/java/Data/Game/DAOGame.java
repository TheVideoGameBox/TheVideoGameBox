package Data.Game;

import Logic.Game.TGame;

import java.util.List;

import org.bson.types.ObjectId;

public interface DAOGame {
    public List<TGame> SearchAllByName(String name);
    public TGame SearchOne(ObjectId id);
}
