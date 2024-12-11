package com.burskey.dailylife.task.domain;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.Date;


@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = SimpleTaskInProgress.class, name = "simple")
})
public interface TaskInProgress {


    public String getID();

    public String getTaskID();

    public Date getCreationDateTime();

    public StatusPoint getStatus();

    public String getType();
}
