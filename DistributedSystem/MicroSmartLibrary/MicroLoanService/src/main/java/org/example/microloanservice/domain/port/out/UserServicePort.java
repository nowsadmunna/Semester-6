package org.example.microloanservice.domain.port.out;

import org.example.microloanservice.domain.model.dto.UserDto;

public interface UserServicePort {
    UserDto getUser(String userId);
}
