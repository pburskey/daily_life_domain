package com.burskey.dailylife.task.service;

import com.burskey.dailylife.task.domain.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class TaskServiceImplTest{

    TaskService service = null;
    TaskService dao = Mockito.mock(TaskService.class);

    SimpleTask task = null;

    @BeforeEach
    void setUp() {
        this.service = new TaskServiceImpl(dao);
        this.task = new SimpleTask();
        task.setId("1");
        task.setDescription("description");
        task.setTitle("title");
        task.setCreationDate(new Date());

        Map<String, SimpleStatus> state = new HashMap<>();
        SimpleStatus startState = new SimpleStatus("start");
        state.put(startState.getId(), startState);
        SimpleStatus endState = new SimpleStatus("finish");
        state.put(endState.getId(), endState);

        Map map = new HashMap<String, String[]>();



        map.put(startState.getId(), new String[]{endState.getId()});
        map.put(endState.getId(), null);
        task.setStatusStateMachine(new SimpleStatusStateMachine(map, startState.getId(), endState.getId(),state));


    }

    @Test
    void getService() {
        assertNotNull(this.service);
    }

    @Test
    void getTask() {
        assertNotNull(this.task);
        when(this.dao.getTask( anyString())).thenReturn(task);

        Task aTask = this.service.getTask("1");
        assertNotNull(aTask);
        assertEquals(task.getId(), aTask.getId());


    }

    @Test
    void saveTask() {
    }

    @Test
    void getStatusStateMachine() {
    }

    @Test
    void save() {
    }

    @Test
    void getStatusPoints() {
    }





}