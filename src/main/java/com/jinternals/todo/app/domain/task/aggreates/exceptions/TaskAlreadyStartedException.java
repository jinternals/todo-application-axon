package com.jinternals.todo.app.domain.task.aggreates.exceptions;

public class TaskAlreadyStartedException extends RuntimeException {

    private static final long serialVersionUID = 1518440584190922771L;

    public TaskAlreadyStartedException(String message) {
        super(message);
    }
}
