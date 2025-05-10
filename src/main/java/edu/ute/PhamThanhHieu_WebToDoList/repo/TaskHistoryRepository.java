package edu.ute.PhamThanhHieu_WebToDoList.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ute.PhamThanhHieu_WebToDoList.model.TaskHistory;

public interface TaskHistoryRepository extends JpaRepository<TaskHistory, Integer> {
    List<TaskHistory> findByTaskId(int taskId);
}
