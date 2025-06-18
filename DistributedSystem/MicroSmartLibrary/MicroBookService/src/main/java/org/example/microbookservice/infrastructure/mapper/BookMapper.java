package org.example.microbookservice.infrastructure.mapper;

import org.example.microbookservice.domain.model.Book;
import org.example.microbookservice.infrastructure.entity.BookEntity;

public class BookMapper {
    public static BookEntity toEntity(Book book) {
        BookEntity entity = new BookEntity(book.getId(), book.getTitle(), book.getAuthor(), book.getIsbn(), book.getCopies(), book.getAvailableCopies());
        return entity;
    }
    public static Book toDomain(BookEntity entity) {
        Book book=new Book(entity.getTitle(), entity.getAuthor(), entity.getIsbn(), entity.getCopies());
        book.setId(entity.getId());
        book.setAvailableCopies(entity.getAvailableCopies());
        return book;
    }
}
