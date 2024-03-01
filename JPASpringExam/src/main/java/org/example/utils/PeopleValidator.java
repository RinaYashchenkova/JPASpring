package org.example.utils;

import org.example.models.Person;
import org.example.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PeopleValidator implements Validator {
    private final PeopleService peopleService;

    @Autowired
    public PeopleValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person =(Person) o;
        if (peopleService.getPersonByFullName(person.getFullName()).isPresent())
            errors.rejectValue("fullName","","Человек с таким ФИО уже существует");
    }
}
