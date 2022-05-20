package com.omeasraf.TaskTracker.Services;

import com.omeasraf.TaskTracker.Models.Task;
import com.omeasraf.TaskTracker.Repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class TaskService {
    public final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return this.taskRepository.findAll();
    }

    public Optional<Task> findTask(Long id) {
        return this.taskRepository.findById(id);
    }

    public void deleteTask(Long id) {
        this.taskRepository.deleteByID(id);
    }

    public Task addTask(Task task) {
        return this.taskRepository.save(task);
    }

    public Task updateTask(Task task) {
        Optional<Task> taskValue = this.taskRepository.findById(task.getId());
        if (taskValue.isPresent()) {
            return this.taskRepository.save(task);
        }

        return null;

    }
}
