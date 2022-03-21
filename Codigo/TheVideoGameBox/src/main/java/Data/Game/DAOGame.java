package Data.Game;

import Logic.Game.TGame;

import java.util.List;

import org.bson.types.ObjectId;

public interface DAOGame {
    public List<TGame> searchAllByName(String name);
    public TGame searchOne(ObjectId id);
}
