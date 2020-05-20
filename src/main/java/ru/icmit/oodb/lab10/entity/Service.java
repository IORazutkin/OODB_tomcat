package ru.icmit.oodb.lab10.entity;

import javax.persistence.*;

@Entity
public class Service {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="personIdGenerator")
    @SequenceGenerator(name="personIdGenerator", sequenceName="person_seq", allocationSize=1)
    long id;
    String title;
    double price;
    String unit;

    public Service() {
    }

    public Service(String title, double price, String unit) {
        this.title = title;
        this.price = price;
        this.unit = unit;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}