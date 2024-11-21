package com.burskey.dailylife.party.service;

import com.burskey.dailylife.party.domain.Communication;
import com.burskey.dailylife.party.domain.Person;
import jakarta.validation.ConstraintViolationException;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ServiceImplTest {

    PartyService service = null;
    PartyService dao =     Mockito.mock(PartyService.class);
    @org.junit.jupiter.api.BeforeEach
    void setUp() {

        this.service = new ServiceImpl(dao);
    }

    @org.junit.jupiter.api.Test
    void getPartyService() {
        assertNotNull(this.service);
    }

    @org.junit.jupiter.api.Test
    void getParty() {
        Person person = new Person();
        person.setId("1");
        when(this.dao.getParty(person.getId())).thenReturn(person);

        Person aPerson = (Person) this.dao.getParty(person.getId());
        assertNotNull(aPerson);

    }

    @org.junit.jupiter.api.Test
    void saveParty_invalid_1() {
        Person person = new Person();
        try{
            this.service.saveParty(person);
            throw new RuntimeException("Expected exception");
        } catch (ConstraintViolationException e) {
            assertNotNull(e);
            assertTrue(e.getConstraintViolations().size() >= 2);
        }

    }

    @org.junit.jupiter.api.Test
    void saveParty_invalid_2() {
        Person person = new Person();
        person.setGivenName("patrick");
        try{
            this.service.saveParty(person);
            throw new RuntimeException("Expected exception");
        } catch (ConstraintViolationException e) {
            assertNotNull(e);
            assertEquals("surname: Please provide a surname",e.getMessage());
        }

    }


    @org.junit.jupiter.api.Test
    void saveParty_invalid_3() {
        Person person = new Person();
        person.setSurname("a");
        try{
            this.service.saveParty(person);
            throw new RuntimeException("Expected exception");
        } catch (ConstraintViolationException e) {
            assertNotNull(e);
            assertEquals("givenName: Please provide a given name",e.getMessage());
        }
    }



    @org.junit.jupiter.api.Test
    void getCommunication() {
    }

    @org.junit.jupiter.api.Test
    void saveCommunication_a() {
        Communication communication = new Communication();
        communication.setCommunication(null);
        communication.setPartyID("123");
        try{
            this.service.saveCommunication(communication);
            throw new RuntimeException("Expected exception");
        } catch (ConstraintViolationException e) {
            assertNotNull(e);
            assertEquals("communication: Please provide a communication",e.getMessage());
        }

    }

    @org.junit.jupiter.api.Test
    void saveCommunication_b() {
        Communication communication = new Communication();
        communication.setCommunication("123");
        communication.setPartyID(null);
        try{
            this.service.saveCommunication(communication);
            throw new RuntimeException("Expected exception");
        } catch (ConstraintViolationException e) {
            assertNotNull(e);
            assertEquals("partyID: Please provide a party id",e.getMessage());
        }

    }

}