package Logic.Box;

import Data.DAOAbstractFactory;
import Logic.Game.TGame;

import java.util.ArrayList;
import java.util.List;

public class SABoxImp implements SABox {

    @Override
    public List<TGame> SearchAllByname(String name) {
        List<TGame> result = new ArrayList<>();
        return result = DAOAbstractFactory.getInstance().createDAOGame().SearchAllByName(name);
    }
}
