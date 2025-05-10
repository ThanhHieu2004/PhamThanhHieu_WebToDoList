package edu.ute.PhamThanhHieu_WebToDoList.service;

import java.util.Collections;
import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import edu.ute.PhamThanhHieu_WebToDoList.dto.TaskListResponseDTO;
import edu.ute.PhamThanhHieu_WebToDoList.dto.TaskResponseDTO;
import edu.ute.PhamThanhHieu_WebToDoList.mapper.TaskMapper;
import edu.ute.PhamThanhHieu_WebToDoList.model.Task;
import edu.ute.PhamThanhHieu_WebToDoList.repo.TaskRepository;

@Service
public class TaskService {
    private final TaskRepository taskRepo;
    private final TaskMapper taskMapper;

    public TaskService(TaskRepository taskRepo, TaskMapper taskMapper) {
        this.taskRepo = taskRepo;
        this.taskMapper = taskMapper;
    }

    public TaskListResponseDTO getTasksByUserId(int userId) {
        List<Task> taskList = taskRepo.findByUserId(userId);
        List<TaskResponseDTO> dtoList = taskMapper.toDtoList(taskList); 
        TaskListResponseDTO response = new TaskListResponseDTO();
        response.setTasks(dtoList);
        return response;
    }
}
