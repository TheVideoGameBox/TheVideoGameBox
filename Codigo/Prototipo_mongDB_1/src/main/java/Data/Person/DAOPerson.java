package Data.Person;

import Logic.Person.TPerson;

import java.util.List;

public interface DAOPerson {

    public String add(TPerson tPerson);
    public int delete(String id);
    public TPerson read(String id);
    public List<TPerson> readAll() throws ClassNotFoundException;
    public int update(TPerson tPerson);

}
