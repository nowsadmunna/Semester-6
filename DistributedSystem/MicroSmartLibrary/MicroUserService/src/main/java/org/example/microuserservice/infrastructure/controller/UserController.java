package org.example.microuserservice.infrastructure.controller;

import org.example.microuserservice.domain.model.User;
import org.example.microuserservice.domain.port.in.UserUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserUseCase service;
    public UserController(UserUseCase service) {
        this.service = service;
    }
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user){
        User createdUser=service.createUser(user);
        return ResponseEntity.status(201).body(createdUser);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable String id) {
        User user=service.getUserById(id).orElseThrow();
        return ResponseEntity.status(200).body(user);
    }
    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable String id, @RequestBody Map<String,String> request) {
        String name=request.get("name");
        String email=request.get("email");
        User updatedUser=service.updateUser(id,name,email);
        return ResponseEntity.status(200).body(updatedUser);
    }
}
