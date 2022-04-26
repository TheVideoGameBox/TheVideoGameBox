package Data.Game;

import Logic.Game.TGame;
import org.bson.types.ObjectId;

import java.util.List;

public interface DAOGame {
    public List<TGame> searchAllByName(String name);

    public TGame searchOne(ObjectId id);

    public List<TGame> random();

    public List<TGame> searchAllByPlatform(String platform);
}
