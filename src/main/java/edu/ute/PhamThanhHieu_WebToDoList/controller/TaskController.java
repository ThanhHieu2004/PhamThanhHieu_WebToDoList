package edu.ute.PhamThanhHieu_WebToDoList.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

import edu.ute.PhamThanhHieu_WebToDoList.dto.TaskListResponseDTO;
import edu.ute.PhamThanhHieu_WebToDoList.dto.TaskRequestDTO;
import edu.ute.PhamThanhHieu_WebToDoList.dto.TaskResponseDTO;
import edu.ute.PhamThanhHieu_WebToDoList.security.CustomUserDetails;
import edu.ute.PhamThanhHieu_WebToDoList.service.CategoryService;
import edu.ute.PhamThanhHieu_WebToDoList.service.TaskService;

@Controller
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/create_task")
    public String showTaskPage(Model model) {
        model.addAttribute("taskRequest", new TaskRequestDTO());
        model.addAttribute("categories", categoryService.getCategoriesByUserId(getCurrentUserId()));
        return "task";
    }

    @PostMapping("/save_task")
    public String saveTask(@Valid @ModelAttribute TaskRequestDTO taskRequest,
            BindingResult bindingResult,
            @RequestParam("dueDate") String dueDateStr,
            Model model) {

        // Check for validation errors
        if (bindingResult.hasErrors()) {
            // Add categories back to the model for the form
            model.addAttribute("categories", categoryService.getCategoriesByUserId(getCurrentUserId()));
            return "task";
        }

        // Process due date
        if (dueDateStr != null && !dueDateStr.isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate dueDate = LocalDate.parse(dueDateStr, formatter);
            taskRequest.setDueDate(dueDate);
        }
        
        // Description is now directly bound to the taskRequest object via th:field

        // Save task
        taskService.createTask(taskRequest, getCurrentUserId());

        return "redirect:/";
    }

    @GetMapping("/delete_task/{id}")
    public String deleteTask(@PathVariable("id") int taskId) {
        taskService.deleteTask(taskId);
        return "redirect:/";
    }

    @GetMapping("/edit_task/{id}")
    public String showEditTaskPage(@PathVariable("id") int taskId, Model model) {
        TaskResponseDTO taskResponse = taskService.getTaskById(taskId);
        if (taskResponse == null) {
            return "redirect:/";
        }
        
        // Convert the TaskResponseDTO to TaskRequestDTO for the form
        TaskRequestDTO taskRequest = new TaskRequestDTO();
        taskRequest.setTitle(taskResponse.getTitle());
        taskRequest.setDescription(taskResponse.getDescription());
        taskRequest.setDueDate(taskResponse.getDueDate());
        taskRequest.setPriority(taskResponse.getPriority());
        taskRequest.setCategoryId(taskResponse.getCategoryId());
        taskRequest.setIsCompleted(taskResponse.getIsCompleted());
        
        model.addAttribute("taskRequest", taskRequest);
        model.addAttribute("taskId", taskId);
        model.addAttribute("categories", categoryService.getCategoriesByUserId(getCurrentUserId()));
        model.addAttribute("isEditMode", true);
        
        return "task";
    }
    
    @PostMapping("/update_task/{id}")
    public String updateTask(@PathVariable("id") int taskId, 
                            @Valid @ModelAttribute TaskRequestDTO taskRequest,
                            BindingResult bindingResult,
                            @RequestParam("dueDate") String dueDateStr,
                            Model model) {
        
        // Check for validation errors
        if (bindingResult.hasErrors()) {
            model.addAttribute("taskId", taskId);
            model.addAttribute("categories", categoryService.getCategoriesByUserId(getCurrentUserId()));
            model.addAttribute("isEditMode", true);
            return "task";
        }
        
        // Process due date
        if (dueDateStr != null && !dueDateStr.isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate dueDate = LocalDate.parse(dueDateStr, formatter);
            taskRequest.setDueDate(dueDate);
        }
        
        // Update task
        taskService.updateTask(taskId, taskRequest);
        
        return "redirect:/";
    }    @GetMapping("/search_tasks")
    public String searchTasks(@RequestParam("keyword") String keyword, 
                             @RequestParam(required = false) String sortBy,
                             Model model) {
        int userId = getCurrentUserId();
        TaskListResponseDTO taskListResponse = taskService.searchTasks(userId, keyword, sortBy);
        int taskCount = taskListResponse.getTasks() != null ? taskListResponse.getTasks().size() : 0;
        
        model.addAttribute("taskListResponse", taskListResponse);
        model.addAttribute("taskCount", taskCount);
        model.addAttribute("searchKeyword", keyword);
        model.addAttribute("sortBy", sortBy);
        
        return "index";
    }

    private int getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return userDetails.getId();
    }
}