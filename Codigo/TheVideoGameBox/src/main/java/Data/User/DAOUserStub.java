package Data.User;

import Logic.User.TUser;
import org.bson.types.ObjectId;

import java.util.Collections;
import java.util.List;

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
    public ObjectId addBox(ObjectId idUser, ObjectId idBox) {
        return ObjectId.get();
    }

    @Override
    public List<ObjectId> userBoxes(ObjectId id) {
        return Collections.emptyList();
    }

    @Override
    public void deleteUserBoxFromDatabase(ObjectId idUser, ObjectId idBox) {
    }

    @Override
    public void deleteFromDatabase(ObjectId id) {
    }
}
