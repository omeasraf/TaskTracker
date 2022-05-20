package com.omeasraf.TaskTracker.Controllers;

import com.omeasraf.TaskTracker.Models.Task;
import com.omeasraf.TaskTracker.Services.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/tasks")
@SuppressWarnings("unused")
public class TaskController {

    public final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Task>> getAll() {
        return new ResponseEntity<>(this.taskService.getAllTasks(), HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Task> getTaskByID(@PathVariable("id") Long id) {
        Optional<Task> task = this.taskService.findTask(id);
        return task.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @PostMapping("/add")
    public ResponseEntity<Task> addTask(@RequestBody Task task) {
        return new ResponseEntity<>(this.taskService.addTask(task), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTaskByID(@PathVariable("id") Long id) {
        this.taskService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Task> updateTask(@RequestBody Task task) {
        return new ResponseEntity<>(this.taskService.updateTask(task), HttpStatus.CREATED);
    }

}
