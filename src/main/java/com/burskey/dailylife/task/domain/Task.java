package com.burskey.dailylife.task.domain;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.Date;


@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = SimpleTask.class, name = "simple")
})
public interface Task {

    public String getId();
    public String getPartyID();
    public String getTitle();
    public String getDescription();
    public Date getCreationDate();
    public SimpleStatusStateMachine getStatusStateMachine();


    public String getType();
}
