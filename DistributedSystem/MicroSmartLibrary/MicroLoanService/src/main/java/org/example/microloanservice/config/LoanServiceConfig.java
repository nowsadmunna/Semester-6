package org.example.microloanservice.config;

import org.example.microloanservice.domain.port.in.LoanUseCase;
import org.example.microloanservice.domain.port.out.BookServicePort;
import org.example.microloanservice.domain.port.out.LoanRepositoryPort;
import org.example.microloanservice.domain.port.out.UserServicePort;
import org.example.microloanservice.infrastructure.client.BookServiceClientAdapter;
import org.example.microloanservice.infrastructure.client.BookServiceFeignClient;
import org.example.microloanservice.infrastructure.client.UserServiceClientAdapter;
import org.example.microloanservice.infrastructure.client.UserServiceFeignClient;
import org.example.microloanservice.service.LoanService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoanServiceConfig {

    @Bean
    public LoanUseCase loanUseCase(LoanRepositoryPort repository,
                                   BookServicePort bookService,
                                   UserServicePort userService) {
        return new LoanService(repository, bookService, userService);
    }
    @Bean
    public UserServiceClientAdapter userServiceClientAdapter(UserServiceFeignClient client) {
        return new UserServiceClientAdapter(client);
    }
    @Bean
    public BookServiceClientAdapter bookServiceClientAdapter(BookServiceFeignClient client) {
        return new BookServiceClientAdapter(client);
    }

}
