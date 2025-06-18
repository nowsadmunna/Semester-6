package org.example.microuserservice.domain.port.out;

import org.example.microuserservice.domain.model.User;

import java.util.Optional;

public interface UserRepositoryPort {
    User save(User user);
    Optional<User> findById(String id);
}
