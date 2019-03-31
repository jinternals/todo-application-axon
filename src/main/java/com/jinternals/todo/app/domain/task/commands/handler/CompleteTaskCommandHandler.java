package com.jinternals.todo.app.domain.task.commands.handler;

import com.jinternals.todo.app.domain.task.aggreates.Task;
import com.jinternals.todo.app.domain.task.commands.CompleteTaskCommand;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.modelling.command.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static java.util.Optional.ofNullable;


@Component
public class CompleteTaskCommandHandler {

    private Repository<Task> repository;

    @Autowired
    public CompleteTaskCommandHandler(Repository<Task> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(CompleteTaskCommand command) {
        ofNullable(repository.load(command.getId()))
                .ifPresent(taskAggreagte -> taskAggreagte.execute(task -> task.complete(command.getId())));
    }
}
