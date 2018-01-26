package com.jinternals.todo.app.query.task.listeners;

import com.jinternals.todo.app.domain.task.events.TaskCompletedEvent;
import com.jinternals.todo.app.query.task.TaskView;
import com.jinternals.todo.app.query.task.TaskViewRepository;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskCompletedEventListener {

    private final TaskViewRepository taskEntryRepository;

    @Autowired
    public TaskCompletedEventListener(TaskViewRepository taskEntryRepository) {
        this.taskEntryRepository = taskEntryRepository;
    }


    @EventHandler
    void on(TaskCompletedEvent event) {
        TaskView task = taskEntryRepository.findOne(event.getId());
        task.setCompleted(true);
        taskEntryRepository.save(task);
    }

}
