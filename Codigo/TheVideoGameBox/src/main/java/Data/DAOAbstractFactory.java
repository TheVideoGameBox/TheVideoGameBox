package Data;

import Data.Box.DAOBox;
import Data.Game.DAOGame;
import Data.User.DAOUser;

public abstract class DAOAbstractFactory {

    private static DAOAbstractFactory instance;

    public static DAOAbstractFactory getInstance() {
        if (instance == null)
            instance = new DAOFactory();
        return instance;
    }

    public static void setInstance(DAOAbstractFactory instance) {
        DAOAbstractFactory.instance = instance;
    }

    public abstract DAOBox createDAOBox();

    public abstract DAOGame createDAOGame();

    public abstract DAOUser createDAOUser();

}
