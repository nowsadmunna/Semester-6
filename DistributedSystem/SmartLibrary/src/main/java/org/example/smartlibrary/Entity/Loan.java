package org.example.smartlibrary.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor

@Document(collection = "loans")
public class Loan {
    @Id
    private String id;
    @DBRef
    private User user;
    @DBRef
    private Book book;

    private LocalDateTime issueDate;
    private LocalDateTime dueDate;
    private LocalDateTime returnDate;
    private LocalDateTime extendedDueDate;
    private int extensionCount=0;
    private String status;
}

