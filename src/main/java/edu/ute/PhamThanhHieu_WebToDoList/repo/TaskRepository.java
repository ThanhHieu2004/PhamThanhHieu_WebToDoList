package edu.ute.PhamThanhHieu_WebToDoList.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ute.PhamThanhHieu_WebToDoList.model.Task;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findByUserId(int userId);
    List<Task> findByUserIdOrderByCreatedAtDesc(int userId);
    List<Task> findByUserIdOrderByDueDateAsc(int userId);
    List<Task> findByUserIdOrderByPriorityDesc(int userId);
    List<Task> findByUserIdAndIsCompleted(int userId, Boolean isCompleted);
    List<Task> findByUserIdAndCategoryId(int userId, int categoryId);
    List<Task> findByUserIdAndDueDateBefore(int userId, LocalDate dueDate);
    
    // Search functionality
    List<Task> findByUserIdAndTitleContainingIgnoreCaseOrUserIdAndDescriptionContainingIgnoreCase(
        int userId1, String keyword1, int userId2, String keyword2);
    List<Task> findByUserIdAndTitleContainingIgnoreCaseOrUserIdAndDescriptionContainingIgnoreCaseOrderByCreatedAtDesc(
        int userId1, String keyword1, int userId2, String keyword2);
    List<Task> findByUserIdAndTitleContainingIgnoreCaseOrUserIdAndDescriptionContainingIgnoreCaseOrderByDueDateAsc(
        int userId1, String keyword1, int userId2, String keyword2);
    List<Task> findByUserIdAndTitleContainingIgnoreCaseOrUserIdAndDescriptionContainingIgnoreCaseOrderByPriorityDesc(
        int userId1, String keyword1, int userId2, String keyword2);
}

