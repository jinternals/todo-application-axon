package com.jinternals.todo.app.rest.task;

import com.jinternals.todo.app.query.task.TaskEntry;
import com.jinternals.todo.app.query.task.TaskEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskQueryController {

    private final TaskEntryRepository repository;

    @Autowired
    public TaskQueryController(TaskEntryRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value = "/api/tasks/{id}", method = RequestMethod.GET)
    public TaskEntry getTask(@PathVariable(value = "id") String id) {
        return repository.findOne(id);
    }

}
