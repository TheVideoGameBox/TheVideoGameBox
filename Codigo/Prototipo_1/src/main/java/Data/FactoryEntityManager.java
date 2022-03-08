package Data;

import jakarta.persistence.EntityManager;

public abstract class FactoryEntityManager {

    private static FactoryEntityManager instance = null;

    public static FactoryEntityManager getInstance(){
        if (instance == null)
            instance = new FactoryEntityManagerImp();

        return instance;
    }

    public abstract EntityManager generateEntityManager();
}
