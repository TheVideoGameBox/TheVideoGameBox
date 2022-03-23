package Logic.Box;

import org.bson.types.ObjectId;

public interface SABox {

	public ObjectId createBox(TBox box);
	public ObjectId addGame(ObjectId idBox, ObjectId gameId);
	public void deleteFromDatabase(ObjectId id);

}
