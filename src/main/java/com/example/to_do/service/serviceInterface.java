package com.example.to_do.service;

import com.example.to_do.model.Status;
import com.example.to_do.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface serviceInterface {
    Page<Task> getTasks(Pageable pageable) ;
    Task addTask(Task task);
    Task updateTask(int taskId, Task updatedTask);
    Task deleteTask(int taskId);
//    List<Task> findByStatusDone(Status status);
//    List<Task> findByStatusProgress(Status status);

    List<Task> findByStatus(Status status);
}
