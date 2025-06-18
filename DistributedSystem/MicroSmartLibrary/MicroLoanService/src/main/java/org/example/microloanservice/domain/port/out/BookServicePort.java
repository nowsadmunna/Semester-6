package org.example.microloanservice.domain.port.out;

import org.example.microloanservice.domain.model.dto.BookDto;

import java.util.Map;

public interface BookServicePort {
    BookDto getBook(String bookId);
    void updateAvailability(String bookId, Map<String,Object> operation);
}
