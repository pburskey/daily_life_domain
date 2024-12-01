package com.burskey.dailylife.task.domain;

import java.util.UUID;

public class SimpleStatus implements Status{
    private String id;
    private String description;
    private String type = "simple";
    public SimpleStatus() {
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SimpleStatus(String description) {
        this.description = description;
        this.id = UUID.randomUUID().toString();
    }

    public SimpleStatus(String id, String description) {
        this.id = id;
        this.description = description;
    }


    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
