package Logic.Box;

import Data.Box.DAOBox;
import Data.DAOAbstractFactory;
import Data.Game.DAOGame;
import Logic.Game.TGame;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class SABoxImp implements SABox {

    private DAOAbstractFactory daoFactory;

    public SABoxImp() {
        daoFactory = DAOAbstractFactory.getInstance();
    }

    public SABoxImp(DAOAbstractFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public ObjectId createBox(TBox box) {
        if (!correctName(box.getName()))
            return null;

        if (!correctDescription(box.getDescription()))
            return null;

        if (!correctGenres(box.getGenres()))
            return null;

        if (!correctPrivacy(box.getPrivacy()))
            return null;

        DAOBox daoBox = DAOAbstractFactory.getInstance().createDAOBox();

        return daoBox.create(box);
    }

    @Override
    public ObjectId addGame(ObjectId idBox, ObjectId gameId) {
        DAOBox daoBox = daoFactory.createDAOBox();
        return daoBox.addGame(idBox, gameId);
    }

    @Override
    public List<TGame> listGames(TBox box) {
        List<TGame> games = new ArrayList<TGame>();
        DAOBox daoBox = daoFactory.createDAOBox();
        List<ObjectId> gameIDs = daoBox.listGames(box);

        if (gameIDs == null)
            return games;

        DAOGame daoGame = daoFactory.createDAOGame();

        for (ObjectId gameID : gameIDs) {
            TGame game = daoGame.searchOne(gameID);

            if (game == null)
                return new ArrayList<>();

            games.add(game);
        }

        return games;
    }

    @Override
    public List<TBox> searchAllBoxesByName(String name) {
        if (!correctName(name))
            return null;

        DAOBox daoBox = daoFactory.createDAOBox();

        return daoBox.searchAllByName(name);
    }

    @Override
    public ObjectId deleteBox(ObjectId id) {
        DAOBox daoBox = DAOAbstractFactory.getInstance().createDAOBox();
        return daoBox.deleteBox(id);
    }

    @Override
    public void deleteFromDatabase(ObjectId id) {
        daoFactory.createDAOBox().deleteFromDatabase(id);
    }

    @Override
    public TBox showBox(ObjectId _id) {
        return DAOAbstractFactory.getInstance().createDAOBox().showBox(_id);
    }

    @Override
    public ObjectId deleteGame(ObjectId idBox, ObjectId gameId) {
        DAOBox daoBox = DAOAbstractFactory.getInstance().createDAOBox();
        return daoBox.deleteGame(idBox, gameId);
    }

    @Override
    public ObjectId modifyBox(TBox tBox) {
        if (!correctName(tBox.getName())) {
            return null;
        }
        if (!correctDescription(tBox.getDescription())) {
            return null;
        }
        if (!correctGenres(tBox.getGenres())) {
            return null;
        }
        if (!correctPrivacy(tBox.getPrivacy())) {
            return null;
        }

        DAOBox daoBox = DAOAbstractFactory.getInstance().createDAOBox();

        return daoBox.modifyBox(tBox);
    }

    private boolean correctGenres(List<Genres> categories) {
        return categories != null && !categories.isEmpty();
    }

    private boolean correctPrivacy(Privacy privacy) {
        return privacy != null;
    }

    private boolean correctDescription(String description) {
        return description != null && (description.length() > 0 && description.length() <= 250);
    }

    private boolean correctName(String name) {
        return name != null && name.length() > 0 && name.length() <= 50;
    }

}
