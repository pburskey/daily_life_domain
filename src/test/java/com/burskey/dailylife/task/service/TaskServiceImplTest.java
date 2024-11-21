package com.burskey.dailylife.task.service;

import com.burskey.dailylife.party.service.PartyService;
import com.burskey.dailylife.task.domain.SimpleTask;
import com.burskey.dailylife.task.domain.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Date;

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
    }

    @Test
    void getService() {
        assertNotNull(this.service);
    }

    @Test
    void getTask() {
        assertNotNull(this.task);
        when(this.dao.getTask(anyString())).thenReturn(task);

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