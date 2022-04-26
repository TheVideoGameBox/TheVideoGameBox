package Logic.Game;

import org.bson.types.ObjectId;

import java.util.List;

public interface SAGame {
    public List<TGame> searchAllByName(String name);

    public TGame searchOne(ObjectId _id);

    public List<TGame> random();

    public List<TGame> searchAllByPlatform(String platform);
}
