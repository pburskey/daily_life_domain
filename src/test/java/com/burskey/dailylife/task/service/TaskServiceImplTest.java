package com.burskey.dailylife.task.service;

import com.burskey.dailylife.party.service.PartyService;
import com.burskey.dailylife.task.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Date;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class TaskServiceImplTest {

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

        HashMap map = new HashMap<Status, Status[]>();
        Status start = new SimpleStatus("start");
        Status end = new SimpleStatus("finish");
        map.put(start, new Status[]{end});
        map.put(end, null);

        task.setStatusStateMachine(new SimpleStateMachine(map, start, end));


    }

    @Test
    void getService() {
        assertNotNull(this.service);
    }

    @Test
    void getTask() {
        assertNotNull(this.task);
        when(this.dao.getTask(anyString(), anyString())).thenReturn(task);

        Task aTask = this.service.getTask("2","1");
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