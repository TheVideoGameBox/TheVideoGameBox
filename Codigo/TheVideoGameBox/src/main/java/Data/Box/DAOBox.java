package Data.Box;

import Logic.Box.TBox;
import Logic.Game.TGame;

import java.util.List;

import org.bson.types.ObjectId;

public interface DAOBox {
	public ObjectId create(TBox box);
	public ObjectId addGame(ObjectId idBox, ObjectId idGame);
	public void deleteFromDatabase(ObjectId id);
	public List<TBox> searchAllByName(String name);
}
