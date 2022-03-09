package Logic.Person;

import Data.Person.Person;
import org.bson.types.ObjectId;

import java.util.List;

public class SAPersonImp {

    public ObjectId add(TPerson tPerson) {
        if (tPerson.getNif().length() != 9)
            return null;
        else if (!tPerson.getNif().substring(0, 8).matches("^[0-9]+$"))
            return null;
        else if (!Character.isAlphabetic(tPerson.getNif().charAt(8)))
            return null;

        Person person = new Person();
        person.add(tPerson);
        return tPerson.getId();
    }

    public ObjectId delete(ObjectId _id) {
        Person person = new Person();
        person.delete(_id);
        return _id;
    }

    public List<TPerson> readAll() {
        Person person = new Person();
        return person.readAll();
    }

    public int update(TPerson tPerson) {
        Person person = new Person();
        person.update(tPerson);
        return 0;
    }
}
