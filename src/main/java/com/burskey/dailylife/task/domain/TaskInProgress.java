package com.burskey.dailylife.task.domain;

import java.util.Date;

public interface TaskInProgress {


    public String getTipID();

    public String getTaskID();

    public Date getCreationDateTime();

    public StatusPoint getStatus();


}
