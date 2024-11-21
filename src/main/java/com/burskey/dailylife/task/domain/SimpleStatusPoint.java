package com.burskey.dailylife.task.domain;

import java.util.Date;

public class SimpleStatusPoint implements StatusPoint {
    private Status status;
    private String taskInProgressID;
    private Date dateTime;



    public SimpleStatusPoint(Status status, String ataskInProgressID, Date dateTime) {
        this.status = status;
        this.taskInProgressID = ataskInProgressID;
        this.dateTime = dateTime;
    }

    @Override
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getTaskInProgressID() {
        return taskInProgressID;
    }

    public void setTaskInProgressID(String taskInProgressID) {
        this.taskInProgressID = taskInProgressID;
    }

    @Override
    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }
}
