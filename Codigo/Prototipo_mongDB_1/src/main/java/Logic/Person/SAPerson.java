package Logic.Person;

import java.util.List;

public interface SAPerson {

    public int add(TPerson tPerson);
    public int delete(int id);
    public TPerson read(int id);
    public List<TPerson> readAll();
    public int update(TPerson tPerson);

}
