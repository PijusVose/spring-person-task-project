package com.swedbank.StudentApplication.group;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/groups")
public class GroupController {

    private static final Logger log = LoggerFactory.getLogger(GroupController.class);

    private final GroupService service;

    public GroupController(GroupService service){
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Group>> getAllGroups(){
        List<Group> groups = service.findAll();
        return new ResponseEntity<List<Group>>(groups, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Group> getGroupByid(@PathVariable final long id){
        Group group  = service.findById(id);
        return new ResponseEntity<>(group, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Group> createGroup(@RequestBody final Group group){
        Group savedGroup = service.save(group);
        return new ResponseEntity<>(savedGroup, HttpStatus.CREATED);
    }

    @PatchMapping
    public ResponseEntity<Group> updateGroup(@RequestBody final Group group){
        Group updateGroup = service.update(group);
        return new ResponseEntity<>(updateGroup, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteGroup(@PathVariable final long id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllGroups(){
        service.deleteAll();
        return ResponseEntity.ok().build();
    }
}
