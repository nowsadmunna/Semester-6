package org.example.smartlibrary.repository;

import org.example.smartlibrary.Entity.Loan;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Repository
public interface LoanRepository extends MongoRepository<Loan, String> {
    List<Loan> findByUserId(String userId);
    List<Loan> findByStatus(String status);

    List<Loan> findByUser_Id(String userId);
    List<Loan> findByBook_Id(String bookId);

    List<Loan> findByDueDateBeforeAndReturnDateIsNull(LocalDateTime dueDateBefore);


}
