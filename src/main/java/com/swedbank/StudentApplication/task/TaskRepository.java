package com.swedbank.StudentApplication.task;

import com.swedbank.StudentApplication.group.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository  extends JpaRepository<Task, Long> {
    List<Task> findTasksByGroup(Group group);
}
