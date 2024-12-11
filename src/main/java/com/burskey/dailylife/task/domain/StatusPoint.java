package com.burskey.dailylife.task.domain;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.Date;



@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = SimpleStatusPoint.class, name = "simple")
})
public interface StatusPoint {
    Status getStatus();

    Date getDateTime();

    public String getType();
}
