package com.example.to_do.repository;

import com.example.to_do.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepo extends JpaRepository<Task,Integer> {
}

