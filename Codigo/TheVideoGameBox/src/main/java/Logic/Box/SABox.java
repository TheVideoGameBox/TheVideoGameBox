package Logic.Box;

import Logic.Game.TGame;
import org.bson.types.ObjectId;

import java.util.List;

public interface SABox {

	public ObjectId createBox(TBox box);
	public ObjectId addGame(ObjectId idBox, ObjectId gameId);
	public List<TGame> listGames(TBox box);
	public void deleteFromDatabase(ObjectId id);
	public List<TBox> searchAllBoxesByName(String name);
	public ObjectId deleteGame(ObjectId idBox, ObjectId gameId);

}
