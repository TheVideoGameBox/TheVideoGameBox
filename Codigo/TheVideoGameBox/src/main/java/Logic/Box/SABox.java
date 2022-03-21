package Logic.Box;

import org.bson.types.ObjectId;

public interface SABox {

	public ObjectId createBox(TBox box);
	public void deleteFromDatabase(ObjectId id);
}
