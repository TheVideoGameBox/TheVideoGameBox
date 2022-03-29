package Data.User;

import Logic.User.TUser;
import org.bson.types.ObjectId;


public interface DAOUser {
	public ObjectId create(TUser user);

    void deleteFromDatabase(ObjectId id);
}
