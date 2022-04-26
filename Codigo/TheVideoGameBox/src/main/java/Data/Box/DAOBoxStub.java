package Data.Box;

import Logic.Box.TBox;
import org.bson.types.ObjectId;

import java.util.Collections;
import java.util.List;

public class DAOBoxStub implements DAOBox {
    @Override
    public ObjectId create(TBox box) {
        return ObjectId.get();
    }

    @Override
    public ObjectId addGame(ObjectId idBox, ObjectId idGame) {
        return ObjectId.get();
    }

    @Override
    public ObjectId deleteGame(ObjectId idBox, ObjectId idGame) {
        return ObjectId.get();
    }

    @Override
    public List<ObjectId> listGames(TBox tBox) {
        return Collections.emptyList();
    }

    @Override
    public List<TBox> searchAllByName(String name) {
        return Collections.emptyList();
    }

    @Override
    public TBox showBox(ObjectId id) {
        return new TBox(null, null, null, null);
    }

    @Override
    public ObjectId modifyBox(TBox tBox) {
        return null;
    }

    @Override
    public ObjectId deleteBox(ObjectId id) {
        return ObjectId.get();
    }

    @Override
    public void deleteFromDatabase(ObjectId id) {
    }
}
