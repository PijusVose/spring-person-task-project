package com.swedbank.StudentApplication.task;

import com.swedbank.StudentApplication.group.Group;
import com.swedbank.StudentApplication.group.exception.GroupNotFoundException;
import com.swedbank.StudentApplication.task.exceptiion.TaskExistsException;
import com.swedbank.StudentApplication.task.exceptiion.TaskNotFoundException;

import java.util.List;

public interface TaskService {

    List<Task> findAll();

    Task findById(long id) throws TaskNotFoundException;

    Task save(Task task) throws TaskExistsException;

    void update(Task task) throws TaskNotFoundException;
    List<Task> findTasksByGroup(Group group) throws TaskNotFoundException, GroupNotFoundException;

    void delete(long id) throws TaskNotFoundException;

    void deleteAll();
}
