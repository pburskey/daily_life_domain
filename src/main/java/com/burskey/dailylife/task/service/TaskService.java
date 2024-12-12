package com.burskey.dailylife.task.service;

import com.burskey.dailylife.task.domain.*;

public interface TaskService {

    public Task getTask( String id);
    public Task[] getTasksByParty(String partyId);
    public Task saveTask(Task task);
    public TaskInProgress[] getByTask(String taskId);
    public TaskInProgress getTaskInProgress(String taskInProgressId);
    public TaskInProgress[] getByParty(String partyId);
    public TaskInProgress saveTaskInProgress(TaskInProgress tip);
    public StatusPoint[] getStatusPoints(String taskInProgressId);
    public TaskInProgress start(Task task);
    public TaskInProgress start(String taskId);
    public TaskInProgress changeTo(Task task, TaskInProgress tip, Status status);
    public TaskInProgress changeTo(String taskInProgressID, String statusID);
}
