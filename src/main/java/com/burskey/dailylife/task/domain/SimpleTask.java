package com.burskey.dailylife.task.domain;

import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public class SimpleTask implements Task {

    private String id;

    @NotBlank(message = "Please provide a Title")
    private String title;

    private String description;

    @NotBlank(message = "Please provide a creation date")
    private Date creationDate;


    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public Date getCreationDate() {
        return creationDate;
    }


    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
