package com.swedbank.StudentApplication.person;

import com.swedbank.StudentApplication.person.exception.PersonNotFoundException;

import java.util.List;

public interface PersonService {

    List<Person> getAll();

    Person getById(long pid) throws PersonNotFoundException;

    Person create(Person person) ;

    Person update(Person person) throws  PersonNotFoundException;

    void delete(long pid) throws PersonNotFoundException;

    public void saveAndFlush(Person person);
}
