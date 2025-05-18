package org.example.microbookservice.config;

import org.example.microbookservice.application.BookService;
import org.example.microbookservice.domain.port.in.BookUseCase;
import org.example.microbookservice.domain.port.out.BookRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
    @Bean
    public BookUseCase bookUseCase(BookRepositoryPort repositoryport) {
        return new BookService(repositoryport);
    }

}
