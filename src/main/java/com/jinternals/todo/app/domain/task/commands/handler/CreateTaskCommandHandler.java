package com.jinternals.todo.app.domain.task.commands.handler;

import com.jinternals.todo.app.domain.task.aggreates.Task;
import com.jinternals.todo.app.domain.task.commands.CreateTaskCommand;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateTaskCommandHandler {

    private EventSourcingRepository<Task> repository;

    @Autowired
    public CreateTaskCommandHandler(EventSourcingRepository<Task> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(CreateTaskCommand command) {
        repository.add(new Task(command));

    }
}
