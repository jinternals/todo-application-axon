package com.jinternals.todo.app.domain.task.commands.handler;


import com.jinternals.todo.app.domain.task.aggreates.Task;
import com.jinternals.todo.app.domain.task.commands.CreateTaskCommand;
import com.jinternals.todo.app.domain.task.events.TaskCreatedEvent;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.Before;
import org.junit.Test;


public class CreateTaskCommandHandlerTest {

    private FixtureConfiguration<Task> fixture;
    private AggregateTestFixture<Task> repository;

    @Before
    public void setUp() {
        fixture = new AggregateTestFixture(Task.class);
        fixture.registerAnnotatedCommandHandler(new CreateTaskCommandHandler(fixture.getRepository()));
    }


    @Test
    public void shouldEmitTaskCreatedEvent() {
        fixture.given()
                .when(new CreateTaskCommand("some-id", "some-title"))
                .expectEvents(new TaskCreatedEvent("some-id", "some-title"));

    }
}