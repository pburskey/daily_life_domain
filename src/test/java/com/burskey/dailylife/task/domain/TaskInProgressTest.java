package com.burskey.dailylife.task.domain;

import com.burskey.dailylife.party.service.ServiceImpl;
import org.junit.jupiter.api.BeforeEach;

import java.util.Date;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TaskInProgressTest {


    private Map<Status, Status[]> progressionConfiguration;
    private Status startState;
    private Status endState;
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


        this.progressionConfiguration = new HashMap<Status, Status[]>();
        this.startState = new SimpleStatus("start");
        this.endState = new SimpleStatus("finish");
        progressionConfiguration.put(this.startState, new Status[]{this.endState});
        progressionConfiguration.put(this.endState, null);
        task.setStatusStateMachine(new SimpleStateMachine(this.progressionConfiguration, this.startState, this.endState));
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
        assertEquals(tip.getStatus().getStatus(), this.task.getStatusStateMachine().startState());

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
        assertEquals(tip.getStatus().getStatus(), this.task.getStatusStateMachine().endState());

    }





}