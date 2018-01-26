package com.jinternals.todo.app.domain.task.events;


import org.axonframework.serializer.Revision;

@Revision("1")
public class TaskCompletedEvent {

    private String id;

    public TaskCompletedEvent() {
    }

    public TaskCompletedEvent(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
