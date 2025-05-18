package org.example.microbookservice.application;

import org.example.microbookservice.domain.model.Book;
import org.example.microbookservice.domain.port.in.BookUseCase;
import org.example.microbookservice.domain.port.out.BookRepositoryPort;

import java.awt.*;
import java.util.List;
import java.util.Optional;

public class BookService implements BookUseCase {
    private final BookRepositoryPort repository;
    public BookService(BookRepositoryPort repository) {
        this.repository = repository;
    }
    @Override
    public Book addBook(Book book) {
        book.setAvailableCopies(book.getCopies());
        return repository.save(book);
    }

    @Override
    public Optional<Book> getBookById(String id) {
        return repository.findById(id);
    }

    @Override
    public List<Book> searchBooks(String query) {
        return repository.search(query);
    }

    @Override
    public Book updateBook(String id, int copies) {
        Book existing = repository.findById(id).orElseThrow();
        existing.setCopies(copies);
        return repository.save(existing);
    }

    @Override
    public void deleteBook(String id) {
        repository.deleteById(id);
    }
    @Override
    public Book adjustAvailability(String id, int delta) {
        Book book = repository.findById(id).orElseThrow();
        book.setAvailableCopies(book.getAvailableCopies() + delta);
        return repository.save(book);
    }
}
