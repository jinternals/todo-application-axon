package com.jinternals.todo.app.rest.task;

import com.jinternals.todo.app.domain.task.commands.CompleteTaskCommand;
import com.jinternals.todo.app.domain.task.commands.CreateTaskCommand;
import com.jinternals.todo.app.domain.task.commands.StartTaskCommand;
import com.jinternals.todo.app.query.task.TaskEntryRepository;
import com.jinternals.todo.app.rest.task.requests.CreateTaskRequest;
import com.jinternals.todo.app.rest.task.response.TaskInfo;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.domain.IdentifierFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class TaskCommandController {

    private final IdentifierFactory identifierFactory = IdentifierFactory.getInstance();

    private final TaskEntryRepository taskEntryRepository;

    private final CommandGateway commandGateway;


    @Autowired
    public TaskCommandController(TaskEntryRepository taskEntryRepository, CommandGateway commandGateway) {
        this.taskEntryRepository = taskEntryRepository;
        this.commandGateway = commandGateway;
    }

    @RequestMapping(value = "/api/tasks", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public TaskInfo createTask(@RequestBody @Valid CreateTaskRequest request) {
        TaskInfo info = new TaskInfo(identifierFactory.generateIdentifier());
        commandGateway.send(new CreateTaskCommand(info.getId(), request.getTitle()));
        return info;
    }

    @RequestMapping(value = "/api/tasks/{id}/complete", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void completeTask(@PathVariable(value = "id") String id) {
        commandGateway.send(new CompleteTaskCommand(id));
    }

    @RequestMapping(value = "/api/tasks/{id}/start", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void startTask(@PathVariable(value = "id") String id) {
        commandGateway.send(new StartTaskCommand(id));
    }

}
