package Data.User;

import Logic.Box.TBox;
import Logic.User.TUser;
import org.bson.types.ObjectId;

import java.util.ArrayList;
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
        return null;
    }

    @Override
    public List<ObjectId> userBoxes(ObjectId id) {
        return null;
    }

    @Override
    public void deleteUserBoxFromDatabase(ObjectId idUser, ObjectId idBox) {
    }

    @Override
    public void deleteFromDatabase(ObjectId id) {
    }

	@Override
	public List<TUser> searchAllByName(String name) {
		return new ArrayList<TUser>();
	}
}
