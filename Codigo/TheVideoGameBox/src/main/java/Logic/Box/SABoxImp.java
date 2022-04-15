package Logic.Box;

import Data.Box.DAOBox;
import Data.DAOAbstractFactory;
import Data.Game.DAOGame;
import Logic.Game.TGame;
import org.bson.types.ObjectId;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SABoxImp implements SABox {

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
        DAOBox daoBox = DAOAbstractFactory.getInstance().createDAOBox();
        return daoBox.addGame(idBox, gameId);
    }

    @Override
    public List<TGame> listGames(TBox box) {
        List<TGame> games = new ArrayList<TGame>();
        DAOBox daoBox = DAOAbstractFactory.getInstance().createDAOBox();
        List<ObjectId> gameIDs = daoBox.listGames(box);

        if(gameIDs == null)
            return games;

        DAOGame daoGame = DAOAbstractFactory.getInstance().createDAOGame();

        for(ObjectId gameID : gameIDs){
            TGame game = daoGame.searchOne(gameID);

            if(game == null)
                return new ArrayList<>();

            games.add(game);
        }

        return games;
    }

    @Override
    public List<TBox> searchAllBoxesByName(String name) {
        if(!correctName(name))
            return null;

        DAOBox daoBox = DAOAbstractFactory.getInstance().createDAOBox();

        return daoBox.searchAllByName(name);
    }

    @Override
    public void deleteFromDatabase(ObjectId id){
        DAOAbstractFactory.getInstance().createDAOBox().deleteFromDatabase(id);
    }
    
    @Override
	public ObjectId deleteGame(ObjectId idBox, ObjectId gameId) {
		DAOBox daoBox = DAOAbstractFactory.getInstance().createDAOBox();
		return daoBox.deleteGame(idBox, gameId);
	}

	private boolean correctPrivacy(boolean privacy) {
		return !Objects.isNull(privacy);
	}

    private boolean correctGenres(List<Genres> categories) {
        return categories != null && !categories.isEmpty();
    }

    private boolean correctPrivacy(Privacy privacy){
        return privacy != null;
    }

    private boolean correctDescription(String description) {
        return description != null && (description.length() > 0 && description.length() <= 250);
    }

    private boolean correctName(String name) {
        return name != null && name.length() > 0 && name.length() <= 50;
    }
}
