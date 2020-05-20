package ru.icmit.oodb.lab10.entity;

import javax.persistence.*;

@Entity
public class Apartment {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="personIdGenerator")
    @SequenceGenerator(name="personIdGenerator", sequenceName="person_seq", allocationSize=1)
    long id;

    @ManyToOne(targetEntity = House.class)
    @JoinColumn(name = "house_id")
    House house;
    int number;

    public Apartment() {}

    public Apartment(House house, int number) {
        this.house = house;
        this.number = number;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}