package com.kenty.repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import com.kenty.model.Task;

@Repository
public class TaskRepository {
    private final AtomicLong numberOfTasks = new AtomicLong(0);
    private final ConcurrentHashMap<Long, Task> tasks;

    public TaskRepository() {
        tasks = new ConcurrentHashMap<>(); 
    }

    public Collection<Task> findAll() {
        return tasks.values();
    }

    public Task findById(long id) {
        return tasks.get(id);
    } 

    public void addTask(Task task) {
        task.setId(numberOfTasks.incrementAndGet());
        tasks.put(task.getId(), task);
    }

    public boolean deleteTask(long id) {
        return tasks.remove(id) != null;
    }

    public Task updateTask(long id, Task task) {
        if (tasks.containsKey(id)) {
            task.setId(id);
            tasks.put(id, task);
            return task;
        }
        return null;
    }
}
