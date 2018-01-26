package com.jinternals.todo.app.domain.task.commands.handler;


import com.jinternals.todo.app.domain.task.aggreates.Task;
import com.jinternals.todo.app.domain.task.commands.CreateTaskCommand;
import com.jinternals.todo.app.domain.task.events.TaskCreatedEvent;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.test.FixtureConfiguration;
import org.junit.Before;
import org.junit.Test;

import static org.axonframework.test.Fixtures.newGivenWhenThenFixture;

public class CreateTaskCommandHandlerTest {

    private FixtureConfiguration<Task> fixture;
    private EventSourcingRepository<Task> repository;

    @Before
    public void setUp() {
        fixture = newGivenWhenThenFixture(Task.class);
        repository = new EventSourcingRepository<Task>(Task.class, fixture.getEventStore());
        fixture.registerRepository(repository);
        fixture.registerAnnotatedCommandHandler(new CreateTaskCommandHandler(repository));
    }

    @Test
    public void shouldEmitTaskCreatedEvent() {
        fixture.given()
                .when(new CreateTaskCommand("some-id", "some-title"))
                .expectEvents(new TaskCreatedEvent("some-id", "some-title"));

    }
}