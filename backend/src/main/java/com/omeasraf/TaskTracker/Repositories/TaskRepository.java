package com.omeasraf.TaskTracker.Repositories;

import com.omeasraf.TaskTracker.Models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @Transactional
    @Modifying
    @Query(value = "delete from Task t where t.id = ?1")
    void deleteByID(@NonNull Long id);
    

}