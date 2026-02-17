package com.api.marketplace.domain.model.store;

import java.util.UUID;

public class Store {
    private final Integer id;
    private final UUID externalId;
    private String name;
    private final String cnpj;

    public Store(Integer id, UUID externalId, String cnpj, String name) {
        this.id = id;
        this.externalId = externalId;
        this.cnpj = cnpj;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public UUID getExternalId() {
        return externalId;
    }

    public String getName() {
        return name;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setName(String name) {
        this.name = name;
    }
}
