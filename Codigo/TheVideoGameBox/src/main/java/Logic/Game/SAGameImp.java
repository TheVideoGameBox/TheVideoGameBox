package Logic.Game;

import Data.DAOAbstractFactory;

import java.util.ArrayList;
import java.util.List;

public class SAGameImp implements SAGame {

    @Override
    public List<TGame> SearchAllByname(String name) {
        List<TGame> result = new ArrayList<>();
        return result = DAOAbstractFactory.getInstance().createDAOGame().SearchAllByName(name);
    }

}
