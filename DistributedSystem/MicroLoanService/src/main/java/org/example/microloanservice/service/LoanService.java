package org.example.microloanservice.service;

import org.example.microloanservice.domain.model.Loan;
import org.example.microloanservice.domain.port.in.LoanUseCase;
import org.example.microloanservice.domain.port.out.BookServicePort;
import org.example.microloanservice.domain.port.out.LoanRepositoryPort;
import org.example.microloanservice.domain.port.out.UserServicePort;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoanService implements LoanUseCase {

    private final LoanRepositoryPort repository;
    private final BookServicePort bookService;
    private final UserServicePort userService;

    public LoanService(LoanRepositoryPort repository, BookServicePort bookService, UserServicePort userService) {
        this.repository = repository;
        this.bookService = bookService;
        this.userService = userService;
    }

    @Override
    public Loan issueLoan(String userId, String bookId, String dueDate) {
        userService.getUser(userId);
        bookService.getBook(bookId);
        Map<String, Object> request = new HashMap<>();
        request.put("availableCopies", 1);
        request.put("operation", "decrement");
        bookService.updateAvailability(bookId,request);

        Loan loan = new Loan();
        loan.setUserId(userId);
        loan.setBookId(bookId);
        loan.setIssueDate(Instant.now());
        loan.setDueDate(Instant.parse(dueDate));
        loan.setStatus("ACTIVE");
        return repository.save(loan);
    }

    @Override
    public Loan returnLoan(String loanId) {
        Loan loan = repository.findById(loanId).orElseThrow();
        loan.setReturnDate(Instant.now());
        loan.setStatus("RETURNED");
        Map<String, Object> request = new HashMap<>();
        request.put("availableCopies", 1);
        request.put("operation", "increment");
        bookService.updateAvailability(loan.getBookId(),request);
        return repository.save(loan);
    }

    @Override
    public List<Loan> getUserLoans(String userId) {
        return repository.findByUserId(userId);
    }

    @Override
    public Loan getLoanById(String id) {
        return repository.findById(id).orElseThrow();
    }
}