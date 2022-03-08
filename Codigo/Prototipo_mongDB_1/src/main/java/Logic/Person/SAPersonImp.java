package Logic.Person;

import Data.Person.DAOPersonImp;

import java.util.List;

public class SAPersonImp {

    public String add(TPerson tPerson) {
        DAOPersonImp person = new DAOPersonImp();
        if(tPerson.getNif().length() == 9) {
            person.add(tPerson);
        }
        else return "";

        return "";
    }

    public int delete(String id) {
        return 0;
    }

    public TPerson read(String id) {
        return null;
    }

    public List<TPerson> readAll() {
        DAOPersonImp person = new DAOPersonImp();
        return person.readAll();
    }

    public int update(TPerson tPerson) {
        return 0;
    }
}
