package org.example.smartlibrary.controller;

import org.example.smartlibrary.Entity.User;
import org.example.smartlibrary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        return ResponseEntity.status(201).body(userService.registerUser(user));
    }
    @GetMapping("/{id}")
    public User getUserById(@PathVariable String id) {
        return userService.getUserById(id);
    }
    @GetMapping("/active")
    public ResponseEntity<List<Map<String, Object>>> getMostActiveUsers() {
        List<Map<String, Object>> result = userService.getMostActiveUsers();
        return ResponseEntity.ok(result);
    }
}
