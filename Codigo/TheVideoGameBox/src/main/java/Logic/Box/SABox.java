package Logic.Box;

import org.bson.types.ObjectId;

public interface SABox {

	public ObjectId createBox(TBox box);
	public ObjectId addGame(TBox tBox, ObjectId gameId);
	public void deleteFromDatabase(ObjectId id);

}
