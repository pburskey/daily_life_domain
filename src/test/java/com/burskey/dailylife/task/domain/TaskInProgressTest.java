package com.burskey.dailylife.task.domain;

import org.junit.jupiter.api.BeforeEach;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TaskInProgressTest {


    private Map<String, String[]> progressionConfiguration;
    private SimpleStatus startState;
    private SimpleStatus endState;
    private String taskID;

    SimpleTask task = null;
    @BeforeEach
    void setUp() {
        this.taskID = "1";
        this.task = new SimpleTask();
        task.setDescription("description");
        task.setCreationDate(new Date());
        task.setTitle("title");
        task.setId(this.taskID);


        this.progressionConfiguration = new HashMap<String, String[]>();
        this.startState = new SimpleStatus("start");
        this.endState = new SimpleStatus("finish");
        Map<String, SimpleStatus> state = new HashMap<>();
        state.put(startState.getId(), startState);
        state.put(endState.getId(), endState);
        progressionConfiguration.put(this.startState.getId(), new String[]{this.endState.getId()});
        progressionConfiguration.put(this.endState.getId(), null);
        task.setStatusStateMachine(new SimpleStatusStateMachine(this.progressionConfiguration, this.startState.getId(), this.endState.getId(), state));
    }

    @org.junit.jupiter.api.Test
    void simpleTaskInProgress() {

        assertNotNull(this.task);
        assertNotNull(this.task.getStatusStateMachine());
        TaskInProgress tip = this.task.start();
        assertNotNull(tip);
    }

    @org.junit.jupiter.api.Test
    void simpleTaskInProgress_statuses_start() {

        assertNotNull(this.task.getStatusStateMachine());
        Status[] statuses = (this.task.getStatusStateMachine().available(null));
        assertNotNull(statuses);
        assertEquals(1, statuses.length);
    }


    @org.junit.jupiter.api.Test
    void simpleTaskInProgress_statuses_start2() {

        assertNotNull(this.task.getStatusStateMachine());
        Status[] statuses = (this.task.getStatusStateMachine().available(this.startState));
        assertNotNull(statuses);
        assertEquals(1, statuses.length);
    }


    @org.junit.jupiter.api.Test
    void simpleTaskInProgress_statuses_end() {

        assertNotNull(this.task.getStatusStateMachine());
        Status[] statuses = (this.task.getStatusStateMachine().available(this.endState));
        assertNull(statuses);
    }

    @org.junit.jupiter.api.Test
    void progression_1() {

        assertNotNull(this.task.getStatusStateMachine());
        TaskInProgress tip = this.task.start();
        assertNotNull(tip);
        assertNotNull(tip.getCreationDateTime());
        assertNotNull(tip.getStatus());
        assertEquals(tip.getStatus().getStatus().getId(), this.task.getStatusStateMachine().getStartState());

    }


    @org.junit.jupiter.api.Test
    void progression_2() {

        assertNotNull(this.task.getStatusStateMachine());
        TaskInProgress tip = this.task.start();
        Status[] statuses = this.task.getStatusStateMachine().available(tip.getStatus().getStatus());
        tip = this.task.changeTo(null,statuses[0]);
        assertNull(tip);

    }

    @org.junit.jupiter.api.Test
    void progression_4() {

        assertNotNull(this.task.getStatusStateMachine());
        TaskInProgress tip = this.task.start();
        Status status = new SimpleStatus("status");
        tip = this.task.changeTo(tip, status);
        assertNull(tip);

    }



    @org.junit.jupiter.api.Test
    void progression_3() {

        assertNotNull(this.task.getStatusStateMachine());
        TaskInProgress tip = this.task.start();
        Status[] statuses = this.task.getStatusStateMachine().available(tip.getStatus().getStatus());
        tip = this.task.changeTo(tip,statuses[0]);
        assertEquals(tip.getStatus().getStatus().getId(), this.task.getStatusStateMachine().getEndState());

    }





}