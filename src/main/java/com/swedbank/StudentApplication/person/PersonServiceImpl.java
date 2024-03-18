package com.swedbank.StudentApplication.person;

import com.swedbank.StudentApplication.person.exception.PersonNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonServiceImpl  implements  PersonService {

    private PersonRepository repository;

    @Autowired
    public PersonServiceImpl(PersonRepository repository){

        this.repository = repository;
    }
    @Override
    public List<Person> getAll() {
        List<Person> list = new ArrayList<>(repository.findAll());
        return list;
    }

    @Override
    public Person getById(long pid) throws PersonNotFoundException {
        Person person = repository.findById(pid)
                .orElseThrow(() -> new PersonNotFoundException(pid));
        return person;
    }

    @Override
    public Person create(Person person) {
        return repository.save(person);
    }

    @Override
    public Person update(Person person) throws PersonNotFoundException {
        Person existingPerson = repository.findById(person.getPid()).orElseThrow(() -> new PersonNotFoundException(person.getPid()));
        existingPerson.setName(person.getName());
        existingPerson.setMiddleName(person.getMiddleName());
        existingPerson.setSurname(person.getSurname());
        existingPerson.setPhone(person.getPhone());
        existingPerson.setEmail(person.getEmail());
        Person updatedPerson = repository.save(existingPerson);
        return  updatedPerson;
    }

    @Override
    public void delete(long pid) throws PersonNotFoundException {
        Person person = repository.findById(pid).orElseThrow(()-> new PersonNotFoundException(pid));
        repository.deleteById(pid);
    }

    @Override
    public void saveAndFlush(Person person) {
        repository.saveAndFlush(person);
    }
}
