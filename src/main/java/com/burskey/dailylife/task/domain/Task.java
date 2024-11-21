package com.burskey.dailylife.task.domain;

import java.util.Date;

public interface Task {

    public String getId();
    public String getTitle();
    public String getDescription();
    public Date getCreationDate();


}
