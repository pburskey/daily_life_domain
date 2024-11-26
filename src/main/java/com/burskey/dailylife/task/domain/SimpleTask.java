package com.burskey.dailylife.task.domain;

import jakarta.validation.constraints.NotBlank;

import java.util.Arrays;
import java.util.Date;

public class SimpleTask implements Task {

    private String id;
    private String partyID;

    @NotBlank(message = "Please provide a Title")
    private String title;

    private String description;

    @NotBlank(message = "Please provide a creation date")
    private Date creationDate;


    @NotBlank(message = "Please provide a status state machine")
    private StatusStateMachine statusStateMachine;


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
    public StatusStateMachine getStatusStateMachine() {
        return statusStateMachine;
    }

    public void setStatusStateMachine(StatusStateMachine statusStateMachine) {
        this.statusStateMachine = statusStateMachine;
    }



    @Override
    public TaskInProgress start() {
        StatusPoint point = new SimpleStatusPoint(this.getStatusStateMachine().startState(), new Date());

        TaskInProgress tip = new SimpleTaskInProgress(null, this.getId(), new Date(), point);
        return tip;
    }


    @Override
    public TaskInProgress changeTo(TaskInProgress task, Status status) {
        TaskInProgress tip = null;
        if (task != null && status != null){
            Status[] available = this.getStatusStateMachine().available(task.getStatus().getStatus());
            if (available != null && available.length > 0){
                if (Arrays.stream(available).anyMatch(aStatus -> aStatus.getId().equals(status.getId()))){
                    tip = new SimpleTaskInProgress(task.getTipID(), task.getTaskID(), task.getCreationDateTime(), new SimpleStatusPoint(status, new Date()));
                }
            }
        }
        return  tip;
    }
}
