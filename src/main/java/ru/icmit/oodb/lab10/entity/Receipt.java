package ru.icmit.oodb.lab10.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Receipt {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="personIdGenerator")
    @SequenceGenerator(name="personIdGenerator", sequenceName="person_seq", allocationSize=1)
    long id;
    @ManyToOne(targetEntity = Service.class)
    @JoinColumn(name = "service_id")
    Service service;
    double value;
    @ManyToOne(targetEntity = Apartment.class)
    @JoinColumn(name = "apartment_id")
    Apartment apartment;
    LocalDate formationDate;

    public Receipt() {
    }

    public Receipt(Service service, double value, Apartment apartment) {
        this.service = service;
        this.value = value;
        this.apartment = apartment;
        this.formationDate = LocalDate.now();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    public LocalDate getFormationDate() {
        return formationDate;
    }

    public void setFormationDate(LocalDate formationDate) {
        this.formationDate = formationDate;
    }
}