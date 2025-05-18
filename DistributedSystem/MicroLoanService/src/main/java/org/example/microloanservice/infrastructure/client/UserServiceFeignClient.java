package org.example.microloanservice.infrastructure.client;

import org.example.microloanservice.domain.model.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", url = "${user-service.url}")

public interface UserServiceFeignClient {
    @GetMapping("/api/users/{id}")
    UserDto getUserById(@PathVariable("id") String id);
}