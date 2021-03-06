package com.jinternals.todo.app.domain.task.commands.handler;

import com.jinternals.todo.app.domain.task.aggreates.Task;
import com.jinternals.todo.app.domain.task.commands.StartTaskCommand;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static java.util.Optional.ofNullable;

@Component
public class StartTaskCommandHandler {

    @Autowired
    public StartTaskCommandHandler(EventSourcingRepository<Task> repository) {
        this.repository = repository;
    }

    private EventSourcingRepository<Task> repository;

    @CommandHandler
    public void handle(StartTaskCommand command) {
        ofNullable(repository.load(command.getId()))
                .ifPresent(task -> task.start(command.getId()));
    }
}
