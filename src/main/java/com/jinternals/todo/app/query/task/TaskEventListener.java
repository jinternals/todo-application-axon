package com.jinternals.todo.app.query.task;

import com.jinternals.todo.app.domain.task.events.TaskCompletedEvent;
import com.jinternals.todo.app.domain.task.events.TaskCreatedEvent;
import com.jinternals.todo.app.domain.task.events.TaskStartedEvent;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskEventListener {

    private final TaskEntryRepository taskEntryRepository;

    @Autowired
    public TaskEventListener(TaskEntryRepository taskEntryRepository) {
        this.taskEntryRepository = taskEntryRepository;
    }

    @EventHandler
    void on(TaskCreatedEvent event) {
        TaskEntry task = new TaskEntry(event.getId(), event.getTitle());
        taskEntryRepository.save(task);
    }

    @EventHandler
    void on(TaskCompletedEvent event) {
        TaskEntry task = taskEntryRepository.findOne(event.getId());
        task.setCompleted(true);
        taskEntryRepository.save(task);
    }

    @EventHandler
    void on(TaskStartedEvent event) {
        TaskEntry task = taskEntryRepository.findOne(event.getId());
        task.setStarred(true);
        taskEntryRepository.save(task);
    }


}
