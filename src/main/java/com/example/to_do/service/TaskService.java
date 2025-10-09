package com.example.to_do.service;

import com.example.to_do.model.Task;
import com.example.to_do.repository.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TaskService {

    @Autowired
    TaskRepo repo;

    // GET - READ (Updated for pagination)
    public Page<Task> getTasks(Pageable pageable) {
        return repo.findAll(pageable);
    }

    // POST - CREATE
    public void addTask(Task task) {
        repo.save(task);
    }

     // PUT - UPDATE
//    public void updateTask(Task task){
//        repo.save(task);
//    }

    // UPDATE
    public void updateTask(int taskId, Task updatedTask) {
        Task existingTask = repo.findById(taskId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Task with ID " + taskId + " not found"
                ));

        existingTask.setTaskTitle(updatedTask.getTaskTitle());
        existingTask.setTaskDescription(updatedTask.getTaskDescription());
        existingTask.setCompleted(updatedTask.getCompleted());

        repo.save(existingTask);
    }


//    public void updateTask(Task task) {
//        if (!repo.existsById(task.getTaskId())) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task with ID " + task.getTaskId() + " not found");
//        }
//        repo.save(task);  // Now safe to update
//    }

    // DELETE
    public void deleteTask(int taskId) {
        repo.deleteById(taskId);
    }
}