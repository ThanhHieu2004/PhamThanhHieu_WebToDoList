package edu.ute.PhamThanhHieu_WebToDoList.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TaskController {
    @GetMapping("/task")
    public String showTasks() {
        return "task"; 
    }
}
