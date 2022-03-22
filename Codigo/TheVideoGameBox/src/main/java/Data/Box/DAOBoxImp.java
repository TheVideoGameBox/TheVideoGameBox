package Data.Box;

import org.apache.commons.lang3.ObjectUtils;
import org.bson.types.ObjectId;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import Data.Connection;
import Logic.Box.Box;
import Logic.Box.TBox;

import static com.mongodb.client.model.Filters.eq;

public class DAOBoxImp implements DAOBox {

    public ObjectId create(TBox box) {
        ObjectId result;

        try {
            MongoDatabase db = Connection.getInstance().getConnection();
            MongoCollection<Box> boxes = db.getCollection("boxes", Box.class);

            Box insert = new Box(box);

            result = boxes.insertOne(insert).getInsertedId().asObjectId().getValue();
        } catch (MongoException | NullPointerException e) {
            result = null;
        }

        return result;
    }

    @Override
    public void deleteFromDatabase(ObjectId id) {
        try {
            MongoDatabase db = Connection.getInstance().getConnection();
            MongoCollection<Box> boxes = db.getCollection("boxes", Box.class);

            boxes.deleteOne(eq("_id", id));
        } catch (MongoException e) {

        }
    }
}
