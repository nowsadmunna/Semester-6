package org.example.microloanservice.infrastructure.client;

import org.example.microloanservice.domain.model.dto.BookDto;
import org.example.microloanservice.domain.port.out.BookServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;


public class BookServiceClientAdapter implements BookServicePort {
    private final BookServiceFeignClient client;

    public BookServiceClientAdapter(BookServiceFeignClient client) {
        this.client = client;
    }

    @Override
    public BookDto getBook(String bookId) {
        return client.getBookById(bookId);
    }

    @Override
    public void updateAvailability(String bookId, Map<String,Object> operation) {
        client.updateAvailability(bookId, operation);
    }
}
