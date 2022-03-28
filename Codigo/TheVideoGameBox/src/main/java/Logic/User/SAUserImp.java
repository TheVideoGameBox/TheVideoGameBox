package Logic.User;

import Data.DAOAbstractFactory;
import Data.User.DAOUser;
import org.bson.types.ObjectId;

public class SAUserImp implements SAUser {

	@Override
	public ObjectId createUser(TUser user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteFromDatabase(ObjectId id) {
		DAOAbstractFactory.getInstance().createDAOUser().deleteFromDatabase(id);
	}
}
