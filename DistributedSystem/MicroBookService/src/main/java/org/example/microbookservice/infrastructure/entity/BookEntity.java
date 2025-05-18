package org.example.microbookservice.infrastructure.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "books")
public class BookEntity {
    @Id
    private String id;
    private String title;
    private String author;
    private String isbn;
    private int copies;
    private int availableCopies;
}
