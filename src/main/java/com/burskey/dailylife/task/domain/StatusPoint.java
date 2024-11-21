package com.burskey.dailylife.task.domain;

import java.util.Date;

public interface StatusPoint {
    Status getStatus();

    String getTaskInProgressID();

    Date getDateTime();
}