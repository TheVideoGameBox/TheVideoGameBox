package Logic.User;

import org.bson.types.ObjectId;

public interface SAUser {
	public ObjectId createUser(TUser user);

    void deleteFromDatabase(ObjectId id);
}
