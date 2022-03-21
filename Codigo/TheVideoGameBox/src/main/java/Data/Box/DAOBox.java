package Data.Box;

import Logic.Game.TGame;
import Logic.Box.TBox;

public interface DAOBox {
	public int create(TBox box);
	public void addGame(TBox tBox, TGame tGame);

}
