package Data.Box;

import Logic.Box.TBox;
import org.bson.types.ObjectId;

public interface DAOBox {

	public ObjectId create(TBox box);
	public ObjectId addGame(ObjectId idBox, ObjectId idGame);
	public void deleteFromDatabase(ObjectId id);
}
