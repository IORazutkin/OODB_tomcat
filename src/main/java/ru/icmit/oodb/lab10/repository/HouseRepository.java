package ru.icmit.oodb.lab10.repository;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.icmit.oodb.lab10.entity.House;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Component
public class HouseRepository {
    @PersistenceContext(name = "entityManagerFactory")
    protected EntityManager entityManager;

    public List<House> findAll() {
        Query query = entityManager.createQuery(
                "select c from House c ", House.class);
        return (List<House>) query.getResultList();
    }

    public House findByAddress(String address) {
        Query query = entityManager.createQuery(
                "select c from House c where c.address like :address ", House.class);
        query.setParameter("address", address);
        List<House> houses = query.getResultList();
        if (houses.isEmpty()) {
            return null;
        }
        return houses.get(0);
    }

    public House findById(long id) {
        Query query = entityManager.createQuery(
                "select c from House c where c.id = :id", House.class);
        query.setParameter("id", id);
        List house = query.getResultList();
        if (house.isEmpty()) {
            return null;
        }
        return (House) house.get(0);
    }

    @Transactional
    public House save(House house) {
        entityManager.persist(house);
        return house;
    }

    @Transactional
    public void update(House house) {
        entityManager.merge(house);
    }

    @Transactional
    public void delete(House house) {
        entityManager.remove(entityManager.contains(house) ? house : entityManager.merge(house));
    }
}
