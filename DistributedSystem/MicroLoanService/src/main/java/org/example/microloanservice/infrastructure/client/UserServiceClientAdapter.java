package org.example.microloanservice.infrastructure.client;

import org.example.microloanservice.domain.model.dto.UserDto;
import org.example.microloanservice.domain.port.out.UserServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
public class UserServiceClientAdapter implements UserServicePort {
    private final UserServiceFeignClient client;
    public UserServiceClientAdapter(UserServiceFeignClient client) {
        this.client = client;
    }
    @Override
    public UserDto getUser(String userId) {
        return client.getUserById(userId);
    }
}
