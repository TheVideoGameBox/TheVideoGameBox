package Data;

import Data.Box.DAOBox;
import Data.Game.DAOGameImp;
import Data.User.DAOUser;

public class DAOFactory extends DAOAbstractFactory{

    @Override
    public DAOBox createDAOBox() {
        return new DAOBox();
    }

    @Override
    public DAOGameImp createDAOGame() {
        return new DAOGameImp();
    }

    @Override
    public DAOUser createDAOUser() {
        return new DAOUser();
    }
}
