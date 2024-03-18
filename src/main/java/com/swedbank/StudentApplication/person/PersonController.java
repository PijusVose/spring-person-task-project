package com.swedbank.StudentApplication.person;

import com.swedbank.StudentApplication.group.Group;
import com.swedbank.StudentApplication.group.GroupService;
import com.swedbank.StudentApplication.person.exception.PersonNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/persons")
public class PersonController {
    private static Logger log = LoggerFactory.getLogger(PersonController.class);

    @Value("${my.value.file}")
    private String val;

    private PersonService service;

    private GroupService groupService;

    @Autowired
    public PersonController(PersonService service, GroupService groupService)
    {
        this.service = service;
        this.groupService = groupService;
    }

    @GetMapping(produces = "application/json")
    ResponseEntity<List<Person>> getAllPersons() {
        List<Person> list = service.getAll();
        log.info("Collection size: " + list.size());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("{pid}")
    ResponseEntity<Person> getByPid(@PathVariable("pid") Long pid)
            throws PersonNotFoundException {
            Person person = service.getById(pid);
            log.info("Found person of ID " + pid);
            return new ResponseEntity<Person>(person, HttpStatus.OK);

    }

    @PostMapping
    ResponseEntity<Person> savePerson(@RequestBody Person person){
        Person savedPerson =  service.create(person);
        return  new ResponseEntity<>(savedPerson, HttpStatus.OK);
    }

    @DeleteMapping("{pid}")
    public ResponseEntity<Void> deletePerson(@PathVariable("pid") Long pid) throws PersonNotFoundException{

            service.delete(pid);
            log.warn("Deleted a person with ID "+ pid);

            return ResponseEntity.ok().build();

    }

    //<-=Group=->
    @GetMapping("{pid}/groups")
    public ResponseEntity<Set<Group>> getPersonGroups(@PathVariable Long pid) throws PersonNotFoundException{
        Person person = service.getById(pid);
        return new ResponseEntity<>(person.getGroups(), HttpStatus.OK);
    }

    @PatchMapping("{pid}/groups/{id}")
    public ResponseEntity<?>  addPersonToGroup(@PathVariable long pid, @PathVariable long id) throws PersonNotFoundException{
        Person person = service.getById(pid);
        Group group = groupService.findById(id);

        Set<Group> groups = person.getGroups();
        groups.add(group);
        person.setGroups(groups);
        service.saveAndFlush(person);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{pid}/groups/{id}")
    public ResponseEntity<?> removePersonFromGroup(@PathVariable long pid, @PathVariable long id) throws PersonNotFoundException{
        Person person = service.getById(pid);
        Group group = groupService.findById(id);

        Set<Group> groups = person.getGroups();

        if (groups.contains(group)) {
            groups.remove(group);
            person.setGroups(groups);

            service.saveAndFlush(person);
        }
        return ResponseEntity.ok().build();
    }

}
