package com.jinternals.todo.app.domain.task.aggreates.exceptions;

public class TaskNotStartedException extends RuntimeException {

    private static final long serialVersionUID = 1518440584190922771L;

    public TaskNotStartedException(String message) {
        super(message);
    }
}
