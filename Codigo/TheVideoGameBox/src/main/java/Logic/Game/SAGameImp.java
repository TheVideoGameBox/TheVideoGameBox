package Logic.Game;

import Data.DAOAbstractFactory;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class SAGameImp implements SAGame {

    @Override
    public List<TGame> searchAllByName(String name) {
        if(name.length() <= 0 || name.length() > 50)
            return null;

        List<TGame> result = new ArrayList<>();
        return result = DAOAbstractFactory.getInstance().createDAOGame().searchAllByName(name);
    }

    @Override
    public TGame SearchOne(String name) {
        return DAOAbstractFactory.getInstance().createDAOGame().searchOne(name);
    }

    @Override
    public TGame SearchOne(ObjectId _id) {
        return DAOAbstractFactory.getInstance().createDAOGame().SearchOne(_id);
    }

}
