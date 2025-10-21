package com.example.to_do.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.sql.exec.spi.StandardEntityInstanceResolver;

@Data
@Entity
@NoArgsConstructor
public class Task {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer taskId;
    private String taskTitle;
    private String taskDescription;
    private Boolean completed = false;

    @Enumerated(EnumType.STRING)
    private Status status;

//    public Task() {
//
//    }

    public Task(int taskId, String taskTitle, String taskDescription, Boolean complete,Status status){
        this.taskId=taskId;
        this.taskTitle=taskTitle;
        this.taskDescription=taskDescription;
//      this.completed=completed;
        this.status=status;
    }



    public int getTaskId(){
        return taskId;
    }

//    public String getTaskTitle() {
//        return taskTitle;
//    }

    public String  getTaskDescription() {
        return taskDescription;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }
    public Boolean getCompleted() {
        return completed;
    }

    public Status getStatus(){
        return status;
    }

    public void setStatus(Status status){
        this.status=status;
    }
}
