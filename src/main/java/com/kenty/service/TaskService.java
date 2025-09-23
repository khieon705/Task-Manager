package com.kenty.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

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
        return taskRepository.findById(id);
    }

    public Task updateTask(long id, Task task) {
        return taskRepository.updateTask(id, task);
    }

    public void addTask(Task task) {
        taskRepository.addTask(task); 
    }

    public boolean deleteTask(long id) {
        return taskRepository.deleteTask(id);
    } 
}
