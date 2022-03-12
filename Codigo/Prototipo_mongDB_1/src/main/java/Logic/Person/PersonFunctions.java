package Logic.Person;

import Data.Person.PersonData;
import org.bson.types.ObjectId;

import java.util.List;

public class PersonFunctions {

    public ObjectId add(TPerson tPerson) {
        if (tPerson.getNif().length() != 9)
            return null;
        else if (!tPerson.getNif().substring(0, 8).matches("^[0-9]+$"))
            return null;
        else if (!Character.isAlphabetic(tPerson.getNif().charAt(8)))
            return null;

        PersonData personData = new PersonData();
        personData.add(tPerson);
        return tPerson.getId();
    }

    public ObjectId delete(ObjectId _id) {
        PersonData personData = new PersonData();
        personData.delete(_id);
        return _id;
    }

    public List<TPerson> readAll() {
        PersonData personData = new PersonData();
        return personData.readAll();
    }

    public int update(TPerson tPerson) {
        PersonData personData = new PersonData();
        personData.update(tPerson);
        return 0;
    }

    public void deleteFromDataBase(ObjectId id){
        PersonData personData = new PersonData();
        personData.deleteFromDataBase(id);
    }
}
