package com.kenty.repository;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.kenty.model.Task;
import com.kenty.model.TaskStatus;

@Repository
public class TaskRepository {
    private final JdbcTemplate jdbc;

    public TaskRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private RowMapper<Task> taskRowMapper() {
        return (rs, i) -> {
            Task rowObject = new Task();
                            
            rowObject.setId(rs.getInt("id"));   
            rowObject.setTitle(rs.getString("title"));
            rowObject.setDescription(rs.getString("description"));
            rowObject.setDueDate(rs.getDate("due_date").toLocalDate());
            System.out.println(rs.getString("status"));
            rowObject.setStatus(TaskStatus.valueOf(rs.getString("status")));

            return rowObject;
        };
    }

    public List<Task> findAll() {
        String sql = "SELECT * FROM task";
        return jdbc.query(sql, taskRowMapper());
    }

    public Task findById(long id) {
        String sql = "SELECT * FROM task WHERE id = ?";
        try {
            return jdbc.queryForObject(sql, new Object[]{id}, taskRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    } 

    public void addTask(Task task) {
        String sql = "INSERT INTO task (title, description, due_date, status) VALUES (?, ?, ?, ?)";
        jdbc.update(sql, task.getTitle(), task.getDescription(), task.getDueDate(), task.getStatus().name());
    }

    public boolean deleteTask(long id) {
        String sql = "DELETE FROM task WHERE id = ?";
        return jdbc.update(sql, id) != 0;
    }

    public Task updateTask(long id, Task task) {
        String sql = "UPDATE task SET title = ?, description = ?, due_date = ?, status = ? WHERE id = ?";
        if ((jdbc.update(sql, task.getTitle(), task.getDescription(), task.getDueDate(), task.getStatus().name(), id)) != 0) {
            task.setId(id);
            return task;
        }

        return null;
    }
}
