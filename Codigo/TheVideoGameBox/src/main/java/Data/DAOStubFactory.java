package Data;

import Data.Box.DAOBox;
import Data.Box.DAOBoxStub;
import Data.Game.DAOGame;
import Data.Game.DAOGameStub;
import Data.User.DAOUser;
import Data.User.DAOUserStub;

public class DAOStubFactory extends DAOAbstractFactory {

    @Override
    public DAOBox createDAOBox() {
        return new DAOBoxStub();
    }

    @Override
    public DAOGame createDAOGame() {
        return new DAOGameStub();
    }

    @Override
    public DAOUser createDAOUser() {
        return new DAOUserStub();
    }
}
