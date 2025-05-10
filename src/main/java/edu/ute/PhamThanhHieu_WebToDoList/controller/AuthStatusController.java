package edu.ute.PhamThanhHieu_WebToDoList.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthStatusController {

    @GetMapping("/auth-status")
    public String showAuthStatus(Model model) {
        return "auth-status";
    }
}
