package com.example.to_do.repository;

import ch.qos.logback.core.status.StatusUtil;
import com.example.to_do.model.Status;
import com.example.to_do.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.*;

public interface TaskRepo extends JpaRepository<Task,Integer> {

//    @Query(value = "select * from Task where status = 'DONE'",nativeQuery = true)
//    public List<Task> findByStatusDone(Status status);
//
//    @Query(value = "SELECT * FROM task WHERE status = 'PROGRESS'",nativeQuery = true)
//    public List<Task> findByStatusProgress(Status status);

    @Query(value = "SELECT * FROM Task WHERE status = :status",nativeQuery = true)
    public List<Task> findByStatus(@Param("status") String status);
}

