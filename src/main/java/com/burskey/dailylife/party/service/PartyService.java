package com.burskey.dailylife.party.service;

import com.burskey.dailylife.party.domain.Communication;
import com.burskey.dailylife.party.domain.Party;

public interface PartyService {


    public Party getParty(String partyId);
    public Party saveParty(Party party);
    public Communication getCommunication(String partyId, String communicationID);
    public Communication[] getCommunicationsByParty(String partyId);
    public Communication saveCommunication(Communication communication);



}
