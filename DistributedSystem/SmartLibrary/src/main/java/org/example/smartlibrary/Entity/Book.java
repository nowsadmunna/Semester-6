package org.example.smartlibrary.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "books")
public class Book {
    @Id
    private String id;

    private String title;
    private String author;
    private String isbn;
    private int copies;
    private int availableCopies;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}

