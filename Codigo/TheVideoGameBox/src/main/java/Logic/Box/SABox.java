package Logic.Box;

import org.bson.types.ObjectId;

import java.util.List;

public interface SABox {

	public ObjectId createBox(TBox box);
	public ObjectId addGame(ObjectId idBox, ObjectId gameId);
	public List<ObjectId> listGames(ObjectId idBox);
	public void deleteFromDatabase(ObjectId id);
	public List<TBox> searchAllBoxesByName(String name);

}
