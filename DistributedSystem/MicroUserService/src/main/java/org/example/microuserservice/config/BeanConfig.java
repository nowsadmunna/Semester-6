package org.example.microuserservice.config;

import org.example.microuserservice.application.UserService;
import org.example.microuserservice.domain.port.in.UserUseCase;
import org.example.microuserservice.domain.port.out.UserRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
    @Bean
    public UserUseCase userUseCase(UserRepositoryPort repositoryPort) {
        return new UserService(repositoryPort);
    }
}
