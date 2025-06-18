package org.example.microuserservice.domain.port.in;

import org.example.microuserservice.domain.model.User;

import java.util.Optional;

public interface UserUseCase {
    User createUser(User user);
    Optional<User> getUserById(String id);
    User updateUser(String id,String name, String email);
}
