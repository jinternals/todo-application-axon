package com.jinternals.todo.app.domain.task.commands.handler;

import com.jinternals.todo.app.domain.task.aggreates.Task;
import com.jinternals.todo.app.domain.task.commands.CreateTaskCommand;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.modelling.command.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateTaskCommandHandler {

    private Repository<Task> repository;

    @Autowired
    public CreateTaskCommandHandler(Repository<Task> repository) {
        this.repository = repository;
    }
    @CommandHandler
    public void handle(CreateTaskCommand command) throws Exception {
        repository.newInstance(() -> new Task(command));

    }
}
