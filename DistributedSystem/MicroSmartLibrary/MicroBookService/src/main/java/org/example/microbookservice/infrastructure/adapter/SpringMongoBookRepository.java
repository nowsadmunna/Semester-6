package org.example.microbookservice.infrastructure.adapter;

import org.example.microbookservice.infrastructure.entity.BookEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SpringMongoBookRepository extends MongoRepository<BookEntity, String> {
    List<BookEntity> findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(String title, String author);

    default List<BookEntity> search(String keyword) {
        return findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(keyword, keyword);
    }
}
