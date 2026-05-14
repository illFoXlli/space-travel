package com.fox.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "planet")
public class Planet {

    @Id
    @Column(name = "id", length = 20)
    private String id;

    @Column(name = "name", length = 500, nullable = false)
    private String name;

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

    @Override
    public String toString() {
        return "Planet{"
                + "id='" + id + '\''
                + ", name='" + name + '\''
                + '}';
    }
}
