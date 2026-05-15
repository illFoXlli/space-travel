package com.fox.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne(optional = false)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne(optional = false)
    @JoinColumn(name = "from_planet_id", nullable = false)
    private Planet fromPlanet;

    @ManyToOne(optional = false)
    @JoinColumn(name = "to_planet_id", nullable = false)
    private Planet toPlanet;

    public Ticket() {
    }

    public Ticket(LocalDateTime createdAt, Client client, Planet fromPlanet, Planet toPlanet) {
        this.createdAt = createdAt;
        this.client = client;
        this.fromPlanet = fromPlanet;
        this.toPlanet = toPlanet;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Planet getFromPlanet() {
        return fromPlanet;
    }

    public void setFromPlanet(Planet fromPlanet) {
        this.fromPlanet = fromPlanet;
    }

    public Planet getToPlanet() {
        return toPlanet;
    }

    public void setToPlanet(Planet toPlanet) {
        this.toPlanet = toPlanet;
    }

    @Override
    public String toString() {
        return "Ticket{"
                + "id=" + id
                + ", createdAt=" + createdAt
                + ", clientId=" + (client != null ? client.getId() : null)
                + ", fromPlanetId='" + (fromPlanet != null ? fromPlanet.getId() : null) + '\''
                + ", toPlanetId='" + (toPlanet != null ? toPlanet.getId() : null) + '\''
                + '}';
    }
}
