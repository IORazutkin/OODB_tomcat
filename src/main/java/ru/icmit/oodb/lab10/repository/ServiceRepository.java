package ru.icmit.oodb.lab10.repository;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.icmit.oodb.lab10.entity.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Component
public class ServiceRepository {
    @PersistenceContext(name = "entityManagerFactory")
    protected EntityManager entityManager;

    public List<Service> findAll() {
        Query query = entityManager.createQuery(
                "select c from Service c ", Service.class);
        return (List<Service>) query.getResultList();
    }

    public Service findById(long id) {
        Query query = entityManager.createQuery(
                "select c from Service c where c.id = :id", Service.class);
        query.setParameter("id", id);
        List services = query.getResultList();
        if (services.isEmpty()) {
            return null;
        }
        return (Service) services.get(0);
    }

    @Transactional
    public Service save(Service service) {
        entityManager.persist(service);
        return service;
    }

    @Transactional
    public void update(Service service) {
        entityManager.merge(service);
    }

    @Transactional
    public void delete(Service service) {
        entityManager.remove(entityManager.contains(service) ? service : entityManager.merge(service));
    }
}
