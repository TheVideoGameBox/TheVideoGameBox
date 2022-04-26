package Logic.Game;

import Data.DAOAbstractFactory;
import org.bson.types.ObjectId;

import java.util.List;

public class SAGameImp implements SAGame {

    private DAOAbstractFactory daoFactory;

    public SAGameImp() {
        daoFactory = DAOAbstractFactory.getInstance();
    }

    public SAGameImp(DAOAbstractFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public List<TGame> searchAllByName(String name) {
        if (name.length() <= 0 || name.length() > 25)
            return null;

        return daoFactory.createDAOGame().searchAllByName(name);
    }

    @Override
    public TGame searchOne(ObjectId _id) {
        return daoFactory.createDAOGame().searchOne(_id);
    }

    @Override
    public List<TGame> random() {
        return daoFactory.createDAOGame().random();
    }

    @Override
    public List<TGame> searchAllByPlatform(String platform) {
        return DAOAbstractFactory.getInstance().createDAOGame().searchAllByPlatform(platform);
    }

}
