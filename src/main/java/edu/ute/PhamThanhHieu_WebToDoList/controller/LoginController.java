package edu.ute.PhamThanhHieu_WebToDoList.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String login(@RequestParam(required = false) String registered, 
                       @RequestParam(required = false) String error,
                       @RequestParam(required = false) String logout,
                       Model model) {
        
        if (registered != null) {
            model.addAttribute("success", "Đăng ký thành công! Vui lòng đăng nhập.");
        }
        
        if (error != null) {
            model.addAttribute("loginError", "Thông tin đăng nhập không hợp lệ.");
        }
        
        if (logout != null) {
            model.addAttribute("success", "Bạn đã đăng xuất thành công.");
        }
        
        model.addAttribute("showSignup", false);
        return "login";
    }
    
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("showSignup", true);
        return "login";
    }
}
