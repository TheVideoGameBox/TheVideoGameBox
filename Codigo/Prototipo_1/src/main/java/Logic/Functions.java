package Logic;

import Data.FactoryEntityManager;
import Logic.Person.Person;
import Logic.Person.TPerson;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;

public class Functions {

    public int add(TPerson tPerson) {
        int id = -1;

        if (tPerson.getNif().length() != 9)
            return id;
        else if (!tPerson.getNif().substring(0, 8).matches("^[0-9]+$"))
            return id;
        else if (!Character.isAlphabetic(tPerson.getNif().charAt(8)))
            return id;


        EntityManager em = FactoryEntityManager.getInstance().generateEntityManager();

        try {
            em.getTransaction().begin();


            TypedQuery<Person> tq = em.createNamedQuery("Person.findByNIF", Person.class);
            tq.setParameter("nif", tPerson.getNif());
            List<Person> leido = tq.getResultList();

            Person person = new Person(tPerson);

            if (leido.isEmpty()) {
                em.persist(person);
                em.flush();

                id = person.getId();
            } else if (!leido.get(0).isActivo()) {
                person = leido.get(0);
                person.setNif(tPerson.getNif());
                person.setNombre(tPerson.getNombre());
                person.setApellidos(tPerson.getApellidos());
                person.setActivo(true);

                id = person.getId();
            }

            if (id > 0)
                em.getTransaction().commit();
            else
                em.getTransaction().rollback();

        } catch (Exception e) {
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();

            id = -1;
        } finally {
            em.close();
        }

        return id;
    }

    public int delete(int id) {
        int res = -1;

        if (id < 0)
            return -1;

        EntityManager em = FactoryEntityManager.getInstance().generateEntityManager();
        EntityTransaction et = em.getTransaction();

        try {
            et.begin();

            Person p = em.find(Person.class, id);

            if (p != null && p.isActivo()) {
                p.setActivo(false);
                res = p.getId();

                et.commit();
            } else
                et.rollback();
        } catch (Exception e) {
            if (et.isActive())
                et.rollback();

            res = -1;
        } finally {
            em.close();
        }

        return res;
    }

    public TPerson read(int id) {
        TPerson tPerson = null;

        if (id < 0)
            return tPerson;

        EntityManager em = FactoryEntityManager.getInstance().generateEntityManager();

        try {
            em.getTransaction().begin();

            Person p = em.find(Person.class, id);

            if (p != null)
                tPerson = new TPerson(id, p.getNif(), p.getNombre(), p.getApellidos(), p.isActivo());

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();

            tPerson = null;
        } finally {
            em.close();
        }

        return tPerson;
    }
    
    public List<TPerson> readAll() {
        FactoryEntityManager fem = FactoryEntityManager.getInstance();
        EntityManager em = fem.generateEntityManager();
        TypedQuery<Person> tq = em.createNamedQuery("Person.findAll", Person.class);
        List<Person> persons = tq.getResultList();
        List<TPerson> tpersons = new ArrayList<TPerson>();
        for (Person per : persons) {
            TPerson p = new TPerson(per.getId(), per.getNif(), per.getNombre(), per.getApellidos(), per.isActivo());
            tpersons.add(p);
        }
        em.close();

        return tpersons;
    }
    
    public int update(TPerson tPerson) {
        int res = -1;

        if (tPerson.getId() < 0)
            return res;

        if (tPerson.getNif().length() != 9)
            return res;
        else if (!tPerson.getNif().substring(0, 8).matches("^[0-9]+$"))
            return res;
        else if (!Character.isAlphabetic(tPerson.getNif().charAt(8)))
            return res;

        EntityManager em = FactoryEntityManager.getInstance().generateEntityManager();
        EntityTransaction et = em.getTransaction();

        try {
            et.begin();

            Person p = em.find(Person.class, tPerson.getId());

            if (p != null && p.isActivo()) {
                TypedQuery tq = em.createNamedQuery("Person.findByNIF", Person.class);
                tq.setParameter("nif", tPerson.getNif());
                List<Person> repetido = tq.getResultList();

                if (repetido.isEmpty() || repetido.get(0).getId() == tPerson.getId()) {
                    p.setActivo(true);
                    p.setNif(tPerson.getNif());
                    p.setNombre(tPerson.getNombre());
                    p.setApellidos(tPerson.getApellidos());

                    res = tPerson.getId();
                    et.commit();
                } else {
                    res = -1;

                    et.rollback();
                }
            }
        } catch (Exception exception) {
            if (et.isActive())
                et.rollback();

            res = -1;
        } finally {
            em.close();
        }

        return res;
    }

}
