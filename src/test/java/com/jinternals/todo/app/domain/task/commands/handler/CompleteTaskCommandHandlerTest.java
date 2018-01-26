package com.jinternals.todo.app.domain.task.commands.handler;

import com.jinternals.todo.app.domain.task.aggreates.Task;
import com.jinternals.todo.app.domain.task.aggreates.exceptions.TaskAlreadyCompletedException;
import com.jinternals.todo.app.domain.task.aggreates.exceptions.TaskNotStartedException;
import com.jinternals.todo.app.domain.task.commands.CompleteTaskCommand;
import com.jinternals.todo.app.domain.task.events.TaskCompletedEvent;
import com.jinternals.todo.app.domain.task.events.TaskCreatedEvent;
import com.jinternals.todo.app.domain.task.events.TaskStartedEvent;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.test.FixtureConfiguration;
import org.junit.Before;
import org.junit.Test;

import static org.axonframework.test.Fixtures.newGivenWhenThenFixture;

public class CompleteTaskCommandHandlerTest {

    private FixtureConfiguration<Task> fixture;
    private EventSourcingRepository<Task> repository;

    @Before
    public void setUp() {
        fixture = newGivenWhenThenFixture(Task.class);
        repository = new EventSourcingRepository<Task>(Task.class, fixture.getEventStore());
        fixture.registerRepository(repository);
        fixture.registerAnnotatedCommandHandler(new CompleteTaskCommandHandler(repository));
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