package edu.ute.PhamThanhHieu_WebToDoList.repo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ute.PhamThanhHieu_WebToDoList.model.Task;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findByUserId(int userId);
    List<Task> findByUserIdAndIsCompleted(int userId, Boolean isCompleted);
    List<Task> findByUserIdAndCategoryId(int userId, int categoryId);
    List<Task> findByUserIdAndDueDateBefore(int userId, LocalDateTime dueDate);
}

