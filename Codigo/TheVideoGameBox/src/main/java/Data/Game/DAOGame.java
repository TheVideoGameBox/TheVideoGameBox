package Data.Game;

import Data.DAOAbstractFactory;
import Logic.Game.TGame;

import java.util.List;

public interface DAOGame {
    public List<TGame> searchAllByName(String name);
    public TGame searchOne(String name);
}
