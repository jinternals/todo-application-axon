package com.jinternals.todo.app.query.task;

import org.springframework.data.repository.CrudRepository;

public interface TaskEntryRepository extends CrudRepository<TaskEntry, String> {
}
