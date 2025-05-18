package org.example.microbookservice.domain.port.in;

import org.example.microbookservice.domain.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookUseCase {
    Book addBook(Book book);
    Optional<Book> getBookById(String id);
    List<Book> searchBooks(String query);
    Book updateBook(String id, int copies);
    void deleteBook(String id);
    Book adjustAvailability(String id, int delta);
}
