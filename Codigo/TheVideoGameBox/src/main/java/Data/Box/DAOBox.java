package Data.Box;

import Logic.Game.TGame;
import Logic.Box.TBox;
import org.bson.types.ObjectId;

public interface DAOBox {
	public int create(TBox box);
	public ObjectId addGame(TBox tBox, ObjectId idGame);

}
