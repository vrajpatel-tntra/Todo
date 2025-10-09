//package com.example.to_do.controller;
//
//import com.example.to_do.model.Task;
//import com.example.to_do.service.TaskService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.NoArgsConstructor;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.web.server.ResponseStatusException;
//
//import java.util.List;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.eq;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@WebMvcTest(TaskController.class)
//
//public class TaskControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private TaskService taskService;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    private Task task;
//
//    @BeforeEach
//    void setUp() {
//        task = new Task();
//        task.setTaskId(121);
//        task.setTaskTitle("Learn Java and python");
//        task.setTaskDescription("Practice pandas and yfinance library");
//        task.setCompleted(true);
//    }
//
//    @Test
//    void testGetTasksWithPagination() throws Exception {
//        // Arrange
//        List<Task> tasks = List.of(
//                new Task(121, "Learn Java and python", "Practice pandas and yfinance library", true),
//                new Task(122, "Buy groceries", "Milk and bread", true)
//        );
//        Page<Task> taskPage = new PageImpl<>(tasks, PageRequest.of(0, 10), 20);
//        when(taskService.getTasks(any(Pageable.class))).thenReturn(taskPage);
//
//        // Act & Assert
//        mockMvc.perform(get("/getTask?page=0&size=10&sortBy=taskId"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.content.length()").value(2))
//                .andExpect(jsonPath("$.content[0].taskId").value(121))
//                .andExpect(jsonPath("$.content[0].taskTitle").value("Learn Java and python"))
//                .andExpect(jsonPath("$.content[1].taskId").value(122))
//                .andExpect(jsonPath("$.totalElements").value(20))
//                .andExpect(jsonPath("$.totalPages").value(2))
//                .andExpect(jsonPath("$.number").value(0))
//                .andExpect(jsonPath("$.size").value(10));
//    }
//
//    @Test
//    void testAddTask() throws Exception {
//        // Arrange
//        doNothing().when(taskService).addTask(any(Task.class));
//
//        // Act & Assert
//        mockMvc.perform(post("/postTask")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(task)))
//                .andExpect(status().isOk());
//
//        verify(taskService, times(1)).addTask(any(Task.class));
//    }
//
//    @Test
//    void testUpdateTaskSuccess() throws Exception {
//        // Arrange
//        Task updatedTask = new Task();
//        updatedTask.setTaskId(121);
//        updatedTask.setTaskTitle("Learn Java and Python Advanced");
//        updatedTask.setTaskDescription("Practice pandas, yfinance, and Spring Boot");
//        updatedTask.setCompleted(false);
//
//        doNothing().when(taskService).updateTask(eq(121), any(Task.class));
//
//        // Act & Assert
//        mockMvc.perform(put("/putTask")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(updatedTask)))
//                .andExpect(status().isOk());
//
//        verify(taskService, times(1)).updateTask(eq(121), any(Task.class));
//    }
//
//    @Test
//    void testUpdateTaskNotFound() throws Exception {
//        // Arrange
//        Task updatedTask = new Task();
//        updatedTask.setTaskId(999);
//        updatedTask.setTaskTitle("Non-existent task");
//        updatedTask.setTaskDescription("This should fail");
//        updatedTask.setCompleted(false);
//
//        doThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, "Task with ID 999 not found"))
//                .when(taskService).updateTask(eq(999), any(Task.class));
//
//        // Act & Assert
//        mockMvc.perform(put("/putTask")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(updatedTask)))
//                .andExpect(status().isNotFound())
//                .andExpect(jsonPath("$.message").value("Task with ID 999 not found"));
//
//        verify(taskService, times(1)).updateTask(eq(999), any(Task.class));
//    }
//
//    @Test
//    void testDeleteTaskSuccess() throws Exception {
//        // Arrange
//        doNothing().when(taskService).deleteTask(121);
//
//        // Act & Assert
//        mockMvc.perform(delete("/deleteTask/121"))
//                .andExpect(status().isOk());
//
//        verify(taskService, times(1)).deleteTask(121);
//    }
//
//    @Test
//    void testDeleteTaskNotFound() throws Exception {
//        // Arrange
//        doThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, "Task with ID 999 not found"))
//                .when(taskService).deleteTask(999);
//
//        // Act & Assert
//        mockMvc.perform(delete("/deleteTask/999"))
//                .andExpect(status().isNotFound())
//                .andExpect(jsonPath("$.message").value("Task with ID 999 not found"));
//
//        verify(taskService, times(1)).deleteTask(999);
//    }
//}





