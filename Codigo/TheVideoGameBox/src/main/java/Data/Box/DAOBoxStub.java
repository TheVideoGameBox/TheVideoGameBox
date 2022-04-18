package Data.Box;

import Logic.Box.Genres;
import Logic.Box.Privacy;
import Logic.Box.TBox;
import org.bson.types.ObjectId;

import java.util.ArrayList;
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
    public List<ObjectId> listGames(TBox tBox) {
        return new ArrayList<ObjectId>();
    }

    @Override
    public List<TBox> searchAllByName(String name) {
        return new ArrayList<TBox>();
    }

    @Override
    public TBox showBox(ObjectId id) {
        return new TBox(null, null, null, null);
    }

    @Override
    public ObjectId deleteBox(TBox box) {
        return ObjectId.get();
    }

    @Override
    public void deleteFromDatabase(ObjectId id) {
    }
}
