package com.jinternals.todo.app.domain.task.commands;


public class CreateTaskCommand {

    private String id;

    private String title;

    public CreateTaskCommand(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

}
