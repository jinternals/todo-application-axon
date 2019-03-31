package com.jinternals.todo.app.domain.task.events;

import org.axonframework.serialization.Revision;

@Revision("1")
public class TaskStartedEvent {

    private String id;

    private TaskStartedEvent() {
    }

    public TaskStartedEvent(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
