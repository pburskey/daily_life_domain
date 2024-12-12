package com.burskey.dailylife.task.domain;

import com.burskey.dailylife.task.service.TaskService;
import com.burskey.dailylife.task.service.TaskServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class TaskInProgressTest {


    private Map<String, String[]> progressionConfiguration;
    private SimpleStatus startState;
    private SimpleStatus endState;
    private String taskID;


    TaskService service = null;
    TaskService dao = Mockito.mock(TaskService.class);

    SimpleTask task = null;
    @BeforeEach
    void setUp() {
        this.service = new TaskServiceImpl(dao);
        when(this.dao.saveTask(any())).thenAnswer(new Answer<Task>() {
            @Override
            public Task answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                return (Task) args[0];
            }
        });

        when(this.dao.saveTaskInProgress(any())).thenAnswer(new Answer<TaskInProgress>() {
            @Override
            public TaskInProgress answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                return (TaskInProgress) args[0];
            }
        });

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
        TaskInProgress tip = this.service.start(this.task);
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
        TaskInProgress tip = this.service.start(this.task);
        assertNotNull(tip);
        assertNotNull(tip.getCreationDateTime());
        assertNotNull(tip.getStatus());
        assertEquals(tip.getStatus().getStatus().getId(), this.task.getStatusStateMachine().getStartState());

    }


    @org.junit.jupiter.api.Test
    void progression_2() {

        assertNotNull(this.task.getStatusStateMachine());
        TaskInProgress tip = this.service.start(this.task);
        Status[] statuses = this.task.getStatusStateMachine().available(tip.getStatus().getStatus());
        tip = this.service.changeTo(this.task, tip,statuses[0]);
        assertNotNull(tip);

    }

    @org.junit.jupiter.api.Test
    void progression_4() {

        assertNotNull(this.task.getStatusStateMachine());
        TaskInProgress tip = this.service.start(this.task);
        Status status = new SimpleStatus("status");
        tip = this.service.changeTo(this.task, tip, status);
        assertNull(tip);

    }



    @org.junit.jupiter.api.Test
    void progression_3() {

        assertNotNull(this.task.getStatusStateMachine());
        TaskInProgress tip = this.service.start(this.task);
        Status[] statuses = this.task.getStatusStateMachine().available(tip.getStatus().getStatus());
        tip = this.service.changeTo(this.task, tip,statuses[0]);
        assertEquals(tip.getStatus().getStatus().getId(), this.task.getStatusStateMachine().getEndState());

    }




    @Test
    void tip_serialize() throws JsonProcessingException {
        assertNotNull(this.task.getStatusStateMachine());
        TaskInProgress tip = this.service.start(this.task);
        assertNotNull(tip);
        ObjectMapper mapper = new ObjectMapper();
//        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
//        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        String json = mapper.writeValueAsString(tip);
        Assertions.assertNotEquals(json, "");


    }

    @Test
    void tip_deserialize() throws JsonProcessingException {
        assertNotNull(this.task.getStatusStateMachine());
        TaskInProgress tip = this.service.start(this.task);
        assertNotNull(tip);
        ObjectMapper mapper = new ObjectMapper();
//        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
//        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        String json = mapper.writeValueAsString(tip);

        TaskInProgress tip2 = mapper.readValue(json, TaskInProgress.class);
        assertNotNull(tip2);



    }
    @Test
    void task_serialize() throws JsonProcessingException {
        assertNotNull(this.task);
        ObjectMapper mapper = new ObjectMapper();
//        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
//        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        String json = mapper.writeValueAsString(this.task);
        Assertions.assertNotEquals(json, "");


    }

    @Test
    void task_deserialize() throws JsonProcessingException {
        assertNotNull(this.task);
        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(this.task);
        Task aTask = mapper.readValue(json, Task.class);
        assertNotNull(aTask);



    }



}