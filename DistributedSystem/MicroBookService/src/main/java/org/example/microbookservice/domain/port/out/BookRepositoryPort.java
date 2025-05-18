package org.example.microbookservice.domain.port.out;

import org.example.microbookservice.domain.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepositoryPort {
    Book save(Book book);
    Optional<Book> findById(String id);
    List<Book> search(String keyword);
    void deleteById(String id);
}
