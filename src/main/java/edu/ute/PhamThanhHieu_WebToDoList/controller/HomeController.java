package edu.ute.PhamThanhHieu_WebToDoList.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import edu.ute.PhamThanhHieu_WebToDoList.dto.TaskListResponseDTO;
import edu.ute.PhamThanhHieu_WebToDoList.service.TaskService;

@Controller
public class HomeController {
    @GetMapping("/")
    public String test() {
        return "index";
    }

    @Autowired
    TaskService taskService;

    @GetMapping("/{user_id}")
    public String index(@PathVariable("user_id") int user_id, Model model) {
        TaskListResponseDTO taskDto = taskService.getTasksByUserId(user_id);
        // taskDto.setTasks(new ArrayList<>());
        model.addAttribute("taskListResponse", taskDto);
        return "index";
    }
}
