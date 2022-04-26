package Logic.User;

import Logic.Box.TBox;
import org.bson.types.ObjectId;

import java.util.List;

public interface SAUser {
    public ObjectId createUser(TUser user);

    public ObjectId logIn(TUser user);

    public ObjectId addBox(ObjectId idUser, ObjectId idBox);

    public List<TBox> userBoxes(ObjectId id);

    void deleteUserBoxFromDatabase(ObjectId idUser, ObjectId idBox);

    void deleteFromDatabase(ObjectId id);
}
