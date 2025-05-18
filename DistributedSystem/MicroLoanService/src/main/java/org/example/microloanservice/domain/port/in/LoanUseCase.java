package org.example.microloanservice.domain.port.in;

import org.example.microloanservice.domain.model.Loan;

import java.util.List;

public interface LoanUseCase {
    Loan issueLoan(String userId, String bookId, String dueDate);
    Loan returnLoan(String loanId);
    List<Loan> getUserLoans(String userId);
    Loan getLoanById(String id);
}
