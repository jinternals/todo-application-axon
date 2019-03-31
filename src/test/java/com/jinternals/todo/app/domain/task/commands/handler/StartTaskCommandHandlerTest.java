package com.jinternals.todo.app.domain.task.commands.handler;

import com.jinternals.todo.app.domain.task.aggreates.Task;
import com.jinternals.todo.app.domain.task.aggreates.exceptions.TaskAlreadyStartedException;
import com.jinternals.todo.app.domain.task.commands.StartTaskCommand;
import com.jinternals.todo.app.domain.task.events.TaskCreatedEvent;
import com.jinternals.todo.app.domain.task.events.TaskStartedEvent;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.junit.Before;
import org.junit.Test;

public class StartTaskCommandHandlerTest {

    private AggregateTestFixture<Task> fixture;

    @Before
    public void setUp() {
        fixture = new AggregateTestFixture(Task.class);
        fixture.registerAnnotatedCommandHandler(new StartTaskCommandHandler(fixture.getRepository()));
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