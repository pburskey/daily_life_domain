package com.burskey.dailylife.task.domain;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.Map;




@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = SimpleStatusStateMachine.class, name = "simple")
})
public interface StatusStateMachine {

    public Map<String,String[]> getProgressionConfiguration();
    public String getStartState();
    public String getEndState();
    public Map<String, SimpleStatus> getState();


    public Status[] available(Status status);

    public Status from(String id);
    public String getType();

}
