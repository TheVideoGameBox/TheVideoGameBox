package Logic.Person;

import Data.Person.DAOPersonImp;

import java.util.List;

public class SAPersonImp {

    public String add(TPerson tPerson) {
        if (tPerson.getNif().length() != 9)
            return "";
        else if (!tPerson.getNif().substring(0, 8).matches("^[0-9]+$"))
            return "";
        else if (!Character.isAlphabetic(tPerson.getNif().charAt(8)))
            return "";

        DAOPersonImp person = new DAOPersonImp();

        person.add(tPerson);

        return "";
    }

    public String delete(String nif) {
        if (nif.length() != 9)
            return "";
        else if (!nif.substring(0, 8).matches("^[0-9]+$"))
            return "";
        else if (!Character.isAlphabetic(nif.charAt(8)))
            return "";

        DAOPersonImp person = new DAOPersonImp();

        person.delete(nif);

        return "";
    }

    public List<TPerson> readAll() {
        DAOPersonImp person = new DAOPersonImp();
        return person.readAll();
    }

    public int update(TPerson tPerson) {
        return 0;
    }
}
