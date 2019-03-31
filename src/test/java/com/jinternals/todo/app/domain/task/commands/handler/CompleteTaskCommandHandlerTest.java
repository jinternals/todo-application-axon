package com.jinternals.todo.app.domain.task.commands.handler;

import com.jinternals.todo.app.domain.task.aggreates.Task;
import com.jinternals.todo.app.domain.task.aggreates.exceptions.TaskAlreadyCompletedException;
import com.jinternals.todo.app.domain.task.aggreates.exceptions.TaskNotStartedException;
import com.jinternals.todo.app.domain.task.commands.CompleteTaskCommand;
import com.jinternals.todo.app.domain.task.events.TaskCompletedEvent;
import com.jinternals.todo.app.domain.task.events.TaskCreatedEvent;
import com.jinternals.todo.app.domain.task.events.TaskStartedEvent;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.modelling.command.Repository;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.junit.Before;
import org.junit.Test;


public class CompleteTaskCommandHandlerTest {

    private AggregateTestFixture<Task> fixture;

    @Before
    public void setUp() {
        fixture = new AggregateTestFixture(Task.class);
        fixture.registerAnnotatedCommandHandler(new CompleteTaskCommandHandler(fixture.getRepository()));
    }

    @Test
    public void shouldCompleteTask() {
        fixture.given(new TaskCreatedEvent("some-id", "some-title"), new TaskStartedEvent("some-id"))
                .when(new CompleteTaskCommand("some-id"))
                .expectEvents(new TaskCompletedEvent("some-id"));

    }

    @Test
    public void shouldNotCompleteTaskIfTaskAlreadyCompleted() {
        fixture.given(new TaskCreatedEvent("some-id", "some-title"), new TaskStartedEvent("some-id"), new TaskCompletedEvent("some-id"))
                .when(new CompleteTaskCommand("some-id"))
                .expectException(TaskAlreadyCompletedException.class);

    }

    @Test
    public void shouldNotCompleteTaskIfTaskNotStartedYet() {
        fixture.given(new TaskCreatedEvent("some-id", "some-title"))
                .when(new CompleteTaskCommand("some-id"))
                .expectException(TaskNotStartedException.class);

    }
}