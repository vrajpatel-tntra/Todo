package com.example.to_do.controller;

import com.example.to_do.model.Status;
import com.example.to_do.model.Task;
import com.example.to_do.service.TaskService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@Tag(name = "Todo Application",description = "Create, Read, Update, and Delete Tasks Seamlessly")
public class TaskController {

    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);

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
        logger.info("Executing getTasks function");

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return service.getTasks(pageable);
    }


    @PostMapping("/postTask")
//    public void addTask(@RequestBody Task task){
//        service.addTask(task);
//    }

    public ResponseEntity<Task> addTask(@RequestBody Task task){
        try {
            logger.info("Executing addTask function");
//            Task task1 = service.addTask(task);
            return ResponseEntity.status(HttpStatus.OK).body(service.addTask(task));
        } catch (Exception e) {
            logger.warn("Error while adding task with id {} ",task.getTaskId());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @PutMapping("/putTask")

//    public void updateTask(@RequestBody Task task){
//        service.updateTask(task.getTaskId(),task);
//    }

    public ResponseEntity<Task> updateTask(@RequestBody Task task) {
//        try {
//            Task updateTask = service.updateTask(task.getTaskId(), task);
//            return ResponseEntity.ok("Task updated successfully"+service.updateTask(task.getTaskId(),task));
//        } catch (ResponseStatusException e) {
//            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
//        }
        try{
            logger.info("executing updateTask function");
            Task updateTask = service.updateTask(task.getTaskId(),task);
            return ResponseEntity.status(HttpStatus.OK).body(updateTask);

        }
        catch(Exception e){
            logger.error("Error while updating task with id {} ",task.getTaskId());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);

        }
    }

    @DeleteMapping("/deleteTask/{taskId}")
//    public void deleteTask(@PathVariable  int taskId){
//        service.deleteTask(taskId);
//    }

    public ResponseEntity<String> deleteTask(@PathVariable int taskId) {
        try {
            logger.info("Executing deleteTask function");
            service.deleteTask(taskId);
            return ResponseEntity.ok("Task deleted successfully /n Task Id : " + taskId);

        } catch (ResponseStatusException e) {
            logger.error("Error while deleting task with id {} ",taskId);
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());

        }
    }

//    @GetMapping("/getStatus/{status}")
//    public List<Task> findByStatusDone(@PathVariable Status status){
//        return service.findByStatusDone(status);
//    }
//
//    @GetMapping("/getStatus/p/{status}")
//    public List<Task> findByStatusProgress(@PathVariable Status status){
//        return  service.findByStatusProgress(status);
//    }

    @GetMapping("/getStatus/{status}")
    public List<Task> findByStatus(@PathVariable("status") Status status){
        logger.info("Executing findByStatus function");
        List<Task> findByStatusList = service.findByStatus(status);

        logger.info("Successfully executed findByStatus with status {}",status);
        return findByStatusList;

    }
}
