package com.jinternals.todo.app.query.task.listeners;

import com.jinternals.todo.app.domain.task.events.TaskCreatedEvent;
import com.jinternals.todo.app.query.task.TaskView;
import com.jinternals.todo.app.query.task.TaskViewRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskCreatedEventListener {

    private final TaskViewRepository taskEntryRepository;

    @Autowired
    public TaskCreatedEventListener(TaskViewRepository taskEntryRepository) {
        this.taskEntryRepository = taskEntryRepository;
    }

    @EventHandler
    void on(TaskCreatedEvent event) {
        TaskView task = new TaskView(event.getId(), event.getTitle());
        taskEntryRepository.save(task);
    }
}
