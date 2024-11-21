package com.burskey.dailylife.task.domain;

import jakarta.validation.constraints.NotBlank;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

public class SimpleStateMachine  implements StatusStateMachine{

    @NotBlank(message = "Please provide a configuration")
    private Map<Status, Status[]> progressionConfiguration;

    @NotBlank(message = "Please provide a start")
    private Status startState;

    @NotBlank(message = "Please provide a end")
    private Status endState;

    @NotBlank(message = "Please provide a task id")
    private String taskID;


    public SimpleStateMachine(Map<Status, Status[]> progressionConfiguration, Status startState, Status endState, String taskID) {
        this.progressionConfiguration = progressionConfiguration;
        this.startState = startState;
        this.endState = endState;
        this.taskID = taskID;
    }

    @Override
    public Map<Status, Status[]> progressionConfiguration() {
        return this.progressionConfiguration;
    }

    @Override
    public Status startState() {
        return this.startState;
    }

    @Override
    public Status endState() {
        return this.endState;
    }

    @Override
    public String getTaskID() {
        return this.taskID;
    }

    @Override
    public TaskInProgress start() {
        StatusPoint point = new SimpleStatusPoint(this.startState, this.getTaskID(), new Date());

        TaskInProgress tip = new SimpleTaskInProgress(null, this.getTaskID(), new Date(), point);
        return tip;
    }

    @Override
    public Status[] available(Status status) {
        Status[] states = null;
        if (status == null){
            states = new Status[]{this.startState()};
        }else{
            states = this.progressionConfiguration.get(status);
        }
        return states;
    }

    @Override
    public TaskInProgress changeTo(TaskInProgress task, Status status) {
        TaskInProgress tip = null;
        if (task != null && status != null){
            Status[] available = available(task.getStatus().getStatus());
            if (available != null && available.length > 0){
                if (Arrays.stream(available).anyMatch(aStatus -> aStatus.getId().equals(status.getId()))){
                    tip = new SimpleTaskInProgress(task.getTipID(), task.getTaskID(), task.getCreationDateTime(), new SimpleStatusPoint(status, task.getTipID(), new Date()));
                }
            }
        }
        return  tip;
    }



}
