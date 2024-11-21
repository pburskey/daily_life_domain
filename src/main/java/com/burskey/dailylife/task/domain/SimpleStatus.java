package com.burskey.dailylife.task.domain;

import java.util.UUID;

public class SimpleStatus implements Status{
    private String id;
    private String description;

    public SimpleStatus() {
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
}
