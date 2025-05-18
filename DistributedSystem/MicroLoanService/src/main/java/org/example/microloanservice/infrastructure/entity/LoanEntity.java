package org.example.microloanservice.infrastructure.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "loans")
public class LoanEntity {
    @Id
    private String id;
    private String userId;
    private String bookId;
    private Instant issueDate;
    private Instant dueDate;
    private Instant returnDate;
    private String status;

}

