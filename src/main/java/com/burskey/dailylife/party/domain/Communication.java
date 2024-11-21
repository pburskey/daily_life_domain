package com.burskey.dailylife.party.domain;

import jakarta.validation.constraints.NotBlank;

public class Communication {
    private CommunicationType communicationType = null;
    private CommunicationCode communicationCode = null;

    @NotBlank(message = "Please provide a communication")
    private String communication = null;

    private String id = null;

    @NotBlank(message = "Please provide a party id")
    private String partyID = null;

    public CommunicationType getCommunicationType() {
        return communicationType;
    }

    public void setCommunicationType(CommunicationType communicationType) {
        this.communicationType = communicationType;
    }

    public CommunicationCode getCommunicationCode() {
        return communicationCode;
    }

    public void setCommunicationCode(CommunicationCode communicationCode) {
        this.communicationCode = communicationCode;
    }

    public String getCommunication() {
        return communication;
    }

    public void setCommunication(String communication) {
        this.communication = communication;
    }

    public String getPartyID() {
        return partyID;
    }

    public void setPartyID(String partyID) {
        this.partyID = partyID;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
