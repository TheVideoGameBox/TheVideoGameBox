package Data.User;

import java.util.Objects;

import org.bson.types.ObjectId;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import Data.Connection;
import Logic.User.User;
import Logic.User.TUser;

import static com.mongodb.client.model.Filters.eq;

public class DAOUserImp implements DAOUser {

	@Override
	public ObjectId create(TUser tUser) {
		ObjectId result;

		try {
			MongoDatabase db = Connection.getInstance().getConnection();
			MongoCollection<User> users = db.getCollection("users", User.class);

			User user = new User(tUser);

			result = Objects.requireNonNull(users.insertOne(user).getInsertedId(), "Fail to create the user").asObjectId().getValue();
		} catch (MongoException | NullPointerException e) {
			result = null;
		}

		return result;
	}

	@Override
	public void deleteFromDatabase(ObjectId id) {
		try {
			MongoDatabase db = Connection.getInstance().getConnection();
			MongoCollection<User> boxes = db.getCollection("users", User.class);
			boxes.deleteOne(eq("_id", id));
		} catch (MongoException e) {

		}
	}
}
