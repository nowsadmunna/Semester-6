package org.example.smartlibrary.controller;

import org.example.smartlibrary.Entity.Loan;
import org.example.smartlibrary.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class LoanController {
    @Autowired
    private LoanService loanService;
    @PostMapping("/loans")
    public ResponseEntity<Map<String, Object>> issueBook(@RequestBody Map<String, Object> request) {
        Map<String, Object> response = loanService.issueLoan(request);
        return ResponseEntity.status(201).body(response);
    }
    @PostMapping("/returns")
    public ResponseEntity<Map<String, Object>> returnBook(@RequestBody Map<String, Object> request) {
        Map<String, Object> response = loanService.returnBook(request);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/loans/{userId}")
    public ResponseEntity<List<Map<String, Object>>> getUserLoanHistory(@PathVariable String userId) {
        List<Map<String, Object>> loanHistory = loanService.getLoanHistoryForUser(userId);
        return ResponseEntity.ok(loanHistory);
    }
    @GetMapping("/loans/overdue")
    public ResponseEntity<List<Map<String, Object>>> getOverdueLoans() {
        List<Map<String, Object>> response = loanService.getOverdueLoans();
        return ResponseEntity.ok(response);
    }
    @PutMapping("/loans/{id}/extend")
    public ResponseEntity<Map<String, Object>> extendLoan(
            @PathVariable String id,
            @RequestBody Map<String, Integer> request) {
        int extensionDays = request.get("extension_days");
        Map<String, Object> response = loanService.extendLoan(id, extensionDays);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/loans/stats/overview")
    public ResponseEntity<Map<String, Object>> getStatsOverview() {
        Map<String, Object> result = loanService.getSystemOverview();
        return ResponseEntity.ok(result);
    }


}
