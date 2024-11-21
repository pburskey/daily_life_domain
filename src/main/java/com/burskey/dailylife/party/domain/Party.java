package com.burskey.dailylife.party.domain;


public abstract class Party {


    private PartyType partyType = null;
    private String id = null;

    public PartyType getPartyType() {
        return partyType;
    }

    public void setPartyType(PartyType partyType) {
        this.partyType = partyType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
