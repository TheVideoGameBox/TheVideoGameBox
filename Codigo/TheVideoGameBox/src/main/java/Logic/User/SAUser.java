package Logic.User;

import org.bson.types.ObjectId;

import Logic.User.TUser;

public interface SAUser {
	public ObjectId createUser(TUser user);

    void deleteFromDatabase(ObjectId id);
}
