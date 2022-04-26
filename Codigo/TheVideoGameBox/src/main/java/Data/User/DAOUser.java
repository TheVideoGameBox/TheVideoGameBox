package Data.User;

import Logic.User.TUser;
import org.bson.types.ObjectId;

import java.util.List;


public interface DAOUser {
    public ObjectId create(TUser user);

    public TUser logIn(String email);

    public ObjectId addBox(ObjectId idUser, ObjectId idBox);

    public List<ObjectId> userBoxes(ObjectId id);

    void deleteUserBoxFromDatabase(ObjectId idUser, ObjectId idBox);

    void deleteFromDatabase(ObjectId id);
}
