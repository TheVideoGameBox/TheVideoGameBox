package Logic.Person;

import Data.Person.DAOPerson;
import Data.Person.DAOPersonImp;

import java.util.List;

public class SAPersonImp implements SAPerson {

    @Override
    public String add(TPerson tPerson) {
        DAOPersonImp person = new DAOPersonImp();
        if(tPerson.getNif().length() == 9) {
            person.add(tPerson);
        }
        else return "";

        return "";
    }

    @Override
    public int delete(String id) {
        return 0;
    }

    @Override
    public TPerson read(String id) {
        return null;
    }

    @Override
    public List<TPerson> readAll() {
        return null;
    }

    @Override
    public int update(TPerson tPerson) {
        return 0;
    }
}
