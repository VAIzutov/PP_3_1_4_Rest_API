package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

@RestController
@RequestMapping("/api/users")
public class CurrentUserController {

    @Autowired
    private UserService userService;

    // Отобразить текущего юзера
    @GetMapping("/current")
    public ResponseEntity<User> getCurrentUser(Authentication auth) {
        if (auth != null && auth.isAuthenticated()) {
            User user = userService.findByUsername(auth.getName());
            if (user != null) {
                user.getRoles().size();
                return ResponseEntity.ok(user);
            }
        }
        return ResponseEntity.status(401).build();
    }
}