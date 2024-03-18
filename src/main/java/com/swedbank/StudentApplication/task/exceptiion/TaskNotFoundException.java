package com.swedbank.StudentApplication.task.exceptiion;

public class TaskNotFoundException extends TaskException{
    public TaskNotFoundException(long id) {
        super("Can't find task with " + id);
    }
}
