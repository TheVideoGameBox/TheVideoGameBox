package Data.User;

import Logic.User.TUser;
import org.bson.types.ObjectId;


public interface DAOUser {
	public ObjectId create(TUser user);
    public TUser logIn(String email);
    void deleteFromDatabase(ObjectId id);
}
