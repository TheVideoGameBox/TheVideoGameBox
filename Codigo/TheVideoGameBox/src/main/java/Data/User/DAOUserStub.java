package Data.User;

import Logic.User.TUser;
import org.bson.types.ObjectId;

public class DAOUserStub implements DAOUser {

    @Override
    public ObjectId create(TUser tUser) {
        return ObjectId.get();
    }

    @Override
    public TUser logIn(String email) {
        return new TUser(null, null, null);
    }

    @Override
    public void deleteFromDatabase(ObjectId id) {
    }
}
