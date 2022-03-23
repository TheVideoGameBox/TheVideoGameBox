package Logic.Box;

import Data.DAOAbstractFactory;
import Data.Box.DAOBox;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Objects;

public class SABoxImp implements SABox {

    @Override
    public ObjectId createBox(TBox box) {
        if (!correctName(box.getName()))
            return null;

        if (!correctDescription(box.getDescription()))
            return null;

        if (!correctCategory(box.getCategories()))
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
    public void deleteFromDatabase(ObjectId id){
        DAOAbstractFactory.getInstance().createDAOBox().deleteFromDatabase(id);
    }

	private boolean correctPrivacy(boolean privacy) {
		return !Objects.isNull(privacy);
	}

    private boolean correctCategory(List<Category> categories) {
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
