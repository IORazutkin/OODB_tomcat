package ru.icmit.oodb.lab10.repository;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.icmit.oodb.lab10.entity.Apartment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Component
public class ApartmentRepository {
    @PersistenceContext(name = "entityManagerFactory")
    protected EntityManager entityManager;

    public List<Apartment> findAll() {
        Query query = entityManager.createQuery(
                "select c from Apartment c ", Apartment.class);
        return (List<Apartment>) query.getResultList();
    }

    public Apartment findById(long id) {
        Query query = entityManager.createQuery(
                "select c from Apartment c where c.id = :id", Apartment.class);
        query.setParameter("id", id);
        List apartments = query.getResultList();
        if (apartments.isEmpty()) {
            return null;
        }
        return (Apartment) apartments.get(0);
    }

    @Transactional
    public Apartment save(Apartment apartment) {
        entityManager.persist(apartment);
        return apartment;
    }

    @Transactional
    public void update(Apartment apartment) {
        entityManager.merge(apartment);
    }

    @Transactional
    public void delete(Apartment apartment) {
        entityManager.remove(entityManager.contains(apartment) ? apartment : entityManager.merge(apartment));
    }
}
