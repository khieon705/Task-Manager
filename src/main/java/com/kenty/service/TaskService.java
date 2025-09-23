package com.kenty.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.kenty.exception.NoTaskFoundException;
import com.kenty.model.Task;
import com.kenty.repository.TaskRepository;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Collection<Task> getAllTask() {
        return taskRepository.findAll();
    }

    public Task getTaskById(long id) {
        Task task = taskRepository.findById(id);

        if (task == null) {
            throw new NoTaskFoundException("Task with id " + id + " not found");
        }

        return task;
    }

    public Task updateTask(long id, Task task) {
        Task t = taskRepository.updateTask(id, task);
        
        if (t == null) {
            throw new NoTaskFoundException("Task with id " + id + " not found");
        }

        return t;
    }

    public void addTask(Task task) {
        taskRepository.addTask(task); 
    }

    public void deleteTask(long id) {
        boolean success = taskRepository.deleteTask(id);

        if (success == false) {
            throw new NoTaskFoundException("Task with id " + id + " not found");
        }
    } 
}
