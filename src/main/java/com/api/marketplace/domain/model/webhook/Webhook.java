package com.api.marketplace.domain.model.webhook;

import java.util.ArrayList;
import java.util.List;

public class Webhook {
    private final Integer id;
    private String callbackUrl;
    private final List<Integer> storeIds = new ArrayList<>();


    public Webhook(Integer id, String callbackUrl) {
        this.id = id;
        this.callbackUrl = callbackUrl;
    }

    public Integer getId() {
        return id;
    }

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public List<Integer> getStoreIds() {
        return storeIds;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }

    public void addStoreId(Integer newStoreId) throws IllegalArgumentException {
        if(storeIds.stream().anyMatch(id -> id.equals(newStoreId))){
            throw new IllegalArgumentException("StoreId already added");
        }
        this.storeIds.add(newStoreId);
    }

    public void removeStoreId(Integer storeIdToRemove) throws IllegalArgumentException {
        if(storeIds.stream().noneMatch(id -> id.equals(storeIdToRemove))){
            throw new IllegalArgumentException("StoreId not found");
        }
        this.storeIds.remove(storeIdToRemove);
    }
}
