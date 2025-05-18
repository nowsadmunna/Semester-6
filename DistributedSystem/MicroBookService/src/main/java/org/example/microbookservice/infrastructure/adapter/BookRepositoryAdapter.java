package org.example.microbookservice.infrastructure.adapter;

import org.example.microbookservice.domain.model.Book;
import org.example.microbookservice.domain.port.out.BookRepositoryPort;
import org.example.microbookservice.infrastructure.mapper.BookMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Repository
public class BookRepositoryAdapter implements BookRepositoryPort {
    private final SpringMongoBookRepository repository;

    public BookRepositoryAdapter(SpringMongoBookRepository repository) {
        this.repository = repository;
    }

    @Override
    public Book save(Book book) {
        return BookMapper.toDomain(repository.save(BookMapper.toEntity(book)));
    }

    @Override
    public Optional<Book> findById(String id) {
        return repository.findById(id).map(BookMapper::toDomain);
    }

    @Override
    public List<Book> search(String keyword) {
        return repository.search(keyword).stream()
                .map(BookMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }
}
