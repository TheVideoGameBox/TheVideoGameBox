package Data.Person;

import Logic.Person.TPerson;

import java.util.List;

public interface DAOPerson {

    public int add(TPerson tPerson);
    public int delete(int id);
    public TPerson read(int id);
    public List<TPerson> readAll() throws ClassNotFoundException;
    public int update(TPerson tPerson);

}
