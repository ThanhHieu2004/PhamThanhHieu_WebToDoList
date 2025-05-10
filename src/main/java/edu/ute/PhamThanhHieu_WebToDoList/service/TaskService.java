package edu.ute.PhamThanhHieu_WebToDoList.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import edu.ute.PhamThanhHieu_WebToDoList.dto.TaskListResponseDTO;
import edu.ute.PhamThanhHieu_WebToDoList.dto.TaskRequestDTO;
import edu.ute.PhamThanhHieu_WebToDoList.dto.TaskResponseDTO;
import edu.ute.PhamThanhHieu_WebToDoList.mapper.TaskMapper;
import edu.ute.PhamThanhHieu_WebToDoList.model.Category;
import edu.ute.PhamThanhHieu_WebToDoList.model.Task;
import edu.ute.PhamThanhHieu_WebToDoList.model.User;
import edu.ute.PhamThanhHieu_WebToDoList.repo.CategoryRepository;
import edu.ute.PhamThanhHieu_WebToDoList.repo.TaskRepository;
import edu.ute.PhamThanhHieu_WebToDoList.repo.UserRepository;
import edu.ute.PhamThanhHieu_WebToDoList.utils.PRIORITY;

@Service
public class TaskService {
    private final TaskRepository taskRepo;
    private final TaskMapper taskMapper;
    private final UserRepository userRepo;
    private final CategoryRepository categoryRepo;

    public TaskService(TaskRepository taskRepo, TaskMapper taskMapper, UserRepository userRepo, CategoryRepository categoryRepo) {
        this.taskRepo = taskRepo;
        this.taskMapper = taskMapper;
        this.userRepo = userRepo;
        this.categoryRepo = categoryRepo;
    }    public TaskListResponseDTO getTasksByUserId(int userId, String sortBy) {
        List<Task> taskList;
        
        // Sort by specified criteria
        if (sortBy == null || sortBy.equals("newest")) {
            taskList = taskRepo.findByUserIdOrderByCreatedAtDesc(userId);
        } else if (sortBy.equals("dueDate")) {
            taskList = taskRepo.findByUserIdOrderByDueDateAsc(userId);
        } else if (sortBy.equals("priority")) {
            taskList = taskRepo.findByUserIdOrderByPriorityDesc(userId);
        } else {
            // Default sorting by creation date (newest first)
            taskList = taskRepo.findByUserIdOrderByCreatedAtDesc(userId);
        }
        
        List<TaskResponseDTO> dtoList = taskMapper.toDtoList(taskList); 
        TaskListResponseDTO response = new TaskListResponseDTO();
        response.setTasks(dtoList);
        return response;
    }
    
    public TaskResponseDTO createTask(TaskRequestDTO taskRequest, int userId) {
        Task task = taskMapper.toEntity(taskRequest);
        
        // Set the user
        Optional<User> userOpt = userRepo.findById(userId);
        if (userOpt.isPresent()) {
            task.setUser(userOpt.get());
        } else {
            throw new RuntimeException("User not found");
        }
        
        // Set creation time
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());
        
        // Save task
        Task savedTask = taskRepo.save(task);
        
        return taskMapper.toDto(savedTask);
    }
    
    public TaskResponseDTO getTaskById(int taskId) {
        Optional<Task> taskOpt = taskRepo.findById(taskId);
        if (taskOpt.isPresent()) {
            return taskMapper.toDto(taskOpt.get());
        }
        return null;
    }
      public TaskResponseDTO updateTask(int taskId, TaskRequestDTO taskRequest) {
        Optional<Task> taskOpt = taskRepo.findById(taskId);
        if (taskOpt.isPresent()) {
            Task task = taskOpt.get();
            
            // Update fields
            task.setTitle(taskRequest.getTitle());
            task.setDescription(taskRequest.getDescription());
            task.setDueDate(taskRequest.getDueDate());
            task.setIsCompleted(taskRequest.getIsCompleted());
            task.setUpdatedAt(LocalDateTime.now());
            
            // Handle priority
            try {
                PRIORITY priority = PRIORITY.valueOf(taskRequest.getPriority());
                task.setPriority(priority);
            } catch (IllegalArgumentException e) {
                // Default to medium priority if invalid
                task.setPriority(PRIORITY.medium);
            }
            
            // Update category if provided
            if (taskRequest.getCategoryId() > 0) {
                Optional<Category> categoryOpt = categoryRepo.findById(taskRequest.getCategoryId());
                if (categoryOpt.isPresent()) {
                    task.setCategory(categoryOpt.get());
                }
            }
            
            // Save updated task
            Task updatedTask = taskRepo.save(task);
            
            return taskMapper.toDto(updatedTask);
        }
        return null;
    }
      public void deleteTask(int taskId) {
        taskRepo.deleteById(taskId);
    }
      public TaskListResponseDTO searchTasks(int userId, String keyword, String sortBy) {
        List<Task> taskList;
        
        // Sort search results by specified criteria
        if (sortBy == null || sortBy.equals("newest")) {
            taskList = taskRepo.findByUserIdAndTitleContainingIgnoreCaseOrUserIdAndDescriptionContainingIgnoreCaseOrderByCreatedAtDesc(
                userId, keyword, userId, keyword);
        } else if (sortBy.equals("dueDate")) {
            taskList = taskRepo.findByUserIdAndTitleContainingIgnoreCaseOrUserIdAndDescriptionContainingIgnoreCaseOrderByDueDateAsc(
                userId, keyword, userId, keyword);
        } else if (sortBy.equals("priority")) {
            taskList = taskRepo.findByUserIdAndTitleContainingIgnoreCaseOrUserIdAndDescriptionContainingIgnoreCaseOrderByPriorityDesc(
                userId, keyword, userId, keyword);
        } else {
            // Default sorting by creation date (newest first)
            taskList = taskRepo.findByUserIdAndTitleContainingIgnoreCaseOrUserIdAndDescriptionContainingIgnoreCaseOrderByCreatedAtDesc(
                userId, keyword, userId, keyword);
        }
        
        List<TaskResponseDTO> dtoList = taskMapper.toDtoList(taskList);
        TaskListResponseDTO response = new TaskListResponseDTO();
        response.setTasks(dtoList);
        return response;
    }
}
