package Logic.User;

import org.bson.types.ObjectId;

public interface SAUser {
	public ObjectId createUser(TUser user);
    public ObjectId logIn(TUser user);
    public ObjectId addBox(ObjectId idUser, ObjectId idBox);
    void deleteFromDatabase(ObjectId id);
}
