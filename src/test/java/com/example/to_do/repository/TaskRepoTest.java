package com.example.to_do.repository;

import com.example.to_do.model.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class TaskRepoTest {

    @Autowired
    private TaskRepo repo;

    @Test
    void saveAndFindById() {
        Task t = new Task(301, "Repo test", "desc", false);
        repo.save(t);

        Optional<Task> found = repo.findById(301);
        assertThat(found).isPresent();
        assertThat(found.get().getTaskDescription()).isEqualTo("desc");
    }
}
