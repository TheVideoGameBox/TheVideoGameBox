package Logic.Box;

import org.bson.types.ObjectId;

public interface SABox {
	public int createBox(TBox box);
	public ObjectId addGame(TBox tBox, ObjectId gameId);
}
