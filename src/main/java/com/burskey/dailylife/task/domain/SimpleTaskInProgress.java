package com.burskey.dailylife.task.domain;

import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public class SimpleTaskInProgress implements TaskInProgress {
    private String tipID;


    @NotBlank(message = "Please provide a task id")
    private String taskID;

    @NotBlank(message = "Please provide a creation date and time")
    private Date creationDateTime;

    @NotBlank(message = "Please provide a status")
    private StatusPoint status;



    public SimpleTaskInProgress(String tipID, String taskID, Date creationDateTime, StatusPoint status) {
        this.tipID = tipID;
        this.taskID = taskID;
        this.creationDateTime = creationDateTime;
        this.status = status;
    }

    public String getTipID() {
        return tipID;
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
}
