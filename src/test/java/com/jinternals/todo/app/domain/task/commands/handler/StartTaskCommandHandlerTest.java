package com.jinternals.todo.app.domain.task.commands.handler;

import com.jinternals.todo.app.domain.task.aggreates.Task;
import com.jinternals.todo.app.domain.task.aggreates.exceptions.TaskAlreadyStartedException;
import com.jinternals.todo.app.domain.task.commands.StartTaskCommand;
import com.jinternals.todo.app.domain.task.events.TaskCreatedEvent;
import com.jinternals.todo.app.domain.task.events.TaskStartedEvent;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.test.FixtureConfiguration;
import org.junit.Before;
import org.junit.Test;

import static org.axonframework.test.Fixtures.newGivenWhenThenFixture;

public class StartTaskCommandHandlerTest {

    private FixtureConfiguration<Task> fixture;
    private EventSourcingRepository<Task> repository;

    @Before
    public void setUp() {
        fixture = newGivenWhenThenFixture(Task.class);
        repository = new EventSourcingRepository<Task>(Task.class, fixture.getEventStore());
        fixture.registerRepository(repository);
        fixture.registerAnnotatedCommandHandler(new StartTaskCommandHandler(repository));
    }

    @Test
    public void shouldStartTask() {
        fixture.given(new TaskCreatedEvent("some-id", "some-title"))
                .when(new StartTaskCommand("some-id"))
                .expectEvents(new TaskStartedEvent("some-id"));

    }

    @Test
    public void shouldNotStartTaskIfAlreadyStarted() {
        fixture.given(new TaskCreatedEvent("some-id", "some-title"), new TaskStartedEvent("some-id"))
                .when(new StartTaskCommand("some-id"))
                .expectException(TaskAlreadyStartedException.class);

    }

}