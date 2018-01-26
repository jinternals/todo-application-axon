package com.jinternals.todo.app.domain.task.commands;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

public class StartTaskCommand {

    @TargetAggregateIdentifier
    private final String id;

    public StartTaskCommand(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

}
