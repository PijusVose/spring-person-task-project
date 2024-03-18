package com.swedbank.StudentApplication.group;

import com.swedbank.StudentApplication.group.exception.GroupExistsException;
import com.swedbank.StudentApplication.group.exception.GroupNotFoundException;

import java.util.List;

public interface GroupService {

    List<Group> findAll();

    Group findById(Long id) throws GroupNotFoundException;

    Group save(Group group) throws GroupExistsException;

    Group update(Group group) throws GroupNotFoundException;

    void delete(long id) throws GroupNotFoundException;

    void deleteAll();

    void saveAndFlush(Group group);
}
