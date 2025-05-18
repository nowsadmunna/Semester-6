package org.example.microloanservice.domain.port.out;

import org.example.microloanservice.domain.model.Loan;

import java.util.List;
import java.util.Optional;

public interface LoanRepositoryPort {
    Loan save(Loan loan);
    Optional<Loan> findById(String id);
    List<Loan> findByUserId(String userId);
    List<Loan> findAll();
}
