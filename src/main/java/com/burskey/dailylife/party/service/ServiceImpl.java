package com.burskey.dailylife.party.service;

import com.burskey.dailylife.party.domain.Communication;
import com.burskey.dailylife.party.domain.Party;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service
public class ServiceImpl implements PartyService{

    private PartyService partyService;

    public ServiceImpl(PartyService partyService) {
        this.partyService = partyService;
    }
    public ServiceImpl() {

    }

    @Autowired
    private Validator validator;

    public PartyService getPartyService() {
        return partyService;
    }

    @Override
    public Party getParty(String partyId) {
        return this.getPartyService().getParty(partyId);
    }

    @Override
    public Party saveParty(Party party) {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Party>> violations = validator.validate(party);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
        return this.getPartyService().saveParty(party);
    }

    @Override
    public Communication getCommunication(String partyId, String communicationID) {
        return this.getPartyService().getCommunication(partyId, communicationID);
    }

    @Override
    public Communication saveCommunication(Communication communication) {

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Communication>> violations = validator.validate(communication);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
        return this.getPartyService().saveCommunication(communication);
    }


    @Override
    public Communication[] getCommunicationsByParty(String partyId) {
        return this.getPartyService().getCommunicationsByParty(partyId);
    }
}
