package edu.ute.PhamThanhHieu_WebToDoList.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import edu.ute.PhamThanhHieu_WebToDoList.dto.TaskListResponseDTO;
// Ensure the correct package path for CustomUserDetails
import edu.ute.PhamThanhHieu_WebToDoList.security.CustomUserDetails; // Update this path if necessary
import edu.ute.PhamThanhHieu_WebToDoList.service.TaskService;
import org.springframework.security.core.Authentication;

@Controller
public class HomeController {

    @Autowired
    TaskService taskService;

    @GetMapping("/")
    public String index(Model model, @org.springframework.web.bind.annotation.RequestParam(required = false) String sortBy) {
        int userId = getCurrentUserId(); // retrieve user ID from session or security
        TaskListResponseDTO taskDto = taskService.getPendingTasksByUserId(userId, sortBy);
        
        model.addAttribute("taskCount", taskDto.getTasks().size());
        model.addAttribute("taskListResponse", taskDto);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("pageTitle", "Công việc cần làm");
        model.addAttribute("isFinishedTasksPage", false);
        return "index";
    }

    @GetMapping("/finished-tasks")
    public String finishedTasks(Model model, @org.springframework.web.bind.annotation.RequestParam(required = false) String sortBy) {
        int userId = getCurrentUserId();
        TaskListResponseDTO taskDto = taskService.getCompletedTasksByUserId(userId, sortBy);
        
        model.addAttribute("taskCount", taskDto.getTasks().size());
        model.addAttribute("taskListResponse", taskDto);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("pageTitle", "Công việc đã hoàn thành");
        model.addAttribute("isFinishedTasksPage", true);
        return "finished_task";
    }

    private int getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return userDetails.getId();
    }
}
