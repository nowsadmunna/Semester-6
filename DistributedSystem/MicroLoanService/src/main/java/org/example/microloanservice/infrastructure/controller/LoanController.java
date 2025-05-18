package org.example.microloanservice.infrastructure.controller;

import org.example.microloanservice.domain.model.Loan;
import org.example.microloanservice.domain.port.in.LoanUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    private final LoanUseCase loanUseCase;

    public LoanController(LoanUseCase loanUseCase) {
        this.loanUseCase = loanUseCase;
    }

    @PostMapping("/issue")
    public ResponseEntity<Loan> issueLoan(@RequestBody Map<String, String> body) {
        String userId = body.get("userId");
        String bookId = body.get("bookId");
        String dueDate = body.get("dueDate");

        Loan loan = loanUseCase.issueLoan(userId, bookId, dueDate);
        return ResponseEntity.ok(loan);
    }


    @PostMapping("/returns")
    public ResponseEntity<Loan> returnLoan(@RequestBody Map<String, String> body) {
        Loan returnedLoan = loanUseCase.returnLoan(body.get("loanId"));
        return ResponseEntity.ok(returnedLoan);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Loan>> getLoansByUser(@PathVariable String userId) {
        List<Loan> loans = loanUseCase.getUserLoans(userId);
        return ResponseEntity.ok(loans);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Loan> getLoanById(@PathVariable String id) {
        Loan loan = loanUseCase.getLoanById(id);
        return ResponseEntity.ok(loan);
    }
}