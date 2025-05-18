package org.example.microloanservice.infrastructure.repository;

import org.example.microloanservice.infrastructure.entity.LoanEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
public interface SpringMongoLoanRepository extends MongoRepository<LoanEntity, String> {
    List<LoanEntity> findByUserId(String userId);
}
