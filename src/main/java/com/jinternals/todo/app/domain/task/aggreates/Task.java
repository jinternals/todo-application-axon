package com.jinternals.todo.app.domain.task.aggreates;

import com.jinternals.todo.app.domain.task.aggreates.exceptions.TaskAlreadyCompletedException;
import com.jinternals.todo.app.domain.task.aggreates.exceptions.TaskAlreadyStartedException;
import com.jinternals.todo.app.domain.task.aggreates.exceptions.TaskNotStartedException;
import com.jinternals.todo.app.domain.task.commands.CreateTaskCommand;
import com.jinternals.todo.app.domain.task.events.TaskCompletedEvent;
import com.jinternals.todo.app.domain.task.events.TaskCreatedEvent;
import com.jinternals.todo.app.domain.task.events.TaskStartedEvent;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;

import javax.persistence.Id;


public class Task extends AbstractAnnotatedAggregateRoot<String> {

    private static final long serialVersionUID = -5977984483620451665L;

    @Id
    @AggregateIdentifier
    private String id;

    private String title;

    private boolean completed;

    private boolean start;

    public Task(CreateTaskCommand command) {
        apply(new TaskCreatedEvent(command.getId(), command.getTitle()));
    }

    private Task() {
    }

    @EventSourcingHandler
    void on(TaskCreatedEvent event) {
        this.id = event.getId();
        this.title = event.getTitle();
    }

    @EventSourcingHandler
    void on(TaskCompletedEvent event) {
        this.completed = true;
    }

    @EventSourcingHandler
    void on(TaskStartedEvent event) {
        this.start = true;
    }

    public void complete(String id) {
        validateTaskAlreadyCompleted(id);
        validateNotStarted(id);
        apply(new TaskCompletedEvent(id));
    }

    public void start(String id) {
        validateTaskAlreadyStarted(id);
        apply(new TaskStartedEvent(id));
    }

    private void validateTaskAlreadyCompleted(String id) {
        if (completed) {
            throw new TaskAlreadyCompletedException("Task [ identifier = " + id + " ] is completed.");
        }
    }

    private void validateTaskAlreadyStarted(String id) {
        if (start) {
            throw new TaskAlreadyStartedException("Task [ identifier = " + id + " ] is already started.");
        }
    }

    private void validateNotStarted(String id) {
        if (!start) {
            throw new TaskNotStartedException("Task [ identifier = " + id + " ] is not yet started.");
        }
    }

}
