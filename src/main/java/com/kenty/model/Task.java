package com.kenty.model;

import java.time.LocalDate;

public class Task implements Comparable<Task>{
    private final long id;
    private String title;
    private String description;
    private LocalDate dueDate;
    private TaskStatus status;

    private static long taskInstanceCount = 0;

    public Task() {
        this.id = ++taskInstanceCount;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setStatus(String status) {
        switch (status) {
            case "PENDING" -> this.status = TaskStatus.PENDING;
            case "DONE" -> this.status = TaskStatus.DONE;
        }
    }

    public long getId() {
        return id;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    @Override
    public int compareTo(Task o) {
        if (this.dueDate.isBefore(o.getDueDate())) {
            return -1;
        } else if (this.dueDate.isAfter(o.getDueDate())) {
            return 1;
        } else {
            return this.title.compareTo(o.getTitle());
        }
    }
}