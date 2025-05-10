package edu.ute.PhamThanhHieu_WebToDoList.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.ute.PhamThanhHieu_WebToDoList.model.User;
import edu.ute.PhamThanhHieu_WebToDoList.repo.UserRepository;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/signup")
    public String registerUser(@RequestParam String username,
            @RequestParam String email,
            @RequestParam String password,
            Model model,
            HttpServletRequest request) {
        
        System.out.println("Signup request received - Username: " + username + ", Email: " + email);
        
        try {            // Check if email already exists
            if (userRepository.findByEmail(email).isPresent()) {
                System.out.println("Email already exists: " + email);
                model.addAttribute("error", "Email đã tồn tại.");
                model.addAttribute("showSignup", true);
                return "login"; // return to the signup view with error message
            }
            
            // Username can be duplicate, no need to check uniqueness
            System.out.println("Username check skipped - usernames allowed to be duplicate");
            
            System.out.println("Creating new user: " + username + ", " + email);
            User user = new User();
            user.setUsername(username);
            user.setEmail(email);
            user.setPasswordHash(passwordEncoder.encode(password));
            user.setCreatedAt(LocalDateTime.now());
            
            try {
                userRepository.save(user);
                System.out.println("User saved successfully with ID: " + user.getId());
            } catch (Exception dbException) {
                System.err.println("Database error when saving user: " + dbException.getMessage());
                dbException.printStackTrace();
                throw dbException; // Re-throw to be caught by the outer try-catch
            }
            
            // Auto-login implementation
            try {
                System.out.println("Attempting auto-login for user: " + email);
                // Load the user details we just created
                UserDetails userDetails = userDetailsService.loadUserByUsername(email);
                System.out.println("UserDetails loaded successfully for: " + email);
                
                // Create a pre-authenticated token with full authentication details
                UsernamePasswordAuthenticationToken authToken = 
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                
                // Add the request details to the authentication token
                authToken.setDetails(new WebAuthenticationDetails(request));
                
                // Set the authentication in the SecurityContext
                SecurityContextHolder.getContext().setAuthentication(authToken);                System.out.println("User authenticated and context set successfully");
                
                // Redirect to the auth status page first to verify authentication worked
                return "redirect:/auth-status";
            } catch (Exception e) {
                // If auto-login fails, just redirect to login
                System.err.println("Auto-login failed: " + e.getMessage());
                e.printStackTrace();
                model.addAttribute("success", "Đăng ký thành công! Vui lòng đăng nhập.");
                return "login";
            }
        } catch (Exception e) {
            // Log the error
            System.err.println("Error registering user: " + e.getMessage());
            e.printStackTrace();
            // Show a generic error
            model.addAttribute("error", "Đã xảy ra lỗi khi đăng ký. Vui lòng thử lại sau.");
            model.addAttribute("showSignup", true);
            return "login";
        }
    }
}
