package edu.ute.PhamThanhHieu_WebToDoList.repo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ute.PhamThanhHieu_WebToDoList.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUserId(Long userId);
    List<Task> findByUserIdAndIsCompleted(Long userId, Boolean isCompleted);
    List<Task> findByUserIdAndCategoryId(Long userId, Long categoryId);
    List<Task> findByUserIdAndDueDateBefore(Long userId, LocalDateTime dueDate);
}

