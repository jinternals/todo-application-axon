package com.jinternals.todo.app.domain.task.events;

import org.axonframework.serializer.Revision;

@Revision("1")
public class TaskCreatedEvent {

    private String id;

    private String title;

    public TaskCreatedEvent() {}

    public TaskCreatedEvent(String id, String title) {

        this.id = id;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getId() {
        return id;
    }

}
