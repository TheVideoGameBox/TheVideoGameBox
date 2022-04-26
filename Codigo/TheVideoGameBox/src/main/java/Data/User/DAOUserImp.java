package Data.User;

import Data.Connection;
import Logic.Box.Box;
import Logic.User.TUser;
import Logic.User.User;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;
import com.mongodb.client.model.Updates;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.mongodb.client.model.Filters.eq;

public class DAOUserImp implements DAOUser {

    @Override
    public ObjectId create(TUser tUser) {
        ObjectId result;
        try {
            MongoDatabase db = Connection.getInstance().getConnection();
            MongoCollection<User> users = db.getCollection("users", User.class);

            User insert = new User(tUser);

            users.createIndex(Indexes.ascending("username"), new IndexOptions().unique(true));
            users.createIndex(Indexes.ascending("email"), new IndexOptions().unique(true));
            result = Objects.requireNonNull(users.insertOne(insert).getInsertedId()).asObjectId().getValue();
        } catch (MongoException | NullPointerException e) {
            result = null;
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public TUser logIn(String email) {
        TUser tUser;
        try {
            MongoDatabase db = Connection.getInstance().getConnection();
            tUser = Objects.requireNonNull(db.getCollection("users", User.class).find(eq("email", email)).first()).toTransfer();
        } catch (MongoException e) {
            tUser = null;
        }
        return tUser;
    }

    @Override
    public ObjectId addBox(ObjectId idUser, ObjectId idBox) {
        List<ObjectId> boxList = new ArrayList<>();
        try {
            MongoDatabase db = Connection.getInstance().getConnection();
            User user = Objects.requireNonNull(db.getCollection("users", User.class).find(eq("_id", idUser)).first());
            if (user.getBoxList() != null) boxList = user.getBoxList();
            boxList.add(idBox);
            db.getCollection("users", Box.class).updateOne(eq("_id", idUser), Updates.set("boxList", boxList));
        } catch (MongoException e) {
            return null;
        }
        return idBox;
    }

    @Override
    public List<ObjectId> userBoxes(ObjectId id) {
        List<ObjectId> boxes = new ArrayList<>();
        try {
            MongoDatabase db = Connection.getInstance().getConnection();
            boxes = Objects.requireNonNull(Objects.requireNonNull(db.getCollection("users", User.class).find(eq("_id", id))).first()).getBoxList();
        } catch (MongoException e) {
            return boxes;
        }
        return boxes;
    }

    @Override
    public void deleteUserBoxFromDatabase(ObjectId idUser, ObjectId idBox) {
        List<ObjectId> boxList = new ArrayList<>();
        try {
            MongoDatabase db = Connection.getInstance().getConnection();
            boxList = Objects.requireNonNull(db.getCollection("users", User.class).find(eq("_id", idUser)).first()).getBoxList();
            if (boxList != null)
                boxList.remove(idBox);
            Objects.requireNonNull(db.getCollection("users", User.class).findOneAndUpdate(eq("_id", idUser), Updates.set("boxList", boxList)));
        } catch (MongoException e) {

        }
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
