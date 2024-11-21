package com.burskey.dailylife.party.domain;

import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public class Person extends Party {

    public Person() {
    }

    @NotBlank(message = "Please provide a given name")
    private String givenName = null;


    private String middleName = null;


    @NotBlank(message = "Please provide a surname")
    private String surname = null;
    private Date dateOfBirth = null;

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public PartyType getPartyType() {
        return super.getPartyType();
    }
}
