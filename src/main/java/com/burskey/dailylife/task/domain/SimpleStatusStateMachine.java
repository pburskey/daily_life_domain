package com.burskey.dailylife.task.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleStatusStateMachine implements StatusStateMachine {

    @NotNull(message = "Please provide a configuration")
    private Map<String, String[]> progressionConfiguration = new HashMap<String, String[]>();

    @NotBlank(message = "Please provide a start")
    private String startState;

    @NotBlank(message = "Please provide a end")
    private String endState;

    @NotNull(message = "Please provide a state")
    private Map<String, SimpleStatus> state = null;

    private String type = "simple";

    public SimpleStatusStateMachine(Map<String, String[]> progressionConfiguration, String startState, String endState, Map<String, SimpleStatus> astate) {
        this.progressionConfiguration = progressionConfiguration;
        this.startState = startState;
        this.endState = endState;
        this.state = astate;
    }


    @Override
    public Map<String, String[]> getProgressionConfiguration() {
        return this.progressionConfiguration;
    }

    @Override
    public String getStartState() {
        return this.startState;
    }

    @Override
    public String getEndState() {
        return this.endState;
    }

    public SimpleStatusStateMachine() {
    }

    @Override
    public Status[] available(Status status) {
        Status[] states = null;
        if (status == null) {
            states = new Status[]{this.from(this.getStartState())};
        } else {
            String[] ids = this.getProgressionConfiguration().get(status.getId());
            List<Status> statuses = new ArrayList<>();
            if (ids != null) {
                for (String id : ids) {
                    statuses.add(this.from(id));
                }
                states = statuses.toArray(new Status[statuses.size()]);
            }

        }
        return states;
    }

    public void setProgressionConfiguration(Map<String, String[]> progressionConfiguration) {
        this.progressionConfiguration = progressionConfiguration;
    }

    public void setStartState(String startState) {
        this.startState = startState;
    }

    public void setEndState(String endState) {
        this.endState = endState;
    }

    @Override
    public Map<String, SimpleStatus> getState() {
        return state;
    }

    public void setState(Map<String, SimpleStatus> state) {
        this.state = state;
    }

    @Override
    public Status from(String id) {
        return this.getState().get(id);
    }

    @Override
    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
