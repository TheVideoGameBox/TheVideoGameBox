package Data;

import com.mongodb.MongoException;
import com.mongodb.client.MongoDatabase;

public abstract class Connection {
    private static Connection instance;

    public static Connection getInstance() {
        if(instance==null) {
            instance = new ConnectionImp();
        }

        return instance;
    }

    public abstract MongoDatabase getConnection() throws ClassNotFoundException, MongoException;

}
