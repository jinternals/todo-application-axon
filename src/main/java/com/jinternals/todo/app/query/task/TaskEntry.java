package com.jinternals.todo.app.query.task;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class TaskEntry {

    @Id
    private String id;

    private String title;

    private boolean completed;

    private boolean started;

    public TaskEntry() {
    }

    public TaskEntry(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public boolean isStarted() {
        return started;
    }

    public void setStarred(boolean starred) {
        this.started = starred;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TaskEntry)) return false;
        TaskEntry taskEntry = (TaskEntry) o;
        return isCompleted() == taskEntry.isCompleted() &&
                isStarted() == taskEntry.isStarted() &&
                Objects.equals(getId(), taskEntry.getId()) &&
                Objects.equals(getTitle(), taskEntry.getTitle());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getTitle(), isCompleted(), isStarted());
    }

    @Override
    public String toString() {
        return "TaskEntry{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                ", started=" + started +
                '}';
    }
}