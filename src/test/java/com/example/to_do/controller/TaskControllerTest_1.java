//package com.example.to_do.controller;
//
//import com.example.to_do.model.Task;
//import com.example.to_do.service.TaskService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.List;
//
//import static org.hamcrest.Matchers.hasSize;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@WebMvcTest(TaskController.class)
//class TaskControllerTest_1 {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    // Jackson mapper to convert objects to JSON
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @MockBean
//    private TaskService service;
//
//    @Test
//    void getTasks_returnsListOfTasks() throws Exception {
//        var t = new Task(101, "Buy groceries", "Milk & eggs", false);
//        when(service.getTasks()).thenReturn(List.of(t));
//
//        mockMvc.perform(get("/getTask"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(1)))
//                .andExpect(jsonPath("$[0].taskId").value(101))
//                .andExpect(jsonPath("$[0].taskTitle").value("Buy groceries"))
//                .andExpect(jsonPath("$[0].taskDescription").value("Milk & eggs"));
//
//        verify(service, times(1)).getTasks();
//    }
//
//    @Test
//    void addTask_callsServiceAndReturns200() throws Exception {
//        var t = new Task(105, "New task", "desc", false);
//        var body = objectMapper.writeValueAsString(t);
//
//        mockMvc.perform(post("/postTask")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(body))
//                .andExpect(status().isOk()); // or isCreated() if you changed controller to return 201
//
//        verify(service, times(1)).addTask(any(Task.class));
//    }
//
//    @Test
//    void updateTask_callsService() throws Exception {
//        var t = new Task(105, "Updated", "updated desc", true);
//        var body = objectMapper.writeValueAsString(t);
//
//        mockMvc.perform(put("/putTask")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(body))
//                .andExpect(status().isOk());
//
//        verify(service, times(1)).updateTask(any(Task.class));
//    }
//
//    @Test
//    void deleteTask_callsService() throws Exception {
//        mockMvc.perform(delete("/deleteTask/{taskId}", 105))
//                .andExpect(status().isOk());
//
//        verify(service, times(1)).deleteTask(105);
//    }
//}
