package com.burskey.dailylife.task.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class SimpleTaskInProgress implements TaskInProgress {
    private String id;


    @NotBlank(message = "Please provide a task id")
    private String taskID;

    @NotNull(message = "Please provide a creation date and time")
    private Date creationDateTime;

    @NotNull(message = "Please provide a status")
    private StatusPoint status;

    private String type = "simple";


    public SimpleTaskInProgress() {
    }

    public SimpleTaskInProgress(String tipID, String taskID, Date creationDateTime, StatusPoint status) {
        this.id = tipID;
        this.taskID = taskID;
        this.creationDateTime = creationDateTime;
        this.status = status;
    }

    public String getID() {
        return id;
    }

    public String getTaskID() {
        return taskID;
    }

    public Date getCreationDateTime() {
        return creationDateTime;
    }

    public StatusPoint getStatus() {
        return status;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTaskID(String taskID) {
        this.taskID = taskID;
    }

    public void setCreationDateTime(Date creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public void setStatus(StatusPoint status) {
        this.status = status;
    }

    @Override
    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
