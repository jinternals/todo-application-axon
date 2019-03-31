package com.jinternals.todo.app.domain.task.commands.handler;

import com.jinternals.todo.app.domain.task.aggreates.Task;
import com.jinternals.todo.app.domain.task.commands.StartTaskCommand;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.modelling.command.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static java.util.Optional.ofNullable;


@Component
public class StartTaskCommandHandler {

    private Repository<Task> repository;

    @Autowired
    public StartTaskCommandHandler(Repository<Task> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(StartTaskCommand command) {
        ofNullable(repository.load(command.getId()))
                .ifPresent(taskAggregate -> taskAggregate.execute(task -> task.start(command.getId())));
    }
}
