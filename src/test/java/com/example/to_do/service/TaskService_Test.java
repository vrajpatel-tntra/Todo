package com.example.to_do.service;

import com.example.to_do.model.Status;
import com.example.to_do.model.Task;
import com.example.to_do.repository.TaskRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.parsing.EmptyReaderEventListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatStream;
import static org.mockito.Mockito.*;

import java.awt.*;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class TaskService_Test {

    @Mock
    TaskRepo taskRepo;

    @InjectMocks
    TaskService taskServiceObj;

    // gettask()
    @Test
    public void getTasks_test() {
    /* STEP-1 :
       Create mock Task objects and put them into a List which will be used as Page content
    */
        Task t1 = new Task();
        t1.setTaskId(1);
        t1.setTaskTitle("Buy groceries");
        t1.setTaskDescription("Milk, Eggs, Bread");
        t1.setCompleted(false);
        t1.setStatus(Status.INCOMPLETE);

        Task t2 = new Task();
        t2.setTaskId(2);
        t2.setTaskTitle("Read book");
        t2.setTaskDescription("Read chapter 4");
        t2.setCompleted(false);
        t2.setStatus(Status.PROGRESS);

        List<Task> mockedList = List.of(t1, t2);

    /* STEP-2 :
       Prepare Pageable and Page objects.
       - page = 0 (first page)
       - size = 10 (page size)
       - sort by "taskId" (same default used in controller)
    */
        Pageable pageable = PageRequest.of(0, 10, Sort.by("taskId"));
        Page<Task> mockedPage = new PageImpl<>(mockedList, pageable, mockedList.size());

    /* STEP-3 :
       Mock repository behavior.
       We expect service.getTasks(pageable) -> repo.findAll(pageable) -> returns mockedPage
       Note: use the same pageable instance in when(...) as you will pass to service.
       If you prefer to match any pageable, you can use: when(taskRepo.findAll(any(Pageable.class))).thenReturn(mockedPage);
    */
        when(taskRepo.findAll(pageable)).thenReturn(mockedPage);

    /* STEP-4 :
       Call service method and assert results
    */
        Page<Task> result = taskServiceObj.getTasks(pageable);

        // Basic equality check (service should return the same Page object)
        assertThat(result).isEqualTo(mockedPage);

        // Extra helpful assertions
        assertThat(result.getTotalElements()).isEqualTo(2);
        assertThat(result.getContent()).hasSize(2);
        assertThat(result.getContent().get(0).getTaskTitle()).isEqualTo("Buy groceries");
        assertThat(result.getContent().get(1).getStatus()).isEqualTo(Status.PROGRESS);
    }


    // addTask()
    @Test
    public void addTask_test(){
        // step-1 :
        Task t1 = new Task();
        t1.setTaskId(1);
        t1.setTaskTitle("Buy groceries");
        t1.setTaskDescription("Milk, Eggs, Bread");
        t1.setCompleted(false);
        t1.setStatus(Status.INCOMPLETE);

        Task savedTask = new Task();
        savedTask.setTaskId(2);
        savedTask.setTaskTitle("Read book");
        savedTask.setTaskDescription("Read chapter 4");
        savedTask.setCompleted(false);
        savedTask.setStatus(Status.PROGRESS);
        
        // step-2 :
        when(taskRepo.save(t1)).thenReturn(savedTask);

        // step-3:
        assertThat(taskServiceObj.addTask(t1)).isEqualTo(savedTask);
        verify(taskRepo, times(1)).save(t1);
    }


    // UPDATE TASK
    @Test
    public void updateTask_test_success(){

        /*
    Step 1 – Setup mock data
    Existing Task (what repo already has)
    Updated Task (what the user sends in the PUT request)
    */

        Task existingTask = new Task();
        existingTask.setTaskId(1);
        existingTask.setTaskTitle("Old Task");
        existingTask.setTaskDescription("Old description");
        existingTask.setCompleted(false);
        existingTask.setStatus(Status.INCOMPLETE);

        Task updatedTask = new Task();
        updatedTask.setTaskId(1);
        updatedTask.setTaskTitle("New Task");
        updatedTask.setTaskDescription("New Description");
        updatedTask.setCompleted(true);
        updatedTask.setStatus(Status.DONE);


    /* STEP-2 : mock repo behaviour
        When repo.findById(taskId) → return existing task wrapped in Optional.of(...)
        When repo.save(existingTask) → return updatedTask (simulate DB save)
        */
        when(taskRepo.findById(1)).thenReturn(Optional.of(existingTask));
        when(taskRepo.save(existingTask)).thenReturn(updatedTask);


    /* STEP -3 : call service method
    */
        Task result = taskServiceObj.updateTask(1,updatedTask);

        assertThat(result).isEqualTo(updatedTask);
//        assertThat(result.getTaskTitle()).isEqualTo("New Task");
//        assertThat(result.getTaskDescription()).isEqualTo("New Description");
//        assertThat(result.getCompleted()).isEqualTo(true);
//        assertThat(result.getStatus()).isEqualTo(Status.DONE);
    }


    // DELETE
    @Test
    public void deleteTask_test_success(){
        Task existingTask = new Task();


        // STEP-1:
        existingTask.setTaskId(1);
        existingTask.setTaskTitle("Learn Java");
        existingTask.setTaskDescription("learn spring ");
        existingTask.setCompleted(false);
        existingTask.setStatus(Status.DONE);

        // STEP-2 :
        when(taskRepo.findById(1)).thenReturn(Optional.of(existingTask));


        // STEP-3 :
        Task deletedtask = taskServiceObj.deleteTask(1);
        assertThat(deletedtask).isEqualTo(existingTask);
    }

    // findByStatus
    @Test
    public void findByStatus_test(){

        // step-1 :
        Task t1 = new Task();
        t1.setTaskId(1);
        t1.setTaskTitle("learn java");
        t1.setStatus(Status.DONE);

        Task t2 = new Task();
        t2.setTaskId(2);
        t2.setTaskTitle("learn python");
        t2.setStatus(Status.INCOMPLETE);

        List<Task> mockedList = List.of(t1,t2);

        // step -2 :
        when(taskRepo.findByStatus(Status.DONE.name())).thenReturn(mockedList);

        // step -3 :
        assertThat(taskServiceObj.findByStatus(Status.DONE)).isEqualTo(mockedList);
        assertThat(taskServiceObj.findByStatus(Status.DONE)).isEqualTo(mockedList);

    }
}
