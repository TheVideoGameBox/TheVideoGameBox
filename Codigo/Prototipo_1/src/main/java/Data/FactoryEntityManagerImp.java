package Data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class FactoryEntityManagerImp extends FactoryEntityManager {

    private EntityManagerFactory factory;

    public FactoryEntityManagerImp() {
        factory = Persistence.createEntityManagerFactory("MySQL-JPA");
    }

    @Override
    public EntityManager generateEntityManager() {
        return factory.createEntityManager();
    }
}
