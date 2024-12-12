package com.burskey.dailylife.task.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Arrays;
import java.util.Date;

public class SimpleTask implements Task {

    private String id;
    private String partyID;
    private String type = "simple";


    @NotBlank(message = "Please provide a Title")
    private String title;

//    @NotBlank(message = "Please provide a description")
    private String description;

    @NotNull(message = "Please provide a creation date")
    private Date creationDate;


    @NotNull(message = "Please provide a status state machine")
    private SimpleStatusStateMachine statusStateMachine;


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

    @Override
    public String getPartyID() {
        return partyID;
    }

    public void setPartyID(String partyId) {
        this.partyID = partyId;
    }

    @Override
    public SimpleStatusStateMachine getStatusStateMachine() {
        return statusStateMachine;
    }

    public void setStatusStateMachine(SimpleStatusStateMachine statusStateMachine) {
        this.statusStateMachine = statusStateMachine;
    }

    public SimpleTask() {
    }





    @Override
    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
