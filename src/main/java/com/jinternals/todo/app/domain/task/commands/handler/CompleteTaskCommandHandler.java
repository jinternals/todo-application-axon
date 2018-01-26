package com.jinternals.todo.app.domain.task.commands.handler;

import com.jinternals.todo.app.domain.task.aggreate.Task;
import com.jinternals.todo.app.domain.task.commands.CompleteTaskCommand;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static java.util.Optional.ofNullable;

@Component
public class CompleteTaskCommandHandler {

    @Autowired
    private EventSourcingRepository<Task> repository;


    @CommandHandler
    public void handle(CompleteTaskCommand command) {
        ofNullable(repository.load(command.getId()))
                .ifPresent(task -> task.complete(command.getId()));
    }
}