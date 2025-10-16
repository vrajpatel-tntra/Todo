package com.example.to_do.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.sql.exec.spi.StandardEntityInstanceResolver;

@Data
@Entity
@NoArgsConstructor
public class Task {


    @Id
    private Integer taskId;
    private String taskTitle;


    private String taskDescription;
    private Boolean completed = false;

//    public Task() {
//
//    }

    public Task(int taskId, String taskTitle, String taskDescription, Boolean completed){
        this.taskId=taskId;
        this.taskTitle=taskTitle;
        this.taskDescription=taskDescription;
        this.completed=completed;
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
}
