package Data.Box;

import Logic.Box.TBox;
import org.bson.types.ObjectId;

import java.util.List;

public interface DAOBox {
    public ObjectId create(TBox box);

    public ObjectId addGame(ObjectId idBox, ObjectId idGame);

    public ObjectId deleteGame(ObjectId idBox, ObjectId idGame);

    public List<ObjectId> listGames(TBox box);

    public List<TBox> searchAllByName(String name);

    public TBox showBox(ObjectId id);

    public ObjectId modifyBox(TBox tBox);

    ObjectId deleteBox(ObjectId id);

    public void deleteFromDatabase(ObjectId id);

}
