package org.example.smartlibrary.service;

import org.example.smartlibrary.Entity.Book;
import org.example.smartlibrary.Entity.Loan;
import org.example.smartlibrary.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private LoanService loanService;
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }
    public Book getBookDetails(String id) {
        return bookRepository.findById(id).get();
    }
    public Book updateBook(String id,int copies,int availableCopies) {
        Book book=bookRepository.findById(id).get();
        book.setCopies(copies);
        book.setAvailableCopies(availableCopies);
        return bookRepository.save(book);
    }

    public void deleteBook(String id) {
        bookRepository.deleteById(id);
    }
    public Book getBookById(String id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }

    public void decrementAvailableCopies(Book book) {
        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepository.save(book);
    }

    public void incrementAvailableCopies(Book book) {
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookRepository.save(book);
    }
    public List<Map<String, Object>> getMostBorrowedBooks() {
        List<Loan> loans = loanService.getAllLoans(); // Inject and call LoanService

        Map<String, Long> bookCountMap = loans.stream()
                .collect(Collectors.groupingBy(loan -> loan.getBook().getId(), Collectors.counting()));

        return bookCountMap.entrySet().stream()
                .sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
                .map(entry -> {
                    Book book = getBookById(entry.getKey());
                    Map<String, Object> bookInfo = new HashMap<>();
                    bookInfo.put("book_id", book.getId());
                    bookInfo.put("title", book.getTitle());
                    bookInfo.put("author", book.getAuthor());
                    bookInfo.put("borrow_count", entry.getValue());
                    return bookInfo;
                })
                .collect(Collectors.toList());
    }
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

}
