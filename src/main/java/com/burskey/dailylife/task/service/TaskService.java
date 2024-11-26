package com.burskey.dailylife.task.service;

import com.burskey.dailylife.task.domain.*;

public interface TaskService {

    public Task getTask(String partyId, String id);
    public Task saveTask(Task task);

    public TaskInProgress save(TaskInProgress tip);
    public StatusPoint[] getStatusPoints(String taskInProgressId);

}
