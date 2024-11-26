package com.burskey.dailylife.task.domain;

import java.util.Map;

public interface StatusStateMachine {

    public Map<Status,Status[]> progressionConfiguration();
    public Status startState();
    public Status endState();



    public Status[] available(Status status);


}
