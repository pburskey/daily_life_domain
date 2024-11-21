package com.burskey.dailylife.task.service;

import com.burskey.dailylife.party.domain.Party;
import com.burskey.dailylife.task.domain.StatusPoint;
import com.burskey.dailylife.task.domain.StatusStateMachine;
import com.burskey.dailylife.task.domain.Task;
import com.burskey.dailylife.task.domain.TaskInProgress;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

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
    public Task getTask(String id) {
        return this.getService().getTask(id);
    }

    @Override
    public Task saveTask(Task task) {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Task>> violations = validator.validate(task);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        return this.getService().saveTask(task);
    }

    @Override
    public StatusStateMachine getStatusStateMachine(String taskId) {
        return this.getService().getStatusStateMachine(taskId);
    }

    @Override
    public TaskInProgress save(TaskInProgress tip) {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<TaskInProgress>> violations = validator.validate(tip);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        return this.getService().save(tip);
    }

    @Override
    public StatusPoint[] getStatusPoints(String taskInProgressId) {
        return this.getService().getStatusPoints(taskInProgressId);
    }

    @Override
    public StatusStateMachine saveStatusStateMachine(StatusStateMachine statusStateMachine) {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<StatusStateMachine>> violations = validator.validate(statusStateMachine);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        return this.getService().saveStatusStateMachine(statusStateMachine);
    }
}
