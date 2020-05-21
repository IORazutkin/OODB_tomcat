package ru.icmit.oodb.lab10.repository;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.icmit.oodb.lab10.entity.Apartment;
import ru.icmit.oodb.lab10.entity.Receipt;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Component
public class ReceiptRepository {
    @PersistenceContext(name = "entityManagerFactory")
    protected EntityManager entityManager;

    public List<Receipt> findAll() {
        Query query = entityManager.createQuery(
                "select c from Receipt c ", Receipt.class);
        return (List<Receipt>) query.getResultList();
    }

    public Receipt findById(long id) {
        Query query = entityManager.createQuery(
                "select c from Receipt c where c.id = :id", Receipt.class);
        query.setParameter("id", id);
        List receipts = query.getResultList();
        if (receipts.isEmpty()) {
            return null;
        }
        return (Receipt) receipts.get(0);
    }

    public List<Receipt> findByApartmentId(long id) {
        Query query = entityManager.createQuery(
                "select c from Receipt c where c.apartment.id = :id", Receipt.class);
        query.setParameter("id", id);
        return (List<Receipt>) query.getResultList();
    }

    @Transactional
    public Receipt save(Receipt receipt) {
        entityManager.persist(receipt);
        return receipt;
    }

    @Transactional
    public void update(Receipt receipt) {
        entityManager.merge(receipt);
    }

    @Transactional
    public void delete(Receipt receipt) {
        entityManager.remove(entityManager.contains(receipt) ? receipt : entityManager.merge(receipt));
    }
}
