package Data;

import Data.Box.DAOBox;
import Data.Box.DAOBoxImp;
import Data.Game.DAOGame;
import Data.Game.DAOGameImp;
import Data.User.DAOUser;
import Data.User.DAOUserImp;

public class DAOFactory extends DAOAbstractFactory {

    @Override
    public DAOBox createDAOBox() {
        return new DAOBoxImp();
    }

    @Override
    public DAOGame createDAOGame() {
        return new DAOGameImp();
    }

    @Override
    public DAOUser createDAOUser() {
        return new DAOUserImp();
    }
}
