package com.api.marketplace.adapters.store.persistence;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "stores")
public class StoreJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
    private String cnpj;

    protected StoreJpaEntity() {
    }

    public StoreJpaEntity(UUID id, String name, String cnpj) {
        this.id = id;
        this.name = name;
        this.cnpj = cnpj;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCnpj() {
        return cnpj;
    }
}