package com.example.to_do.controller;

import com.example.to_do.model.Task;
import com.example.to_do.service.TaskService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;

    @Autowired
    private ObjectMapper objectMapper;

    private Task task;

    @BeforeEach
    void setUp() {
        task = new Task(121, "Learn Java and python", "Practice pandas and yfinance library", true);
    }

    @Test
    void testGetTasksWithPagination() throws Exception {
        List<Task> tasks = List.of(
                new Task(121, "Learn Java and python", "Practice pandas and yfinance library", true),
                new Task(122, "Buy groceries", "Milk and bread", true)
        );
        Page<Task> taskPage = new PageImpl<>(tasks, PageRequest.of(0, 10), 20);
        when(taskService.getTasks(any(Pageable.class))).thenReturn(taskPage);

        mockMvc.perform(get("/getTask?page=0&size=10&sortBy=taskId"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()").value(2))
                .andExpect(jsonPath("$.content[0].taskId").value(121))
                .andExpect(jsonPath("$.content[0].taskTitle").value("Learn Java and python"))
                .andExpect(jsonPath("$.content[1].taskId").value(122))
                .andExpect(jsonPath("$.totalElements").value(20))
                .andExpect(jsonPath("$.totalPages").value(2))
                .andExpect(jsonPath("$.number").value(0))
                .andExpect(jsonPath("$.size").value(10));
    }

    @Test
    void testAddTask() throws Exception {
        doNothing().when(taskService).addTask(any(Task.class));

        mockMvc.perform(post("/postTask")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(task)))
                .andExpect(status().isCreated())
                .andExpect(content().string("Task created successfully"));

        verify(taskService, times(1)).addTask(any(Task.class));
    }

    @Test
    void testUpdateTaskSuccess() throws Exception {
        Task updatedTask = new Task(121, "Learn Java and Python Advanced", "Practice pandas, yfinance, and Spring Boot", false);
        doNothing().when(taskService).updateTask(eq(121), any(Task.class));

        mockMvc.perform(put("/putTask")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedTask)))
                .andExpect(status().isOk())
                .andExpect(content().string("Task updated successfully"));

        verify(taskService, times(1)).updateTask(eq(121), any(Task.class));
    }

    @Test
    void testUpdateTaskNotFound() throws Exception {
        Task updatedTask = new Task(999, "Non-existent task", "This should fail", false);
        doThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, "Task with ID 999 not found"))
                .when(taskService).updateTask(eq(999), any(Task.class));

        mockMvc.perform(put("/putTask")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedTask)))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Task with ID 999 not found"));

        verify(taskService, times(1)).updateTask(eq(999), any(Task.class));
    }

    @Test
    void testDeleteTaskSuccess() throws Exception {
        doNothing().when(taskService).deleteTask(121);

        mockMvc.perform(delete("/deleteTask/121"))
                .andExpect(status().isOk())
                .andExpect(content().string("Task deleted successfully"));

        verify(taskService, times(1)).deleteTask(121);
    }

    @Test
    void testDeleteTaskNotFound() throws Exception {
        doThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, "Task with ID 999 not found"))
                .when(taskService).deleteTask(999);

        mockMvc.perform(delete("/deleteTask/999"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Task with ID 999 not found"));

        verify(taskService, times(1)).deleteTask(999);
    }
}
