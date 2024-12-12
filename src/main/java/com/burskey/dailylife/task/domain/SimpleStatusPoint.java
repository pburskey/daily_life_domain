package com.burskey.dailylife.task.domain;

import java.util.Date;

public class SimpleStatusPoint implements StatusPoint {
    private Status status;
    private Date dateTime;
    private String type = "simple";
    private String id;


    public SimpleStatusPoint() {
    }

    public SimpleStatusPoint(Status status, Date dateTime) {
        this.status = status;
        this.dateTime = dateTime;

    }

    @Override
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


    @Override
    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
