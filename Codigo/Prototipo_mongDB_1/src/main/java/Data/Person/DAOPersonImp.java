package Data.Person;

import Data.Connection;
import Logic.Person.TPerson;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Collections;
import java.util.List;

public class DAOPersonImp implements DAOPerson {

    @Override
    public int add(TPerson tPerson) {
        try {
            MongoDatabase db = Connection.getInstance().getConnection();
            Document person = new Document();
            System.out.print(tPerson);
            person.append("_id", tPerson.getId());
            person.append("nif", tPerson.getNif());
            person.append("name", tPerson.getNombre());
            person.append("apellidos", tPerson.getApellidos());
            person.append("activo", true);

            db.getCollection("personas").insertOne(person);
        } catch (Exception e) {
            return 0;
        }

        return tPerson.getId();
    }

    @Override
    public int delete(int id) {
        return 0;
    }

    @Override
    public TPerson read(int id) {
        return null;
    }

    @Override
    public List<TPerson> readAll() {
        try {
            MongoDatabase db = Connection.getInstance().getConnection();
            db.getCollection("personas");



        } catch (Exception e) {
            return null;
        }

        return Collections.emptyList();
    }

    @Override
    public int update(TPerson tPerson) {
        return 0;
    }
}
