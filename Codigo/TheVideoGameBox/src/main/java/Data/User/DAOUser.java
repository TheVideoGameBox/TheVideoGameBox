package Data.User;

import org.bson.types.ObjectId;

import Logic.User.TUser;


public interface DAOUser {
	public ObjectId create(TUser user);

    void deleteFromDatabase(ObjectId id);
}
