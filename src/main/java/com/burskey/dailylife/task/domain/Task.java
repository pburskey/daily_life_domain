package com.burskey.dailylife.task.domain;

import java.util.Date;

public interface Task {

    public String getId();
    public String getPartyID();
    public String getTitle();
    public String getDescription();
    public Date getCreationDate();
    public StatusStateMachine getStatusStateMachine();

    public TaskInProgress start();
    public TaskInProgress changeTo(TaskInProgress task, Status status);

}
