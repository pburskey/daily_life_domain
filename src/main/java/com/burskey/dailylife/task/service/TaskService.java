package com.burskey.dailylife.task.service;

import com.burskey.dailylife.task.domain.*;

public interface TaskService {

    public Task getTask(String partyId, String id);
    public Task[] getTasksByParty(String partyId);
    public Task saveTask(Task task);
    public TaskInProgress[] getByTask(String partyId, String taskId);
    public TaskInProgress[] getByParty(String partyId);
    public TaskInProgress saveTaskInProgress(TaskInProgress tip);
    public StatusPoint[] getStatusPoints(String taskInProgressId);

}
