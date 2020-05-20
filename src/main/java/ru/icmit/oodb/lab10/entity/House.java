package ru.icmit.oodb.lab10.entity;

import javax.persistence.*;

@Entity
public class House {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="personIdGenerator")
    @SequenceGenerator(name="personIdGenerator", sequenceName="person_seq", allocationSize=1)
    long id;
    String address;

    public House() {}

    public House(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "House{" +
                "house_id=" + id +
                ", address='" + address + '\'' +
                '}';
    }
}