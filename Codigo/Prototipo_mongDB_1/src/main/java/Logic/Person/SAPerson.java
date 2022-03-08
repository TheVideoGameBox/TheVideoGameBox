package Logic.Person;

import java.util.List;

public interface SAPerson {

    public String add(TPerson tPerson);
    public int delete(String id);
    public TPerson read(String id);
    public List<TPerson> readAll();
    public int update(TPerson tPerson);

}
