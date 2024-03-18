package com.swedbank.StudentApplication.group.exception;

public class GroupNotFoundException extends GroupException {
    public GroupNotFoundException(Long id) {
        super("Could not find group " + id);
    }
}
