package Data.Box;

import Logic.Box.TBox;
import Logic.Game.TGame;

import java.util.List;

import org.bson.types.ObjectId;

import java.util.List;

public interface DAOBox {
	public ObjectId create(TBox box);
	public ObjectId addGame(ObjectId idBox, ObjectId idGame);
	public List<ObjectId> listGames(ObjectId idBox);
	public void deleteFromDatabase(ObjectId id);
	public List<TBox> searchAllByName(String name);
}
