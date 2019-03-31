package com.jinternals.todo.app.domain.task.aggreates;

import org.axonframework.eventsourcing.EventSourcedAggregate;
import org.axonframework.modelling.command.LockAwareAggregate;

import java.util.Optional;

public class AggregateOptional {

    public static <T> Optional<T> ofNullable( LockAwareAggregate<T, EventSourcedAggregate<T>> value) {
        return value == null ? Optional.empty() : Optional.of(value.getWrappedAggregate().getAggregateRoot());
    }


}
