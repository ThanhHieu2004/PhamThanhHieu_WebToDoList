package edu.ute.PhamThanhHieu_WebToDoList.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import edu.ute.PhamThanhHieu_WebToDoList.model.User;
import edu.ute.PhamThanhHieu_WebToDoList.repo.UserRepository;

public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean createUser(String username, String email, String rawPassword) {
        if (userRepository.existsByEmail(email) || userRepository.existsByUsername(username)) {
            return false;
        }

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPasswordHash(passwordEncoder.encode(rawPassword));
        userRepository.save(user);
        return true;
    }
}
