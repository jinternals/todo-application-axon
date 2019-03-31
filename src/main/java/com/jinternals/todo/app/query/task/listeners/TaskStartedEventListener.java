package com.jinternals.todo.app.query.task.listeners;

import com.jinternals.todo.app.domain.task.events.TaskStartedEvent;
import com.jinternals.todo.app.query.task.TaskView;
import com.jinternals.todo.app.query.task.TaskViewRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TaskStartedEventListener {

    private final TaskViewRepository taskEntryRepository;

    @Autowired
    public TaskStartedEventListener(TaskViewRepository taskEntryRepository) {
        this.taskEntryRepository = taskEntryRepository;
    }

    @EventHandler
    void on(TaskStartedEvent event) {
        Optional<TaskView> task = taskEntryRepository.findById(event.getId());

        task.ifPresent(taskView -> {
            taskView.setStarred(true);
            taskEntryRepository.save(taskView);
        });
    }

}
