package org.example.microuserservice.application;

import org.example.microuserservice.domain.model.User;
import org.example.microuserservice.domain.port.in.UserUseCase;
import org.example.microuserservice.domain.port.out.UserRepositoryPort;

import java.util.Optional;

public class UserService implements UserUseCase {

    private final UserRepositoryPort repository;

    public UserService(UserRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public User createUser(User user) {
        return repository.save(user);
    }

    @Override
    public Optional<User> getUserById(String id) {
        return repository.findById(id);
    }

    @Override
    public User updateUser(String id, String name, String email) {
        User user = repository.findById(id).orElseThrow();
        user.setName(name);
        user.setEmail(email);
        return repository.save(user);
    }
}
