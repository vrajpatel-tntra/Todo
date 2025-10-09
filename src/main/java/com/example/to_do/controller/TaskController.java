package com.example.to_do.controller;

import com.example.to_do.model.Task;
import com.example.to_do.service.TaskService;
import org.hibernate.sql.results.internal.StandardEntityGraphTraversalStateImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class TaskController {

    @Autowired
    TaskService service;

//    @GetMapping("/getTask")
//    public List<Task> getTasks(){
//        return service.getTasks();
//    }

    @GetMapping("/getTask")
    public Page<Task> getTasks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "taskId") String sortBy
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return service.getTasks(pageable);
    }

    @PostMapping("/postTask")
//    public void addTask(@RequestBody Task task){
//        service.addTask(task);
//    }

    public ResponseEntity<String> addTask(@RequestBody Task task){
        try {
            service.addTask(task);
            return ResponseEntity.status(HttpStatus.CREATED).body("Task created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create task: " + e.getMessage());
        }
    }


    @PutMapping("/putTask")
//    public void updateTask(@RequestBody Task task){
//        service.updateTask(task.getTaskId(),task);
//    }

    public ResponseEntity<String> updateTask(@RequestBody Task task) {
        try {
            service.updateTask(task.getTaskId(), task);
            return ResponseEntity.ok("Task updated successfully");
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    @DeleteMapping("/deleteTask/{taskId}")
//    public void deleteTask(@PathVariable  int taskId){
//        service.deleteTask(taskId);
//    }

    public ResponseEntity<String> deleteTask(@PathVariable int taskId) {
        try {
            service.deleteTask(taskId);
            return ResponseEntity.ok("Task deleted successfully");
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }
}
