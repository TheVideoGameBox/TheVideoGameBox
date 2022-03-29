package Logic.Game;

import org.bson.types.ObjectId;

import java.util.List;

public interface SAGame {
    public List<TGame> searchAllByName(String name);
    public TGame searchOne(ObjectId _id);
}
