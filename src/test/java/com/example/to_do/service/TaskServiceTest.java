//package com.example.to_do.service;
//
//import com.example.to_do.model.Task;
//import com.example.to_do.repository.TaskRepo;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.List;
//
//import static org.mockito.Mockito.*;
//import static org.junit.jupiter.api.Assertions.*;
//
//@ExtendWith(MockitoExtension.class)
//class TaskServiceTest {
//
//    @Mock
//    private TaskRepo repo;
//
//    @InjectMocks
//    private TaskService service;
//
//    @Test
//    void addTask_savesUsingRepository() {
//        Task t = new Task(201, "A", "B", false);
//        service.addTask(t);
//        verify(repo, times(1)).save(t);
//    }
//
//    @Test
//    void getTasks_returnsListFromRepository() {
//        var t = new Task(202, "Title", "Desc", false);
//        when(repo.findAll()).thenReturn(List.of(t));
//
//        var result = service.getTasks();
//        assertEquals(1, result.size());
//        assertEquals("Title", result.get(0).getTaskTitle());
//        verify(repo, times(1)).findAll();
//    }
//
//    @Test
//    void deleteTask_callsRepositoryDeleteById() {
//        service.deleteTask(202);
//        verify(repo, times(1)).deleteById(202);
//    }
//}
