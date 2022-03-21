package Logic.Game;

import org.bson.types.ObjectId;

import java.util.List;

public interface SAGame {
    public List<TGame> SearchAllByName(String name);

    public TGame SearchOne(ObjectId _id);

}
