package com.kenty.controller;

import java.util.Collection;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kenty.model.Task;
import com.kenty.service.TaskService;

@RestController
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    public ResponseEntity<Collection<Task>> allTasks() {
        Collection<Task> tasks = taskService.getAllTask();

        if (tasks.isEmpty()) {
            return ResponseEntity
                        .status(HttpStatus.NO_CONTENT)
                        .build();
        } else {
            return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(tasks);
        }
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<Task> taskById(@PathVariable long id) {
        Task task = taskService.getTaskById(id);
        return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(task);
    }

    @PostMapping("/tasks")
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        taskService.addTask(task);
        return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(task);
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable long id, @RequestBody Task updatedTask) {
        Task task = taskService.updateTask(id, updatedTask);
        return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(task);
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable long id) {
        taskService.deleteTask(id);
        return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .build();
    }
}
