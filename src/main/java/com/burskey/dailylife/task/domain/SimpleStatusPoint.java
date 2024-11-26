package com.burskey.dailylife.task.domain;

import java.util.Date;

public class SimpleStatusPoint implements StatusPoint {
    private Status status;
    private Date dateTime;



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
}
