package com.jinternals.todo.app.domain.task.commands;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;


public class CompleteTaskCommand {

    @TargetAggregateIdentifier
    private final String id;

    public CompleteTaskCommand(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

}