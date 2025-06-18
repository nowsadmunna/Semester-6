package org.example.microloanservice.infrastructure.client;

import org.example.microloanservice.domain.model.dto.BookDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(name = "book-service", url = "${book-service.url}")

public interface BookServiceFeignClient {

    @GetMapping("/api/books/{id}")
    BookDto getBookById(@PathVariable("id") String id);

    @PatchMapping("/api/books/{id}/availability")
    void updateAvailability(@PathVariable("id") String id, @RequestBody Map<String,Object> body);
}