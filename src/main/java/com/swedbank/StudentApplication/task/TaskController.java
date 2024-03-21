package com.swedbank.StudentApplication.task;

import com.swedbank.StudentApplication.group.Group;
import com.swedbank.StudentApplication.group.GroupService;
import com.swedbank.StudentApplication.group.exception.GroupNotFoundException;
import com.swedbank.StudentApplication.task.exceptiion.TaskNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController()
@CrossOrigin(origins = "*")
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;
    private final GroupService groupService;

    @Autowired
    public TaskController(TaskService service, GroupService groupService) {
        this.taskService = service;
        this.groupService = groupService;
    }

    @GetMapping()
    public ResponseEntity<List<Task>> getAllTasks(@RequestParam Optional<Long> groupId) throws GroupNotFoundException {
        if (groupId.isPresent()) {
            Group group = groupService.findById(groupId.get());

            return ResponseEntity.ok(taskService.findTasksByGroup(group));
        }

        return ResponseEntity.ok(taskService.findAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable long id) throws TaskNotFoundException {
        Task task = taskService.findById(id);

        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Task> createTask(@RequestBody TaskRequest taskRequest)
    {
        Task task = new Task(taskRequest.getShortDesc(), taskRequest.getDetails(), taskRequest.getStartDate(),
                taskRequest.getEndDate());

        taskService.save(task);

        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @PatchMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public void updateTask(@RequestBody Task taskData) throws TaskNotFoundException
    {
         taskService.update(taskData);
    }

    @PatchMapping("/{id}/set-group")
    public ResponseEntity<String> setTaskGroup(@PathVariable long id, @RequestParam long groupId) {
        Task task = taskService.findById(id);
        Group group = groupService.findById(groupId);

        task.setGroup(group);
        taskService.update(task);

        return new ResponseEntity<>("Task has been assigned to a group successfully.", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable long id) throws TaskNotFoundException {
        taskService.delete(id);

        return new ResponseEntity<>("Task deleted.", HttpStatus.OK);
    }
}
