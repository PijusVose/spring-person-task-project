package com.swedbank.StudentApplication.task;

import com.swedbank.StudentApplication.task.exceptiion.TaskNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController()
@CrossOrigin(origins = "*")
@RequestMapping("/api/tasks")
public class TaskController {

    private TaskService service;

    @Autowired
    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = service.findAll();

        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable long id) throws TaskNotFoundException {
        Task task = service.findById(id);

        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Task> createTask(@RequestBody TaskRequest taskRequest)
    {
        Task task = new Task(taskRequest.getShortDesc(), taskRequest.getDetails(), taskRequest.getStartDate(),
                taskRequest.getEndDate());

        service.save(task);

        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @PatchMapping("/update")
    public ResponseEntity<String> updateTask(@RequestBody Task taskData) throws TaskNotFoundException
    {
        service.update(taskData);

        return new ResponseEntity<>("Task updated successfully.", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable long id) throws TaskNotFoundException {
        service.delete(id);

        return new ResponseEntity<>("Task deleted.", HttpStatus.OK);
    }
}
