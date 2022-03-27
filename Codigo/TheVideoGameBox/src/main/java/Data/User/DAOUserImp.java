package Data.User;

import java.util.Objects;

import org.bson.types.ObjectId;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import Data.Connection;
import Logic.User.User;
import Logic.User.TUser;

public class DAOUserImp implements DAOUser {

	@Override
	public ObjectId create(TUser tUser) {
		ObjectId result;

		try {
			MongoDatabase db = Connection.getInstance().getConnection();
			MongoCollection<User> users = db.getCollection("Users", User.class);

			User user = new User(tUser);

			result = Objects.requireNonNull(users.insertOne(user).getInsertedId(), "Fail to create the user").asObjectId().getValue();
		} catch (MongoException | NullPointerException e) {
			result = null;
		}

		return result;
	}
}
