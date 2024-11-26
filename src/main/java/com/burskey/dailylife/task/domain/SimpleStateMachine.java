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


    public SimpleStateMachine(Map<Status, Status[]> progressionConfiguration, Status startState, Status endState) {
        this.progressionConfiguration = progressionConfiguration;
        this.startState = startState;
        this.endState = endState;
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
    public Status[] available(Status status) {
        Status[] states = null;
        if (status == null){
            states = new Status[]{this.startState()};
        }else{
            states = this.progressionConfiguration.get(status);
        }
        return states;
    }




}
