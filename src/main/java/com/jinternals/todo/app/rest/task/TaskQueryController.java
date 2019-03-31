package com.jinternals.todo.app.rest.task;

import com.jinternals.todo.app.query.task.TaskView;
import com.jinternals.todo.app.query.task.TaskViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class TaskQueryController {

    private final TaskViewRepository repository;

    @Autowired
    public TaskQueryController(TaskViewRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value = "/api/tasks/{id}", method = RequestMethod.GET)
    public TaskView getTask(@PathVariable(value = "id") String id) {
        Optional<TaskView> taskView = repository.findById(id);
        return taskView.get();
    }

}
