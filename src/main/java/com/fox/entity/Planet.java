package com.fox.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "planet")
public class Planet {

    @Id
    @Column(name = "id", length = 20)
    private String id;

    @Column(name = "name", length = 500, nullable = false)
    private String name;

    @OneToMany(mappedBy = "fromPlanet")
    private List<Ticket> departureTickets = new ArrayList<>();

    @OneToMany(mappedBy = "toPlanet")
    private List<Ticket> arrivalTickets = new ArrayList<>();

    public Planet() {
    }

    public Planet(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ticket> getDepartureTickets() {
        return departureTickets;
    }

    public List<Ticket> getArrivalTickets() {
        return arrivalTickets;
    }

    @Override
    public String toString() {
        return "Planet{"
                + "id='" + id + '\''
                + ", name='" + name + '\''
                + '}';
    }
}
