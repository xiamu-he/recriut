package com.qzx.recruit.domain;

public class PersonInfoWithPersonApply extends PersonInfo {
    private PersonApply personApply;

    public PersonApply getPersonApply() {
        return personApply;
    }

    public void setPersonApply(PersonApply personApply) {
        this.personApply = personApply;
    }
}