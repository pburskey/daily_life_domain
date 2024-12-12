package com.burskey.dailylife.task.service;

import com.burskey.dailylife.task.domain.*;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

import java.util.Arrays;
import java.util.Date;
import java.util.Set;

public class TaskServiceImpl implements TaskService {

    private TaskService service;

    public TaskServiceImpl() {

    }

    public TaskService getService() {
        return service;
    }

    public TaskServiceImpl(TaskService service) {
        this.service = service;
    }

    @Override
    public Task getTask(  String id) {
        return this.getService().getTask( id);
    }

    @Override
    public Task saveTask(Task task) {
        {
            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set<ConstraintViolation<Task>> violations = validator.validate(task);
            if (!violations.isEmpty()) {
                throw new ConstraintViolationException(violations);
            }
        }


//        {
//            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
//            Set<ConstraintViolation<>> violations = validator.validate(task.getStatusStateMachine());
//            if (!violations.isEmpty()) {
//                throw new ConstraintViolationException(violations);
//            }
//        }


        return this.getService().saveTask(task);
    }


    @Override
    public TaskInProgress saveTaskInProgress(TaskInProgress tip) {
        {
            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set<ConstraintViolation<TaskInProgress>> violations = validator.validate(tip);
            if (!violations.isEmpty()) {
                throw new ConstraintViolationException(violations);
            }
        }

        return this.getService().saveTaskInProgress(tip);
    }

    @Override
    public StatusPoint[] getStatusPoints(String taskInProgressId) {
        return this.getService().getStatusPoints(taskInProgressId);
    }

    @Override
    public Task[] getTasksByParty(String partyId) {
        return this.getService().getTasksByParty(partyId);
    }


    @Override
    public TaskInProgress[] getByTask( String taskId) {
        return this.getService().getByTask( taskId);
    }

    @Override
    public TaskInProgress[] getByParty(String partyId) {
        return this.getService().getByParty(partyId);
    }

    @Override
    public TaskInProgress changeTo(String taskInProgressID, String statusID) {
        TaskInProgress tip = this.getTaskInProgress(taskInProgressID);
        Task task = this.getTask(tip.getTaskID());
        Status status = task.getStatusStateMachine().getState().get(statusID);
        return this.changeTo(task, tip, status);
    }


    @Override
    public TaskInProgress getTaskInProgress(String taskInProgressId) {
        return this.getService().getTaskInProgress(taskInProgressId);
    }

    @Override
    public TaskInProgress changeTo(Task task, TaskInProgress tip, Status status) {
        TaskInProgress newTip = null;
        if (task != null && status != null){
            Status[] available = task.getStatusStateMachine().available(tip.getStatus().getStatus());
            if (available != null && available.length > 0){
                if (Arrays.stream(available).anyMatch(aStatus -> aStatus.getId().equals(status.getId()))){
                    newTip = new SimpleTaskInProgress(tip.getID(), task.getId(), tip.getCreationDateTime(), new SimpleStatusPoint(status, new Date()));
                    this.saveTaskInProgress(newTip);
                }
            }
        }
        return  newTip;
    }

    @Override
    public TaskInProgress start(Task task) {
        StatusPoint point = new SimpleStatusPoint(task.getStatusStateMachine().from(task.getStatusStateMachine().getStartState()), new Date());
        TaskInProgress tip = new SimpleTaskInProgress(null, task.getId(), new Date(), point);
        this.saveTaskInProgress(tip);
        return tip;
    }

    @Override
    public TaskInProgress start(String taskId) {
        Task task = this.getTask( taskId);
        return this.start(task);
    }
}
