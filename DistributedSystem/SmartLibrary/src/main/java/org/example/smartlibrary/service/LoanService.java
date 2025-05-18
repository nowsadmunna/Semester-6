package org.example.smartlibrary.service;

import org.example.smartlibrary.Entity.Book;
import org.example.smartlibrary.Entity.Loan;
import org.example.smartlibrary.Entity.User;
import org.example.smartlibrary.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class LoanService {
    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    @Lazy
    private BookService bookService;
    @Autowired
    @Lazy
    private UserService userService;

    public Map<String, Object> issueLoan(Map<String, Object> request) {
        String userId = (String) request.get("userId");
        String bookId = (String) request.get("bookId");
        String dueDateStr = (String) request.get("dueDate");

        User user = userService.getUserById(userId);
        Book book = bookService.getBookById(bookId);

        if (book.getAvailableCopies() <= 0) {
            throw new RuntimeException("No available copies for the book");
        }

        Loan loan = new Loan();
        loan.setUser(user);
        loan.setBook(book);
        loan.setIssueDate(LocalDateTime.now());
        loan.setDueDate(OffsetDateTime.parse(dueDateStr).toLocalDateTime());
        loan.setStatus("ACTIVE");

        Loan savedLoan = loanRepository.save(loan);

        bookService.decrementAvailableCopies(book);

        Map<String, Object> response = new HashMap<>();
        response.put("id", savedLoan.getId());
        response.put("user_id", user.getId());
        response.put("book_id", book.getId());
        response.put("issue_date", savedLoan.getIssueDate());
        response.put("due_date", savedLoan.getDueDate());
        response.put("status", savedLoan.getStatus());

        return response;
    }

    public Map<String, Object> returnBook(Map<String, Object> request) {
        String loanId = (String) request.get("loanId");

        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found"));

        if ("RETURNED".equals(loan.getStatus())) {
            throw new RuntimeException("Loan already returned");
        }

        loan.setReturnDate(LocalDateTime.now());
        loan.setStatus("RETURNED");

        bookService.incrementAvailableCopies(loan.getBook());

        Loan updatedLoan = loanRepository.save(loan);

        Map<String, Object> response = new HashMap<>();
        response.put("id", updatedLoan.getId());
        response.put("user_id", updatedLoan.getUser().getId());
        response.put("book_id", updatedLoan.getBook().getId());
        response.put("issue_date", updatedLoan.getIssueDate());
        response.put("due_date", updatedLoan.getDueDate());
        response.put("return_date", updatedLoan.getReturnDate());
        response.put("status", updatedLoan.getStatus());

        return response;
    }

    public List<Map<String, Object>> getLoanHistoryForUser(String userId) {
        List<Loan> loans = loanRepository.findByUser_Id(userId);
        List<Map<String, Object>> response = new ArrayList<>();

        for (Loan loan : loans) {
            Map<String, Object> loanMap = new HashMap<>();
            loanMap.put("id", loan.getId());

            Map<String, Object> bookMap = new HashMap<>();
            bookMap.put("id", loan.getBook().getId());
            bookMap.put("title", loan.getBook().getTitle());
            bookMap.put("author", loan.getBook().getAuthor());

            loanMap.put("book", bookMap);
            loanMap.put("issue_date", loan.getIssueDate());
            loanMap.put("due_date", loan.getDueDate());
            loanMap.put("return_date", loan.getReturnDate());
            loanMap.put("status", loan.getStatus());

            response.add(loanMap);
        }

        return response;
    }
    public List<Map<String, Object>> getOverdueLoans() {
        LocalDateTime now = LocalDateTime.now();

        List<Loan> overdueLoans = loanRepository.findByDueDateBeforeAndReturnDateIsNull(now);

        return overdueLoans.stream().map(loan -> {
            Map<String, Object> result = new LinkedHashMap<>();

            result.put("id", loan.getId());

            Map<String, Object> userMap = new HashMap<>();
            userMap.put("id", loan.getUser().getId());
            userMap.put("name", loan.getUser().getName());
            userMap.put("email", loan.getUser().getEmail());
            result.put("user", userMap);

            Map<String, Object> bookMap = new HashMap<>();
            bookMap.put("id", loan.getBook().getId());
            bookMap.put("title", loan.getBook().getTitle());
            bookMap.put("author", loan.getBook().getAuthor());
            result.put("book", bookMap);

            result.put("issue_date", loan.getIssueDate());
            result.put("due_date", loan.getDueDate());

            long daysOverdue = Duration.between(loan.getDueDate(), now).toDays();
            result.put("days_overdue", daysOverdue);

            return result;
        }).collect(Collectors.toList());
    }
    public Map<String, Object> extendLoan(String loanId, int extensionDays) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found"));

        if (loan.getStatus().equals("RETURNED")) {
            throw new RuntimeException("Cannot extend a returned loan");
        }

        LocalDateTime originalDueDate = loan.getExtendedDueDate() != null
                ? loan.getExtendedDueDate()
                : loan.getDueDate();

        LocalDateTime newExtendedDueDate = originalDueDate.plusDays(extensionDays);
        loan.setExtendedDueDate(newExtendedDueDate);
        loan.setExtensionCount(loan.getExtensionCount() + 1);

        loanRepository.save(loan);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("id", loan.getId());
        response.put("user_id", loan.getUser().getId());
        response.put("book_id", loan.getBook().getId());
        response.put("issue_date", loan.getIssueDate());
        response.put("original_due_date", loan.getDueDate());
        response.put("extended_due_date", loan.getExtendedDueDate());
        response.put("status", loan.getStatus());
        response.put("extensions_count", loan.getExtensionCount());

        return response;
    }
    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    public Map<String, Object> getSystemOverview() {
        List<Book> books = bookService.getAllBooks();
        List<Loan> loans = getAllLoans();

        long totalBooks = books.stream().mapToLong(Book::getCopies).sum();
        long availableBooks = books.stream().mapToLong(Book::getAvailableCopies).sum();
        long borrowedBooks = totalBooks - availableBooks;

        long totalUsers = userService.countUsers(); // New method in UserService
        long overdueLoans = loans.stream()
                .filter(loan -> "ACTIVE".equalsIgnoreCase(loan.getStatus()) &&
                        loan.getDueDate().isBefore(LocalDateTime.now()))
                .count();

        LocalDate today = LocalDate.now();

        long loansToday = loans.stream()
                .filter(loan -> loan.getIssueDate().toLocalDate().equals(today))
                .count();

        long returnsToday = loans.stream()
                .filter(loan -> loan.getReturnDate() != null &&
                        loan.getReturnDate().toLocalDate().equals(today))
                .count();

        Map<String, Object> map = new HashMap<>();
        map.put("total_books", totalBooks);
        map.put("total_users", totalUsers);
        map.put("books_available", availableBooks);
        map.put("books_borrowed", borrowedBooks);
        map.put("overdue_loans", overdueLoans);
        map.put("loans_today", loansToday);
        map.put("returns_today", returnsToday);

        return map;
    }


}
