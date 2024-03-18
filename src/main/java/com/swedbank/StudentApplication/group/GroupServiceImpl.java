package com.swedbank.StudentApplication.group;

import com.swedbank.StudentApplication.group.exception.GroupExistsException;
import com.swedbank.StudentApplication.group.exception.GroupNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupServiceImpl  implements GroupService{

    private final  GroupRepository repository;

    @Autowired
    public GroupServiceImpl(GroupRepository repository) {

        this.repository = repository;
    }

    @Override
    public List<Group> findAll() {
        return repository.findAll();
    }

    @Override
    public Group findById(Long id) throws GroupNotFoundException {
        return repository.findById(id).orElseThrow(() -> new GroupNotFoundException(id));
    }

    @Override
    public Group save(Group group) throws GroupExistsException {
        return repository.save(group);
    }

    @Override
    public Group update(Group group) throws GroupNotFoundException {

        Group existingGroup = repository.findById(group.getId()).orElseThrow(() ->new GroupNotFoundException(group.getId()));
        existingGroup.setName(group.getName());
        existingGroup.setDetails(group.getDetails());
        existingGroup.setPersons(group.getPersons());
        Group updatedGroup = repository.save(existingGroup);
        return updatedGroup;
    }

    @Override
    public void delete(long id) throws GroupNotFoundException {
        Optional<Group> group = repository.findById(id);
        if (group.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new GroupNotFoundException(id);
        }
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public void saveAndFlush(Group group) {
        repository.saveAndFlush(group);
    }
}
