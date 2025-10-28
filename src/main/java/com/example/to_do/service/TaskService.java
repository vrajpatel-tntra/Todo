package com.example.to_do.service;

import com.example.to_do.model.Status;
import com.example.to_do.model.Task;
import com.example.to_do.repository.TaskRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TaskService implements serviceInterface {

    private static final Logger logger = LoggerFactory.getLogger(TaskService.class);
    @Autowired
    TaskRepo repo;

    // GET - READ (Updated for pagination)
    public Page<Task> getTasks(Pageable pageable) {
//        return repo.findAll(pageable);

        logger.info("Entering getTasks() with page number: {}, page size: {}",
                pageable.getPageNumber(), pageable.getPageSize());

        Page<Task> tasks = repo.findAll(pageable);

        logger.debug("Retrieved {} tasks from repository", tasks.getTotalElements());
        logger.info("Exiting getTasks() successfully");
        return tasks;
    }

    // POST - CREATE
    public Task addTask(Task task) {
//        return repo.save(task);

        try{
            Task savedTask = repo.save(task);
            logger.info("Task saved successfully with id {} ",savedTask.getTaskId());
            return savedTask;
        }catch (Exception e){
            logger.error("Error occurred while saving {} ",task);
            throw e;
        }

    }

     // PUT - UPDATE
//    public void updateTask(Task task){
//        repo.save(task);
//    }

    // UPDATE
    public Task updateTask(int taskId, Task updatedTask) {
        logger.info("Executing updateTask() for taskId: {}", taskId);

        try {
            // Fetch existing task
            Task existingTask = repo.findById(taskId)
                    .orElseThrow(() -> {
                        logger.warn("Task with ID {} not found in updateTask()", taskId);
                        return new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Task with ID " + taskId + " not found"
                        );
                    });

            logger.debug("Existing task found: {}", existingTask);

            // Update fields
            existingTask.setTaskTitle(updatedTask.getTaskTitle());
            existingTask.setTaskDescription(updatedTask.getTaskDescription());
            existingTask.setCompleted(updatedTask.getCompleted());

            logger.debug("Task updated with new details: {}", updatedTask);

            // Save updated entity
            Task savedTask = repo.save(existingTask);
            logger.info("Task with ID {} updated successfully", taskId);

            return savedTask;

        } catch (ResponseStatusException e) {
            // Log specific not-found case at WARN level (already logged above)
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error while updating task with ID {}", taskId, e);
            throw e;
        }

//        Task existingTask = repo.findById(taskId)
//                .orElseThrow(() -> new ResponseStatusException(
//                        HttpStatus.NOT_FOUND,
//                        "Task with ID " + taskId + " not found"
//                ));
//
//
//        existingTask.setTaskTitle(updatedTask.getTaskTitle());
//        existingTask.setTaskDescription(updatedTask.getTaskDescription());
//        existingTask.setCompleted(updatedTask.getCompleted());
//
//        return  repo.save(existingTask);


    }


//    public void updateTask(Task task) {
//        if (!repo.existsById(task.getTaskId())) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task with ID " + task.getTaskId() + " not found");
//        }
//        repo.save(task);  // Now safe to update
//    }

    // DELETE
    public Task deleteTask(int taskId) {
//        Task task = repo.findById(taskId)
//                .orElseThrow(() -> new ResponseStatusException(
//                        HttpStatus.NOT_FOUND,
//                        "Task with Id"+" not found"
//                ));
//        repo.delete(task);
//        return task;

        try {
            // Step 1: Check if task exists
            Task task = repo.findById(taskId)
                    .orElseThrow(() -> {
                        logger.warn("Task with ID {} not found in deleteTask()", taskId);
                        return new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Task with ID " + taskId + " not found"
                        );
                    });

            logger.debug("Task found for deletion: {}", task);

            // Step 2: Delete the task
            repo.delete(task);
            logger.info("Task with ID {} deleted successfully", taskId);

            // Step 3: Return deleted entity (optional)
            return task;

        } catch (ResponseStatusException e) {
            // Already logged specific not-found case
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error while deleting task with ID {}", taskId, e);
            throw e;
        }
    }

//    public List<Task> findByStatusDone(Status status){
//        return repo.findByStatusDone(status);
//    }
//
//    public List<Task> findByStatusProgress(Status status){
//        return repo.findByStatusProgress(status);

    public List<Task> findByStatus(Status status) {
        logger.info("Entering findByStatus() with status: {}", status);

        try {
            List<Task> tasks = repo.findByStatus(status.name());
            logger.debug("Fetched {} tasks with status: {}", tasks.size(), status);
            logger.info("Exiting findByStatus() successfully for status: {}", status);
            return tasks;
        } catch (Exception e) {
            logger.error("Error occurred while fetching tasks with status: {}", status, e);
            throw e;
        }
    }
}